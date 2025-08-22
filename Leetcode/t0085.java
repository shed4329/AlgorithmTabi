package Leetcode;

import java.util.Arrays;

/**
 * 链接:https://leetcode.cn/problems/maximal-rectangle
 * <hr/>
 * 题目描述:
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * <hr/>
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 3：
 * 输入：matrix = [["1"]]
 * 输出：1
 * <hr/>
 * 提示：
 * rows == matrix.length
 * cols == matrix[0].length
 * 1 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 * <hr/>
 * Tag:单调栈
 * 提交时交Solution类，不要static修饰
 * <hr/>
 * 左老师讲解视频：https://www.bilibili.com/video/BV1HH4y1X7T9
 */
public class t0085 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximalRectangle(new char[][]{
                "10100".toCharArray(),
                "10111".toCharArray(),
                "11111".toCharArray(),
                "10010".toCharArray()
        }));
        System.out.println(solution.maximalRectangle(new char[][]{
                {'0'}
        }));
        System.out.println(solution.maximalRectangle(new char[][]{
                {'1'}
        }));
    }

    static class Solution{
        // 最大值
        private int MAX = 201;
        // 栈
        private int[] stack = new int[MAX];
        // 栈的右侧位置
        private int r = 0;
        // 压缩后的数组
        private int[] arr = new int[MAX];
        // 矩阵的长
        private int m = 0;

        /**
         * 对之前的方法做个小小的包装，对每一行使用数组压缩，求以某一行为底的矩形的最大值
         * @param matrix 矩阵
         * @return 最大值
         */
        public int maximalRectangle(char[][] matrix) {
            int n = matrix.length;
            m = matrix[0].length;
            Arrays.fill(arr,0,m,0);
            int ans = 0;

            for(int i=0;i<n;i++){
                // 数组压缩
                for(int j=0;j<m;j++){
                    if (matrix[i][j] == '0'){
                        arr[j] = 0;
                    }else{
                        arr[j]++;
                    }
                }
                ans = Math.max(maxAreaOfALine(),ans);
            }

            return ans;
        }

        /**
         * 其实这个直接把0084的方法拿过来小改一下就可以了
         * @return 数组中的最大矩形面积
         */
        private int maxAreaOfALine(){
            int ans = 0;
            int cur,left;
            // 1.遍历阶段
            for(int i=0;i<m;i++){
                //尝试弹出,弹出应该是遇到比自己高度小的时候
                while(r>0&&arr[i]<=arr[stack[r-1]]){
                    cur = stack[--r];
                    left = r>0?stack[r-1]:-1;
                    ans = Math.max(ans,(i-left-1)*arr[cur]);
                }
                stack[r++] = i;
            }
            // 2.清算阶段
            while(r>0){
                cur = stack[--r];
                left = r>0?stack[r-1]:-1;
                ans = Math.max(ans,(m-left-1)*arr[cur]);
            }

            return ans;
        }
    }
}
