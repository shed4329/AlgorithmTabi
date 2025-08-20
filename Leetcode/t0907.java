package Leetcode;

/**
 *  链接:https://leetcode.cn/problems/sum-of-subarray-minimums/
 *
 *  907. 子数组的最小值之和
 *
 *  给定一个整数数组 arr，找到 min(b) 的总和，其中 b 的范围为 arr 的每个（连续）子数组。
 *
 * 由于答案可能很大，因此 返回答案模 10^9 + 7 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,1,2,4]
 * 输出：17
 * 解释：
 * 子数组为 [3]，[1]，[2]，[4]，[3,1]，[1,2]，[2,4]，[3,1,2]，[1,2,4]，[3,1,2,4]。
 * 最小值为 3，1，2，4，1，1，2，1，1，1，和为 17。
 * 示例 2：
 *
 * 输入：arr = [11,81,94,43,3]
 * 输出：444
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 3 * 104
 * 1 <= arr[i] <= 3 * 104
 *
 * 左老师讲解链接:https://www.bilibili.com/video/BV1HH4y1X7T9
 *
 */
public class t0907 {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.sumSubarrayMins(new int[]{3,1,2,4}));
        System.out.println(s.sumSubarrayMins(new int[]{11,81,94,43,3}));
    }

    static class Solution {
        // 取模
        int MOD = 1000000007;
        // 最大长度
        int MAX = 30001;
        // 栈
        int[] stack = new int[MAX];
        // 栈大小
        int r = 0;
        public int sumSubarrayMins(int[] arr) {
            int n = arr.length;
            long ans = 0;
            r = 0;

            int cur,left;
            // 遍历阶段
            for(int i=0;i<n;i++){
                //尝试压入，这个求子数组最小值，要找到两边小的值，所以是大压小，小的时候弹出,相等也弹出
                while(r>0&&arr[i]<=arr[stack[r-1]]){
                    cur = stack[--r];
                    left = r>0?stack[r-1]:-1;
                    ans = (ans + (long)(cur-left)*(i-cur)*arr[cur])%MOD;
                }
                stack[r++] = i;
            }

            // 弹出阶段，右侧固定为n
            while(r>0){
                cur = stack[--r];
                left = r>0?stack[r-1]:-1;
                ans = (ans + (long)(cur-left)*(n-cur)*arr[cur])%MOD;
            }

            return (int)ans;
        }
    }
}


