package Leetcode;

import java.util.Arrays;

/**
 * 链接:https://leetcode.cn/problems/daily-temperatures/
 *
 * 739.每日温度
 *
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 *
 *
 *
 * 示例 1:
 *
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * 示例 2:
 *
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * 示例 3:
 *
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 *
 *
 * 提示：
 *
 * 1 <= temperatures.length <= 105
 * 30 <= temperatures[i] <= 100
 *
 * 提交到leetcode只用把全局变量和方法提交上去，要不把class名字改为Solution，把main函数改了
 *
 * 左老师题解:https://www.bilibili.com/video/BV1HH4y1X7T9
 *
 * Tag:单调栈
 *
 * Note:注意相等时候的处理
 */
public class t0739 {
    // 最大数据量
    int MAX = 100001;
    // 栈
    int[] stack = new int[MAX];
    // 右侧位置
    int r = 0;
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        r = 0;
        //答案数组
        int[] ans = new int[n];
        for(int i=0,cur;i<n;i++){
            //尝试弹出,这个值大的时候弹出，小压大，相等也压入
            while(r>0&&temperatures[i]>temperatures[stack[r-1]]){
                cur = stack[--r];
                ans[cur] = i-cur;
            }
            // 压入栈
            stack[r++] = i;
        }
        //不用清算，因为stack里面答案为0
        return ans;
    }

    public static void main(String[] args) {
        t0739 test = new t0739();
        System.out.println(Arrays.toString(test.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println(Arrays.toString(test.dailyTemperatures(new int[]{30,40,50,60})));
        System.out.println(Arrays.toString(test.dailyTemperatures(new int[]{30,60,90})));
    }
}
