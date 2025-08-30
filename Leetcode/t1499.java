package Leetcode;

/**
 * @author shed4329
 * @date 2025/8/30 16:23
 * <hr/>
 * 链接:<a href="https://leetcode.cn/problems/max-value-of-equation/">Leetcode</a>
 * <h1>1499. 满足不等式的最大值</h1>
 * 给你一个数组 points 和一个整数 k 。数组中每个元素都表示二维平面上的点的坐标，并按照横坐标 x 的值从小到大排序。也就是说 points[i] = [xi, yi] ，并且在 1 <= i < j <= points.length 的前提下， xi < xj 总成立。
 * <br>
 * 请你找出 yi + yj + |xi - xj| 的 最大值，其中 |xi - xj| <= k 且 1 <= i < j <= points.length。
 * <br>
 * 题目测试数据保证至少存在一对能够满足 |xi - xj| <= k 的点。
 * <br>
 * <h3>示例 1</h3>：
 * 输入：points = [[1,3],[2,0],[5,10],[6,-10]], k = 1<br>
 * 输出：4<br>
 * 解释：前两个点满足 |xi - xj| <= 1 ，代入方程计算，则得到值 3 + 0 + |1 - 2| = 4 。第三个和第四个点也满足条件，得到值 10 + -10 + |5 - 6| = 1 。<br>
 * 没有其他满足条件的点，所以返回 4 和 1 中最大的那个。
 * <h3>示例 2</h3>：
 * 输入：points = [[0,0],[3,0],[9,2]], k = 3<br>
 * 输出：3<br>
 * 解释：只有前两个点满足 |xi - xj| <= 3 ，代入方程后得到值 0 + 0 + |0 - 3| = 3 。
 * <h2>提示：</h2>
 * <ul>2 <= points.length <= 10^5</ul>
 * <ul>points[i].length == 2</ul>
 * <ul>-10^8 <= points[i][0], points[i][1] <= 10^8</ul>
 * <ul>0 <= k <= 2 * 10^8</ul>
 * <ul>对于所有的1 <= i < j <= points.length ，points[i][0] < points[j][0] 都成立。也就是说，xi 是严格递增的。</ul>
 *
 * <h2>Tip</h2>
 * <ul>Tag:单调队列</ul>
 * <ul><a href="https://www.bilibili.com/video/BV1y8411i7cY">左老师视频</a></ul>
 * <ul>提交solution类，去掉static修饰</ul>
 */
public class t1499 {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.findMaxValueOfEquation(new int[][]{
//                {1, 3},
//                {2, 0},
//                {5, 10},
//                {6, -10}
//        }, 1));
//        System.out.println(solution.findMaxValueOfEquation(new int[][]{
//                {0,0},
//                {3,0},
//                {9,2}
//        }, 3));
//        System.out.println(solution.findMaxValueOfEquation(new int[][]{
//                {-19,9},
//                {-15,-19},
//                {-5,-8}
//        }, 10));
        System.out.println(solution.findMaxValueOfEquation(new int[][]
                {{-19,-12},{-13,-18},{-12,18},{-11,-8},{-8,2},{-7,12},{-5,16},{-3,9},{1,-7},{5,-4},{6,-20},{10,4},{16,4},{19,-9},{20,19}},
                6));
    }

    /**
     * 解题思路:<br>
     * 维护单调队列，大小排序:y-x,大到小,并且提出无效的数字
     */
    static class Solution {
        int MAX = 100001;
        // [][0]->x    [][1]->y
        int[][] deque = new int[MAX][2];
        // 头尾
        int t,h;

        public int findMaxValueOfEquation(int[][] points, int k) {
            int ans = Integer.MIN_VALUE;
            t = h = 0;
            int n = points.length;

            for(int i=0;i<n;i++){
                // 剔除无效的头数字
                while(h<t&&points[i][0]-deque[h][0]>k){
                    h++;
                }
                // 记录答案
                if(h<t){
                    ans = Math.max(ans,points[i][0]+points[i][1]+deque[h][1]-deque[h][0]);
                }

                // 压入数据
                while(h<t&&deque[t-1][1]-deque[t-1][0]<=points[i][1]-points[i][0]){
                    // 弹出
                    t--;
                }
                deque[t][0] = points[i][0];
                deque[t++][1] = points[i][1];
            }

            return ans;
        }
    }
}
