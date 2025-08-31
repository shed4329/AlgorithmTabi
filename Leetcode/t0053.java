package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/31  14:44
 * <hr>
 * 链接:https://leetcode.cn/problems/maximum-subarray/
 * <h1>53. 最大子数组和</h1>
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <br>
 * 子数组是数组中的一个连续部分。
 * <h3>示例 1：</h3>
 * <br>
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]<br>
 * 输出：6<br>
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。<br>
 * <h3>示例 2：</h3>
 * <br>
 * 输入：nums = [1]<br>
 * 输出：1<br>
 * <h3>示例 3：</h3>
 * <br>
 * 输入：nums = [5,4,-1,7,8]<br>
 * 输出：23<br>
 * <h2>提示：</h2>
 * <br>
 * 1 <= nums.length <= 105<br>
 * -104 <= nums[i] <= 104
 * <br>
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 * <h2>Tip</h2>
 * Tag:动态规划<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交的时候交Solution类，去掉static，还有方法名要改一下
 */
public class t0053 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxSubArray1(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(solution.maxSubArray1(new int[]{1}));
        System.out.println(solution.maxSubArray1(new int[]{5,4,-1,7,8}));

        System.out.println(solution.maxSubArray2(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
        System.out.println(solution.maxSubArray2(new int[]{1}));
        System.out.println(solution.maxSubArray2(new int[]{5,4,-1,7,8}));
    }

    static class Solution {
        /**
         * 使用普通DP
         * <li>递推关系:dp[i]=max(dp[i-1]+arr[i],arr[i])</li>
         * <li>边界条件:dp[0]=arr[0]</li>
         * @param arr 数据
         * @return 最大累加和
         */
        public int maxSubArray1(int[] arr) {
            int n = arr.length;
            int ans = arr[0];
            int[] dp = new int[n];
            dp[0] = arr[0];

            for(int i=1;i<n;i++){
                dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);
                ans = Math.max(dp[i],ans);
            }

            return ans;
        }

        /**
         * 空间压缩之后的版本
         * @param arr 数据
         * @return 最大子数组累加和
         */
        public int maxSubArray2(int[] arr) {
            int n = arr.length;
            int pre = arr[0];
            int ans = pre;

            for(int i=1;i<n;i++){
                pre = Math.max(pre+arr[i],arr[i]);
                ans = Math.max(ans,pre);
            }

            return ans;
        }
    }
}
