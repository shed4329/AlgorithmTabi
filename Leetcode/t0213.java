package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/31  19:37
 * 链接:https://leetcode.cn/problems/house-robber-ii/
 * <h1>213. 打家劫舍 II</h1>
 * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警 。
 * <br>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额。
 * <h3>示例 1：</h3>
 * 输入：nums = [2,3,2]<br>
 * 输出：3<br>
 * 解释：你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
 * <h3>示例 2：</h3>
 * 输入：nums = [1,2,3,1]<br>
 * 输出：4<br>
 * 解释：你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。<br>
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <h3>示例 3：</h3>
 * 输入：nums = [1,2,3]<br>
 * 输出：3
 * <h2>提示：</h2>
 * <li>1 <= nums.length <= 100</li>
 * <li>0 <= nums[i] <= 1000</li>
 * <h2>Tip</h2>
 * Tag:子数组最大累加和，动态规划<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交solution类，去掉static修饰<br>
 * 这道题在t0198的基础上加强了
 */
public class t0213 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{2, 3, 2}));
        System.out.println(solution.rob(new int[]{1,2, 3, 1}));
        System.out.println(solution.rob(new int[]{1,2,3}));
        System.out.println(solution.rob(new int[]{1,2,1,1}));
    }

    /**
     * 思路分析:<br>
     * 1.打破环形，0位置要和不要<br>
     * 2.要0位置,求2...n-2的max<br>
     * 3.不要0位置,求1...n-1的max
     */
    static class Solution {
        public int rob(int[] nums) {
            int n = nums.length;
            if(n==1){
                return nums[0];
            }
            return Math.max(max(nums,2,n-2)+nums[0],max(nums,1,n-1));
        }

        /**
         * 求arr数组[l..r]的最大累加和,不考虑环形，这里就和t198一样
         * @param arr 数组
         * @param left 开始点
         * @param right 结束点
         * @return 最大累加和
         */
        public int max(int[] arr,int left,int right){
            int n = right-left+1;
            if(n<=0){
                return 0;
            }else if(n==1){
                return arr[left];
            } else if (n==2) {
                return Math.max(arr[left],arr[right]);
            }

            int prepre = arr[left];
            int pre = Math.max(arr[left],arr[left+1]);
            for(int i=left+2,cur;i<=right;i++){
                cur = Math.max(prepre+arr[i],pre);
                prepre = pre;
                pre = cur;
            }

            return pre;
        }
    }
}
