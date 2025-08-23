package Leetcode;

/**
 * 这道题和newcoder的hor4，大鱼吃小鱼相似，去看那个吧
 */
public class t2289 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.totalSteps(new int[]{5, 3, 4, 4, 7, 3, 6, 11, 8, 5, 11}));
        System.out.println(solution.totalSteps(new int[]{4,5,7,7,13}));
    }

    static class Solution {
        int MAX = 100001;
        int r = 0;
        int[][] stack = new int[MAX][2];
        public int totalSteps(int[] arr) {
            r = 0;
            int n = arr.length;
            int ans = 0;
            // 压栈
            for(int i=n-1,curTurns;i>=0;i--){
                curTurns = 0;
                // 尝试弹出
                while (r>0&&arr[i]>arr[stack[r-1][0]]){
                    // 弹出和更新
                    curTurns = Math.max(curTurns+1,stack[--r][1]);
                }
                ans = Math.max(ans,curTurns);
                //压入栈
                stack[r][0] = i;
                stack[r++][1] = curTurns;
            }
            return ans;
        }
    }
}
