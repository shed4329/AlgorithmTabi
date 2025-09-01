package Leetcode;

import java.util.Arrays;

/**
 * @author shed4329
 * @date 2025/9/1  11:00
 * 链接:https://leetcode.cn/problems/max-submatrix-lcci/
 * <h1>面试题 17.24. 最大子矩阵</h1>
 * 给定一个正整数、负整数和 0 组成的 N × M 矩阵，编写代码找出元素总和最大的子矩阵。
 * <br>
 * 返回一个数组 [r1, c1, r2, c2]，其中 r1, c1 分别代表子矩阵左上角的行号和列号，r2, c2 分别代表右下角的行号和列号。若有多个满足条件的子矩阵，返回任意一个均可。
 * <br>
 * 注意：本题相对书上原题稍作改动
 * <h2>示例：</h2>
 * 输入：<br>
 * [<br>
 *    [-1,0],<br>
 *    [0,-1]<br>
 * ]<br>
 * 输出：[0,1,0,1]<br>
 * 解释：输入中标粗的元素即为输出所表示的矩阵
 * <h2>说明：</h2>
 * 1 <= matrix.length, matrix[0].length <= 200
 * <h2>Tip</h2>
 * Tag:动态压缩，最大子数组累加和，数组压缩<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1pw411M7Du<br>
 * 提交只用交Solution类，去掉static修饰
 */
public class max_submatrix_lcci {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.getMaxMatrix(new int[][]{
                {-1, 0},
                {0, -1}
        })));
    }

    /**
     * 解题思路:<br>
     * <ul>
     *     <li>首先，利用数组压缩,将问题转换为0-0,0-1,0-2,...1-1,1-2的累加和</li>
     *     <li>然后再用之前的动态规划求一维数组的最大累加和</li>
     * </ul>
     */
    static class Solution {
        public int[] getMaxMatrix(int[][] matrix) {
            // 最大累加和
            int sum = Integer.MIN_VALUE;
            // (a,b)->(c,d)
            int a=0,b=0,c=0,d=0;
            // n*m的矩阵
            int n = matrix.length;
            int m = matrix[0].length;
            // 用于压缩数组的数组
            int[] arr = new int[m];
            for(int up = 0;up<n;up++){
                Arrays.fill(arr,0);
                for(int down = up;down<n;down++){
                    // 从up到down的累加和
                    // 跑累加和，dp[i] = max(arr[i],arr[i]+dp[i-1])
                    int pre = Integer.MIN_VALUE;
                    for(int left=0,right=0;right<m;right++){
                        arr[right] += matrix[down][right];
                        if(pre<0){
                            // 不要前面的了
                            left = right;
                            pre = arr[right];
                        }else{
                            pre += arr[right];
                        }

                        if(pre > sum){
                            sum = pre;
                            a = up;
                            b = left;
                            c = down;
                            d = right;
                        }
                    }
                }
            }
            return new int[]{a,b,c,d};
        }
    }
}
