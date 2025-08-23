package Leetcode;

/**
 * 链接:https://leetcode.cn/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/description/
 * <h1>1438. 绝对差不超过限制的最长连续子数组</h1>
 * 给你一个整数数组 nums ，和一个表示限制的整数 limit，请你返回最长连续子数组的长度，该子数组中的任意两个元素之间的绝对差必须小于或者等于 limit。
 * <h2>示例 1：</h2>
 * 输入：nums = [8,2,4,7], limit = 4<br>
 * 输出：2 <br>
 * 解释：所有子数组如下：<br>
 * [8] 最大绝对差 |8-8| = 0 <= 4.<br>
 * [8,2] 最大绝对差 |8-2| = 6 > 4. <br>
 * [8,2,4] 最大绝对差 |8-2| = 6 > 4.<br>
 * [8,2,4,7] 最大绝对差 |8-2| = 6 > 4.<br>
 * [2] 最大绝对差 |2-2| = 0 <= 4.<br>
 * [2,4] 最大绝对差 |2-4| = 2 <= 4.<br>
 * [2,4,7] 最大绝对差 |2-7| = 5 > 4.<br>
 * [4] 最大绝对差 |4-4| = 0 <= 4.<br>
 * [4,7] 最大绝对差 |4-7| = 3 <= 4.<br>
 * [7] 最大绝对差 |7-7| = 0 <= 4. <br>
 * 因此，满足题意的最长子数组的长度为 2 。
 * <h2>示例 2：</h2>
 * 输入：nums = [10,1,2,4,7,2], limit = 5<br>
 * 输出：4 <br>
 * 解释：满足题意的最长子数组是 [2,4,7,2]，其最大绝对差 |2-7| = 5 <= 5 。
 * <h2>示例 3：</h2>
 * 输入：nums = [4,2,2,2,4,4,2,2], limit = 0<br>
 * 输出：3
 * <h2>提示：</h2>
 * <ul>1 <= nums.length <= 105</ul>
 * <ul>1 <= nums[i] <= 109</ul>
 * <ul>0 <= limit <= 109</ul>
 * <h2>Tip</h2>
 * Tag:单调队列<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV11h4y1w7Bu
 * <h2>解题思路</h2>
 * <ul>首先,窗口越大越容易超出界限</ul>
 * <ul>而可以通过维护两个单调栈来判断是否超出limit</ul>
 * <ul>从而控制窗口的扩张和收缩</ul>
 */
public class t1438 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestSubarray(new int[]{8, 2, 4, 7}, 4));
        System.out.println(solution.longestSubarray(new int[]{10,1,2,4,7,2}, 5));
        System.out.println(solution.longestSubarray(new int[]{4,2,2,2,4,4,2,2}, 0));
    }

    static class Solution {
        int MAX = 100001;
        int[] maxDeque = new int[MAX];
        //maxDeque的头尾
        int maxh,maxt;
        // 最小值的Deque
        int[] minDeque = new int[MAX];
        int minh,mint;
        // 输入数组，懒得传参了
        int[] arr;
        public int longestSubarray(int[] nums, int limit) {
            maxh=minh=maxt=mint=0;
            arr = nums;
            int left=0;
            int right=0;
            int n = nums.length;
            int ans = 0;

            for(;left<n;left++){
                // 贪心，可以吸入多少吸入多少
                while(right<n&&ok(right,limit)){
                    push(right++);
                }
                ans = Math.max(ans,right-left);
                pop(left);
            }
            return ans;
        }

        /**
         * 判断某个位置是否应该进入deque
         * @param index 位置
         * @param limit 限制
         * @return True = ok
         */
        public boolean ok(int index,int limit){
            int max = maxh==maxt?arr[index]:Math.max(arr[index],arr[maxDeque[maxh]]);
            int min = minh==mint?arr[index]:Math.min(arr[index],arr[minDeque[minh]]);
            return max-min<=limit;
        }

        /**
         * 把某个数字压入大Deque和小Deque
         * @param index 这个数字的下标
         */
        public void push(int index){
            // 尝试弹出最大Deque,大到小
            while(maxt>maxh&&arr[index]>=arr[maxDeque[maxt-1]]){
                //弹出
                maxt--;
            }
            //压入
            maxDeque[maxt++] = index;
            // 尝试弹出最小Deque,小到大
            while(mint>minh&&arr[index]<=arr[minDeque[mint-1]]){
                mint--;
            }
            minDeque[mint++] = index;
        }

        /**
         * 弹出
         * @param index 弹出时的下标
         */
        public void pop(int index){
            if(maxt>maxh&&maxDeque[maxh]==index){
                maxh++;
            }
            if(mint>minh&&minDeque[minh]==index){
                minh++;
            }
        }


    }
}
