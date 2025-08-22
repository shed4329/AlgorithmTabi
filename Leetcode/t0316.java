package Leetcode;

import java.util.Arrays;

/**
 * 链接:https://leetcode.cn/problems/remove-duplicate-letters/
 * <hr/>
 * 316. 去除重复字母
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 *<hr/>
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *<hr/>
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 * </hr>
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 *<hr/>
 * Tag:最小栈
 * 提交时只提交Solution类,去除static修饰
 * <hr/>
 *左老师讲解:https://www.bilibili.com/video/BV1GH4y1D7TB
 *
 */
public class t0316 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.removeDuplicateLetters("bcabc"));
        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
    }

    static class Solution {
        // 总共26个小写字母
        int MAX = 26;
        char[] stack = new char[MAX];
        int r = 0;
        // 表示某个字符是否进过栈
        boolean[] enter = new boolean[MAX];
        // 字符词频统计
        int[] cnt = new int[MAX];

        /**
         * 思路:
         * 1.首先统计词频
         * 2.然后遍历，如果栈顶比较大，后面还有相同字符，弹出(当前字符还没进入栈)
         * 3.将栈里面剩余的字符变为字符串
         * @param s 字符串
         * @return 最小字典序的字符串
         */
        public String removeDuplicateLetters(String s) {
            char[] array = s.toCharArray();
            r = 0;
            Arrays.fill(cnt,0);
            Arrays.fill(enter,false);

            // 统计词频
            for(char ch:array){
                cnt[ch-'a']++;
            }
            // 遍历阶段
            for(int i=0,index;i<array.length;i++){
                index = array[i]-'a';
                if(!enter[index]){
                    // 弹出
                    while(r>0&&stack[r-1]>array[i]&&cnt[stack[r-1]-'a']>0){
                        enter[stack[--r]-'a'] = false;
                    }

                    // 压入
                    stack[r++] = array[i];
                    enter[index] = true;
                }
                // 过了当前字符，统计减少1
                cnt[index]--;
            }

            // 转换为字符串
            return String.valueOf(stack,0,r);
        }
    }
}
