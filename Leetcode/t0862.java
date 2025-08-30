package Leetcode;

/**
 * @Author：shed4329
 * @Date：2025/8/30 15:12
 * <hr/>
 * 链接:https://leetcode.cn/problems/shortest-subarray-with-sum-at-least-k/
 * <h1>862. 和至少为 K 的最短子数组</h1>
 * 给你一个整数数组 nums 和一个整数 k ，找出 nums 中和至少为 k 的 最短非空子数组 ，并返回该子数组的长度。如果不存在这样的 子数组 ，返回 -1 。
 * <br>
 * 子数组 是数组中 连续 的一部分。<br>
 * <h2>示例 1：</h2>
 * 输入：nums = [1], k = 1<br>
 * 输出：1
 * <h2>示例 2：</h2>
 * 输入：nums = [1,2], k = 4<br>
 * 输出：-1
 * <h2>示例 3：</h2>
 * 输入：nums = [2,-1,2], k = 3<br>
 * 输出：3
 *
 * <h2>提示：</h2>
 * 1 <= nums.length <= 105<br>
 * -105 <= nums[i] <= 105<br>
 * 1 <= k <= 109
 * <h2>Tip</h2>
 * Tag:单调队列<br>
 * 左老师讲解链接:https://www.bilibili.com/video/BV1y8411i7cY<br>
 * 提交的时候只用提交Solution，去掉static修饰
 */
public class t0862 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestSubarray(new int[]{1}, 1));
        System.out.println(solution.shortestSubarray(new int[]{1,2}, 4));
        System.out.println(solution.shortestSubarray(new int[]{2,-1,2}, 3));
        System.out.println(solution.shortestSubarray(new int[]{1,4,5,2,-1,-2,9,2,8,-2}, 10));
        System.out.println(solution.shortestSubarray(new int[]{84,-37,32,40,95}, 167));
    }

    /**
     * 解题思路:
     * <ul>计算前缀和加速计算</ul>
     * <ul>维护单调队列，保持小到大的顺序</ul>
     * <ul>弹出规则:1.首尾相差满足条件 2.不符合单调顺序</ul>
     */
    static class Solution {
        // 数量最大值
        int MAX = 100001;
        // 单调队列
        int[] deque = new int[MAX];
        // 队列的头尾
        int t,h;
        // 前缀和数组,[0]->前0个数的前缀和;[1]->前1个数的前缀和
        long[] sum = new long[MAX];
        public int shortestSubarray(int[] arr, int k) {
            h = t = 0;
            int n = arr.length;
            int ans = Integer.MAX_VALUE;
            // 1.前缀和
            for(int i=0;i<n;i++){
                sum[i+1] = sum[i]+arr[i];
            }
            // 2.遍历，维护队列
            for(int i = 0;i<=n;i++){
                // 第0/1/2/3/4/5...个数的前缀和是否符合条件
                // 1.首尾符合条件,和大于k,eg.index =  1,2 = sum[3] - sum[1]
                while(t>h&&sum[i]-sum[deque[h]]>=k){
                    // 记录答案，弹出
                    ans = Math.min(ans,i-deque[h++]);
                }
                // 2.坚持是否维护了先小后大的单调顺序
                while(t>h&&sum[deque[t-1]]>=sum[i]){
                    // 弹出
                    t--;
                }
                // 加入队列
                deque[t++] = i;
            }

            // 返回值
            return ans == Integer.MAX_VALUE ? -1:ans;
        }
    }
}
