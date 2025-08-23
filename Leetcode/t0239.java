package Leetcode;

import java.util.Arrays;

/**
 * 链接:https://leetcode.cn/problems/sliding-window-maximum
 * <h1>239. 滑动窗口最大值</h1>
 * <p>给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <br>
 * 返回 滑动窗口中的最大值 。</p>
 * <h2>示例 1：</h2>
 *<p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3<br>
 * 输出：[3,3,5,5,6,7]<br>
 * 解释：<br>
 * 滑动窗口的位置                最大值<br>
 * ---------------               -----<br>
 * [1  3  -1] -3  5  3  6  7       3<br>
 *  1 [3  -1  -3] 5  3  6  7       3<br>
 *  1  3 [-1  -3  5] 3  6  7       5<br>
 *  1  3  -1 [-3  5  3] 6  7       5<br>
 *  1  3  -1  -3 [5  3  6] 7       6<br>
 *  1  3  -1  -3  5 [3  6  7]      7<br>
 *  </p>
 * <h2>示例 2:</h2>
 * 输入：nums = [1], k = 1<br>
 * 输出：[1]
 * <h2>提示：</h2>
 * <ul>1 <= nums.length <= 105</ul>
 * <ul>-104 <= nums[i] <= 104</ul>
 * <ul>1 <= k <= nums.length</ul>
 *<h2>Tip</h2>
 * <p>Tag：单调队列<br>
 * 左老师视频:https://www.bilibili.com/video/BV11h4y1w7Bu
 * </p>
 */

public class t0239 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
        System.out.println(Arrays.toString(solution.maxSlidingWindow(new int[]{1}, 1)));
    }
    static class Solution {
        int MAX = 100001;
        int[] deque = new int[MAX];
        // h=head,t=tail，deque的头尾
        int h,t;
        // 滑动窗口位置[l,r)
        int l,r;

        public int[] maxSlidingWindow(int[] arr, int k) {
            h=t=0;
            l=0;
            r=k-1;
            // 先压入k-1个数字
            for(int i=0;i<k-1;i++){
                // 压入前弹出,从大到小
                while(h<t&&arr[i]>=arr[deque[t-1]]){
                    // 弹出
                    t--;
                }
                deque[t++] = i;
            }

            // 计算要遍历的次数
            int times = arr.length - k + 1;
            int[] ans = new int[times];
            for(int i=0;i<times;i++,l++,r++){
                // 先压入一个
                // 弹出检查
                while(h<t&&arr[r]>=arr[deque[t-1]]){
                    // 弹出
                    t--;
                }
                deque[t++] = r;
                // 最大值
                ans[i] = arr[deque[h]];
                //l++检查
                if (deque[h] == l){
                    h++;
                }
            }
            return ans;
        }
    }
}
