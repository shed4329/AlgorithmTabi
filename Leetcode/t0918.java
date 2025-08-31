package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/31  16:27
 * 链接:https://leetcode.cn/problems/maximum-sum-circular-subarray/
 * <h1>918. 环形子数组的最大和</h1>
 * 给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。
 * <br>
 * 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个元素是 nums[(i - 1 + n) % n] 。
 * <br>
 * 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。
 * <h3>示例 1：</h3>
 * 输入：nums = [1,-2,3,-2]<br>
 * 输出：3<br>
 * 解释：从子数组 [3] 得到最大和 3<br>
 * <h3>示例 2</h3>：
 * 输入：nums = [5,-3,5]<br>
 * 输出：10<br>
 * 解释：从子数组 [5,5] 得到最大和 5 + 5 = 10<br>
 * <h3>示例 3：</h3>
 * 输入：nums = [3,-2,2,-3]<br>
 * 输出：3<br>
 * 解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3<br>
 * <h2>提示：</h2>
 * <li>n == nums.length</li>
 * <li>1 <= n <= 3 * 104</li>
 * <li>-3 * 104 <= nums[i] <= 3 * 104</li>
 * <h2>Tip</h2>
 * Tag:DP,子数组累加和<br>
 * 左老师讲解链接:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交solution类，去掉static修饰
 */
public class t0918 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.maxSubarraySumCircular(new int[]{1, -2, 3, -2}));
//        System.out.println(solution.maxSubarraySumCircular(new int[]{5,-3,5}));
        System.out.println(solution.maxSubarraySumCircular(new int[]{3,-2,2,-3}));
    }

    static class Solution {
        /**
         * 分类讨论:
         * <li>在不跨越界限的max</li>
         * <li>跨越界限的max</li>
         * <li>跨越界限时，max可以转换为all-min</li>
         * @param nums
         * @return
         */
        public int maxSubarraySumCircular(int[] nums) {
            int n = nums.length;
            int maxSum = nums[0],minSum = nums[0];
            int maxPre = nums[0], minPre = nums[0];
            int all = nums[0];
            for(int i=1;i<n;i++){
                all += nums[i];
                maxPre = Math.max(nums[i],maxPre+nums[i]);
                maxSum = Math.max(maxSum,maxPre);
                minPre = Math.min(nums[i],minPre +nums[i]);
                minSum = Math.min(minPre,minSum);
            }
            // 例外:排除把所有的给扣掉
            return all== minSum ?maxSum:Math.max(maxSum,all- minSum);
        }
    }
}
