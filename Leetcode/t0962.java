package Leetcode;

/**
 * 链接:https://leetcode.cn/problems/maximum-width-ramp
 * <hr/>
 * 962. 最大宽度坡
 * 给定一个整数数组 A，坡是元组 (i, j)，其中  i < j 且 A[i] <= A[j]。这样的坡的宽度为 j - i。
 * 找出 A 中的坡的最大宽度，如果不存在，返回 0 。
 *<hr/>
 * 示例 1：
 * 输入：[6,0,8,2,1,5]
 * 输出：4
 * 解释：
 * 最大宽度的坡为 (i, j) = (1, 5): A[1] = 0 且 A[5] = 5.
 * 示例 2：
 * 输入：[9,8,1,0,1,9,4,0,4,1]
 * 输出：7
 * 解释：
 * 最大宽度的坡为 (i, j) = (2, 9): A[2] = 1 且 A[9] = 1.
 *<hr/>
 * 提示：
 * 2 <= A.length <= 50000
 * 0 <= A[i] <= 50000
 * <hr/>
 * Tag:单调栈
 * 提交时只提交Solution类,去除static修饰
 * <hr/>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1GH4y1D7TB
 */
public class t0962 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}));
        System.out.println(solution.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}));
    }

    static class Solution{
        int MAX = 50001;
        int[] stack = new int[MAX];
        // 默认0下标是压入的，所以在压入0（初始化的时候就是0）的时候，直接把下标设置到1
        int r = 1;

        /**
         * 解答思路:
         * 1.首先从左到右压入栈，只压入比栈顶小的元素
         * 2.然后从右往左遍历，如果可以和栈里的元素构成坡，那么弹出元素
         * @param arr 数组
         * @return 最大坡宽度
         */
        public int maxWidthRamp(int[] arr) {
            int n = arr.length;
            r = 1;

            //Step1.遍历压入栈，相等的时候不压入
            for(int i=1;i<n;i++){
                if(arr[stack[r-1]]>arr[i]){
                    // 压入栈
                    stack[r++] = i;
                }
            }
            int ans = 0;
            //step2.弹出栈，得到答案
            for(int i=n-1;i>=0;i--){
                // 弹出中
                while(r>0&&arr[i]>=arr[stack[r-1]]){
                    ans = Math.max(ans,i-stack[--r]);
                }
            }
            return ans;
        }
    }
}
