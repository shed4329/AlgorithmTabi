package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/31  15:25
 * 链接:https://leetcode.cn/problems/house-robber/
 * <h1>198. 打家劫舍</h1>
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <br>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <h3>示例 1：</h3>
 * <br>
 * 输入：[1,2,3,1]<br>
 * 输出：4<br>
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。<br>
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * <h3>示例 2：</h3>
 * 输入：[2,7,9,3,1]<br>
 * 输出：12<br>
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。<br>
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <h2>提示：</h2>
 * <li>1 <= nums.length <= 100</li>
 * <li>0 <= nums[i] <= 400</li>
 * <h2>Tip</h2>
 * Tag:动态规划<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交的时候只用提交Solution类，去掉static修饰，还要改一下方法名
 */
public class t0198 {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.rob1(new int[]{1, 2, 3, 1}));
        System.out.println(solution.rob1(new int[]{1, 3, 1}));
        System.out.println(solution.rob1(new int[]{2,7,9,3,1}));

        System.out.println(solution.rob2(new int[]{1, 2, 3, 1}));
        System.out.println(solution.rob2(new int[]{2,7,9,3,1}));
    }

    static class Solution {
        /**
         * 普通DP<br>
         * 解题思路:<br>
         * 当前房子只有偷和不偷两种情况，可以把DP[i]定义为[0...i]上的最佳收益
         * <li>递推条件:dp[i]=max(dp[i-2]+arr[i],dp[i-1])</li>
         * <li>边界条件:dp[0]=arr[0],dp[1]=max(arr[0],arr[1])</li>
         * @param nums
         * @return
         */
        public int rob1(int[] nums) {
            int n = nums.length;
            if(n==1){
                return nums[0];
            }

            if(n==2){
                return Math.max(nums[0],nums[1]);
            }

            int[] dp = new int[n];
            dp[0] = nums[0];
            dp[1] = Math.max(dp[0],nums[1]);
            for(int i=2;i<n;i++){
                dp[i] = Math.max(dp[i-1],dp[i-2]+nums[i]);
            }

            return dp[n-1];
        }

        /**
         * 状态压缩的DP
         * @param arr 数据
         * @return 最佳收益
         */
        public int rob2(int[] arr){
            int n = arr.length;
            if(n==1){
                return arr[0];
            }

            if(n==2){
                return Math.max(arr[0],arr[1]);
            }

            int prepre = arr[0];
            int pre = Math.max(arr[0],arr[1]);

            for(int i=2,cur;i<n;i++){
                cur = Math.max(prepre+arr[i],pre);
                prepre = pre;
                pre = cur;
            }

            return pre;
        }
    }
}
