package Leetcode;

import java.util.Arrays;

/**
 * 链接:https://leetcode.cn/problems/count-submatrices-with-all-ones
 * <hr/>
 * <h1>1504. 统计全 1 子矩形</h1>
 * 给你一个 m x n 的二进制矩阵 mat ，请你返回有多少个 子矩形 的元素全部都是 1 。
 *
 * <h2>示例 1：</h2>
 * 输入：mat = [[1,0,1],[1,1,0],[1,1,0]]
 * 输出：13
 * 解释：
 * <ul>有 6 个 1x1 的矩形。</ul>
 * <ul>有 2 个 1x2 的矩形。</ul>
 * <ul>有 3 个 2x1 的矩形。</ul>
 * <ul>有 1 个 2x2 的矩形。</ul>
 * <ul>有 1 个 3x1 的矩形。</ul>
 * 矩形数目总共 = 6 + 2 + 3 + 1 + 1 = 13 。
 * <h2>示例 2：</h2>
 * 输入：mat = [[0,1,1,0],[0,1,1,1],[1,1,1,0]]
 * 输出：24
 * 解释：
 * <ul>有 8 个 1x1 的子矩形。</ul>
 * <ul>有 5 个 1x2 的子矩形。</ul>
 * <ul></ul>有 2 个 1x3 的子矩形。
 * <ul></ul>有 4 个 2x1 的子矩形。
 * <ul></ul>有 2 个 2x2 的子矩形。
 * <ul></ul>有 2 个 3x1 的子矩形。
 * <ul></ul>有 1 个 3x2 的子矩形。
 * 矩形数目总共 = 8 + 5 + 2 + 4 + 2 + 2 + 1 = 24 。
 * <h2>提示</h2>
 * 1 <= m, n <= 150
 * mat[i][j] 仅包含 0 或 1
 * <h2>Tip</h2>
 * Tag:单调栈
 * 左老师讲解视频:https://www.bilibili.com/video/BV1GH4y1D7TB
 * <h2>解题思路</h2>
 * <p>转换为简单的以一维问题，求直方图中底的矩形个数</p>
 * <p>大压小，遇到小的时候弹出，获得左右更小的数，相等的时候直接弹出，不计算</p>
 * <p>计算公式:(arr[cur]-max{arr[left],arr[i]})*(i-left-1)*（i-left)*0.5</p>
 */
public class t1504 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.numSubmat(new int[][]{
                {1, 0, 1},
                {1, 1, 0},
                {1, 1, 0}
        }));
        System.out.println(solution.numSubmat(new int[][]{
                {0, 1, 1, 0},
                {0, 1, 1, 1},
                {1, 1, 1, 0}
        }));
    }

    static class Solution {
        int MAX = 151;
        int[] stack = new int[MAX];
        int r = 0;

        int[] height = new int[MAX];
        int n = 0;
        public int numSubmat(int[][] mat) {
            n = mat[0].length;
            int ans = 0;
            Arrays.fill(height,0,n,0);
            for(int i=0;i<mat.length;i++){
                for(int j=0;j<n;j++){
                    height[j] = mat[i][j]==0?0:height[j]+1;
                }
                ans += numArray();
            }
            return ans;
        }

        public int numArray(){
            r = 0;
            int ans = 0;
            int left,cur,bottom;
            // 1.遍历阶段
            for(int i=0;i<n;i++){
                //尝试弹出
                while(r>0&&height[i]<=height[stack[r-1]]){
                    cur = stack[--r];
                    // 结算
                    if(height[i]!=height[cur]){

                        left = r>0?stack[r-1]:-1;
                        bottom = Math.max(height[i],left == -1?0:height[left]);
                        ans += (height[cur]-bottom)*(i-left-1)*(i-left)/2;
                    }
                }
                // 压入
                stack[r++] = i;
            }

            //2.清算阶段
            //尝试弹出
            while(r>0){
                cur = stack[--r];
                left = r>0?stack[r-1]:-1;
                bottom =left == -1?0:height[left];
                ans += (height[cur]-bottom)*(n-left-1)*(n-left)/2;
            }
            return ans;
        }
    }
}
