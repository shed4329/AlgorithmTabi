package Leetcode;

/**
 * 链接:https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * </hr>
 * 84. 柱状图中最大的矩形
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 *</hr>
 * 示例 1:
 *</p>
 * 输入：heights = [2,1,5,6,2,3]
 * 输出：10
 * 解释：最大的矩形为图中红色区域，面积为 10
 * <p>
 * 示例 2：
 * <p>
 * 输入： heights = [2,4]
 * 输出： 4
 * </hr>
 * 提示：
 * <p>
 * 1 <= heights.length <=105
 * 0 <= heights[i] <= 104
 * </hr>
 * 提交时只用提交solution类，不要static修饰
 * </hr>
 * 左老师讲解：https://www.bilibili.com/video/BV1HH4y1X7T9
 */
public class t0084 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.largestRectangleArea(new int[]{2,1,5,6,2,3}));
        System.out.println(s.largestRectangleArea(new int[]{2,4}));
    }
    static class Solution {
        int MAX = 100001;
        int[] stack = new int[MAX];
        int r;
        public int largestRectangleArea(int[] heights) {
            r = 0;
            int n = heights.length;
            int ans = 0;
            int cur,left;
            //遍历
            for(int i=0;i<n;i++){
                // 当遇到更小的值的时候弹出，所以是大压小,等于的时候也弹出，反正只要一个最大值
                while(r>0&&heights[i]<=heights[stack[r-1]]){
                    cur = stack[--r];
                    left = r>0?stack[r-1]:-1;
                    ans = Math.max(ans,(i-left-1)*heights[cur]);
                }
                // 压入
                stack[r++] = i;
            }
            //清算
            while(r>0){
                cur = stack[--r];
                left = r>0?stack[r-1]:-1;
                ans = Math.max(ans,(n-left-1)*heights[cur]);
            }
            return ans;
        }
    }
}


