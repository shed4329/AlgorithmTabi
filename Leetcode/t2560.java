package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/31  22:11
 * 链接:https://leetcode.cn/problems/house-robber-iv/
 * <h1>2560. 打家劫舍 IV</h1>
 * 沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。
 * <br>
 * 由于相邻的房屋装有相互连通的防盗系统，所以小偷 不会窃取相邻的房屋 。
 * <br>
 * 小偷的 窃取能力 定义为他在窃取过程中能从单间房屋中窃取的 最大金额 。
 * <br>
 * 给你一个整数数组 nums 表示每间房屋存放的现金金额。形式上，从左起第 i 间房屋中放有 nums[i] 美元。
 * <br>
 * 另给你一个整数 k ，表示窃贼将会窃取的 最少 房屋数。小偷总能窃取至少 k 间房屋。
 * <br>
 * 返回小偷的 最小 窃取能力。
 * <h3>示例 1：</h3>
 * 输入：nums = [2,3,5,9], k = 2<br>
 * 输出：5<br>
 * 解释：<br>
 * 小偷窃取至少 2 间房屋，共有 3 种方式：<br>
 * - 窃取下标 0 和 2 处的房屋，窃取能力为 max(nums[0], nums[2]) = 5 。<br>
 * - 窃取下标 0 和 3 处的房屋，窃取能力为 max(nums[0], nums[3]) = 9 。<br>
 * - 窃取下标 1 和 3 处的房屋，窃取能力为 max(nums[1], nums[3]) = 9 。<br>
 * 因此，返回 min(5, 9, 9) = 5 。
 * <h3>示例 2：</h3>
 * 输入：nums = [2,7,9,3,1], k = 2<br>
 * 输出：2<br>
 * 解释：共有 7 种窃取方式。窃取能力最小的情况所对应的方式是窃取下标 0 和 4 处的房屋。返回 max(nums[0], nums[4]) = 2 。<br>
 * <h2>提示：</h2>
 * <li>1 <= nums.length <= 105</li>
 * <li>1 <= nums[i] <= 109</li>
 * <li>1 <= k <= (nums.length + 1)/2</li>
 * <h2>Tip</h2>
 * Tag:动态规划,子数组最大累加和,二分答案法,贪心<br>
 * 左老师讲解链接:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交时只用提交Solution,去掉static修饰
 */
public class t2560 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.minCapability(new int[]{2, 3, 5, 9}, 2));
        System.out.println(solution.minCapability(new int[]{2,7,9,3,1}, 2));
    }

    /**
     * 解题思路:
     * <ul>
     *     <li>首先，使用二分答案法，对小偷的能力二分</li>
     *     <li>然后，对于小偷能力的验证可以使用动态规划或者贪心</li>
     * </ul>
     */
    static class Solution {
        public int minCapability(int[] nums, int k) {
            int n = nums.length;
            int left = nums[0];
            int right= nums[0];
            // 求最大值和最小值
            for(int i=1;i<n;i++){
                left = Math.min(nums[i],left);
                right = Math.max(nums[i],right);
            }
            // 开始二分
            int middle;
            int ans = 0;
            while(left<=right){
                middle = (left+right)/2;
                if(robMost2(nums,middle)>=k){
                    // 可以的话
                    ans = middle;
                    right = middle-1;
                }else{
                    // 不可以的话
                    left = middle+1;
                }
            }
            return ans;
        }

        /**
         * 使用空间压缩的动态规划求小偷最多可以偷多少房子.<br>
         * <li>dp[i]定义:从0偷到i位置一共可以偷多少房子</li>
         * <li>递推关系:dp[i]=(dp[i-1],dp[i-2]+arr[i]<=ability?1:0)</li>
         * <li>边界条件:dp[0] = arr[0]<=ability?1:0;dp[1] = ((arr[0]<=ability||arr[1]<=ability)?1:0)</li>
         * @param arr 房子数组
         * @param ability 小偷的能力
         * @return 小偷最多可以偷多少房子
         */
        public int robMost1(int[] arr,int ability){
            int n =arr.length;
            if(n==1){
                return arr[0]<=ability?1:0;
            } else if (n==2) {
                return (arr[0]<=ability || arr[1]<=ability)?1:0;
            }
            // 初始条件
            int prepre = arr[0]<=ability?1:0;
            int pre = (arr[0]<=ability || arr[1]<=ability)?1:0;
            // 递推
            for(int i=2,cur;i<n;i++){
                cur = Math.max(pre,prepre+(arr[i]<=ability?1:0));
                prepre = pre;
                pre = cur;
            }
            return pre;
        }

        /**
         * 使用贪心优化算法,因为要的不是最大累加和，而是数量，所以小偷能偷就偷
         * @param arr 房子数组
         * @param ability 小偷能力
         * @return 小偷最多能偷多少房子
         */
        public int robMost2(int[] arr,int ability){
            int ans = 0;
            for(int i=0;i<arr.length;i++){
                if(arr[i]<=ability){
                    i++;
                    ans++;
                }
            }
            return ans;
        }
    }
}
