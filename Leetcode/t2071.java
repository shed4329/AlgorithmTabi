package Leetcode;

import java.util.Arrays;

/**
 * @author shed4329
 * @date 2025/8/30  19:32
 * <hr/>
 * 链接:https://leetcode.cn/problems/maximum-number-of-tasks-you-can-assign/description/
 * <h1>2071. 你可以安排的最多任务数目</h1>
 * 给你 n 个任务和 m 个工人。每个任务需要一定的力量值才能完成，需要的力量值保存在下标从 0 开始的整数数组 tasks 中，第 i 个任务需要 tasks[i] 的力量才能完成。每个工人的力量值保存在下标从 0 开始的整数数组 workers 中，第 j 个工人的力量值为 workers[j] 。每个工人只能完成 一个 任务，且力量值需要 大于等于 该任务的力量要求值（即 workers[j] >= tasks[i] ）。
 * <br>
 * 除此以外，你还有 pills 个神奇药丸，可以给 一个工人的力量值 增加 strength 。你可以决定给哪些工人使用药丸，但每个工人 最多 只能使用 一片 药丸。
 * <br>
 * 给你下标从 0 开始的整数数组tasks 和 workers 以及两个整数 pills 和 strength ，请你返回 最多 有多少个任务可以被完成。
 * <br>
 * <h3>示例 1：</h3>
 * 输入：tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1<br>
 * 输出：3<br>
 * 解释：<br>
 * 我们可以按照如下方案安排药丸：<br>
 * <ul>给 0 号工人药丸。</ul>
 * <ul>0 号工人完成任务 2（0 + 1 >= 1）</ul>
 * <ul>1 号工人完成任务 1（3 >= 2）</ul>
 * <ul>2 号工人完成任务 0（3 >= 3）</ul>
 * <h3>示例 2：</h3>
 * 输入：tasks = [5,4], workers = [0,0,0], pills = 1, strength = 5<br>
 * 输出：1<br>
 * 解释：<br>
 * 我们可以按照如下方案安排药丸：<br>
 * <ul>给 0 号工人药丸。</ul>
 * <ul>0 号工人完成任务 0（0 + 5 >= 5）</ul>
 * <h3>示例 3：</h3>
 * 输入：tasks = [10,15,30], workers = [0,10,10,10,10], pills = 3, strength = 10<br>
 * 输出：2<br>
 * 解释：<br>
 * 我们可以按照如下方案安排药丸：<br>
 * <ul>给 0 号和 1 号工人药丸。</ul>
 * <ul>0 号工人完成任务 0（0 + 10 >= 10）</ul>
 * <ul>1 号工人完成任务 1（10 + 10 >= 15）</ul>
 * <h3>示例 4：</h3>
 * 输入：tasks = [5,9,8,5,9], workers = [1,6,4,2,6], pills = 1, strength = 5<br>
 * 输出：3<br>
 * 解释：<br>
 * 我们可以按照如下方案安排药丸：<br>
 * <ul>给 2 号工人药丸。</ul>
 * <ul>1 号工人完成任务 0（6 >= 5）</ul>
 * <ul>2 号工人完成任务 2（4 + 5 >= 8）</ul>
 * <ul>4 号工人完成任务 3（6 >= 5）</ul>
 * <h2>提示：</h2>
 * <ul>n == tasks.length</ul>
 * <ul>m == workers.length</ul>
 * <ul>1 <= n, m <= 5 * 104</ul>
 * <ul>0 <= pills <= m</ul>
 * <ul>0 <= tasks[i], workers[j], strength <= 109</ul>
 * <h2>Tip</h2>
 * Tag:单调队列<br>
 * 左老师讲解视频:https://www.bilibili.com/video/BV1y8411i7cY<br>
 * 提交时提交Solution类，去掉static修饰
 */
public class t2071 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maxTaskAssign(new int[]{3, 2, 1}, new int[]{0, 3, 3}, 1, 1));
        System.out.println(solution.maxTaskAssign(new int[]{5, 4}, new int[]{0, 0, 0}, 1, 5));
        System.out.println(solution.maxTaskAssign(new int[]{10,15,304}, new int[]{0,10,10,10,10}, 3, 10));
        System.out.println(solution.maxTaskAssign(new int[]{5,9,8,5,9}, new int[]{1,6,4,2,6}, 1, 5));
    }

    /**
     * 解题思路:
     * <li>二分搜索，可能完成的任务数在0和min{n,m}之间</li>
     * <li>贪心:根据自然智慧，任务要选弱的，工人要选强的</li>
     * <li>具体处理逻辑</li>
     * <ul>
     *     <li>在不吃药的情况下解锁任务</li>
     *     <li>如果可以，去除Deque头任务</li>
     *     <li>如果不可以，吃药解锁</li>
     *     <li>可以解锁的话，吃尾巴的节点，如果不行fail</li>
     * </ul>
     */
    static class Solution {
        int MAX = 50001;
        // 双端队列
        int[] deque = new int[MAX];
        // 头尾
        int t,h;
        // 全局任务数组
        int[] tasks;
        // 全局工人数组
        int[] works;
        public int maxTaskAssign(int[] ts, int[] ws, int pills, int strength) {
            Arrays.sort(ts);
            Arrays.sort(ws);
            tasks = ts;
            works = ws;
            int left = 0;
            int right = Math.min(ts.length,ws.length);
            int ans = 0;
            int middle = 0;
            int wSize = ws.length;
            while(right>=left){
                middle = left + (right-left)/2;
                if(ok(0,middle-1,wSize-middle,wSize-1,pills,strength)){
                    // OK
                    ans = middle;
                    left = middle+1;
                }else{
                    right = middle-1;
                }
            }
            return ans;
        }

        /**
         *
         * @param taskFrom Task起点
         * @param taskTo
         * @param workFrom
         * @param workTo
         * @param pills
         * @param strength
         * @return
         */
        public boolean ok(int taskFrom,int taskTo,int workFrom,int workTo,int pills,int strength){
            t = h =0;
            // 吃了多少药
            int cnt = 0;
            for(int i=workFrom,j=taskFrom;i<=workTo;i++){
                // 不吃药尝试解锁
                for(;j<=taskTo&&works[i]>=tasks[j];j++){
                    deque[t++] = j;
                }
                // 如果成功解锁了
                if(h<t&&works[i]>=tasks[deque[h]]){
                    // 弹出Deque的头
                    h++;
                }else{
                    // 尝试吃药解锁
                    for(;j<=taskTo&&works[i]+strength>=tasks[j];j++){
                        deque[t++] = j;
                    }
                    // 如果解锁成功
                    if(h<t&&works[i]+strength>=tasks[deque[t-1]]){
                        // 丢掉末尾
                        t--;
                        cnt++;
                    }else{
                        return false;
                    }
                    // 吃药吃太多了
                    if(cnt>pills){
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
