package newcoder;

import java.io.*;

/**
 * 链接:https://www.nowcoder.com/practice/77199defc4b74b24b8ebf6244e1793de
 * <hr/>
 * 大雨吃小鱼
 * 描述
 * 小明最近喜欢上了俄罗斯套娃、大鱼吃小鱼这些大的包住小的类型的游戏。
 * 于是小明爸爸给小明做了一个特别版的大鱼吃小鱼游戏，他希望通过这个游戏
 * 能够近一步提高牛牛的智商。
 * 游戏规则如下：
 * 现在有N条鱼，每条鱼的体积为Ai，从左到右排成一排。
 * A数组是一个排列。
 * 小明每轮可以执行一次大鱼吃小鱼的操作
 * 一次大鱼吃小鱼的操作：对于每条鱼，它在每一次操作时会吃掉右边比自己小的第一条鱼
 * 值得注意的时，在一次操作中，每条鱼吃比自己小的鱼的时候是同时发生的。
 * 举一个例子，假设现在有三条鱼,体积为分别[5，4，3]，5吃4，4吃3，一次操作后就剩下[5]一条鱼。
 * 爸爸问小明，你知道要多少次操作，鱼的数量就不会变了嘛？
 * <hr/>
 * 输入描述：
 * 给定N；
 * 给定A数组
 * １＜＝N＜＝１０＾５
 * １＜＝Ai＜＝Ｎ
 * <hr/>
 * 输出描述：
 * 一行, 正整数, 表示要多少次操作，鱼的数量就不会变了。
 * <hr/>
 * Tag:单调栈
 * 左老师讲解视频:https://www.bilibili.com/video/BV1HH4y1X7T9
 * <hr/>
 * 解题思路:
 * 单调栈存2个数的小数组，第一个数表示鱼的重量，第二个数表示这个鱼完成后面的工作需要多少轮
 * 压栈时从右到左，小压大，这样出栈的时候表示被大鱼吃掉了，相等的时候直接压入，因为吃不掉
 * 更新逻辑:被压入栈的轮数+1和栈顶轮数取max
 */
public class hor4 {
    static int n = 0;
    static int MAX = 100001;
    static int[] arr = new int[MAX];
    // 栈
    static int[][] stack = new int[MAX][2];
    // 栈右边的位置
    static int r;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);

        int ans;
        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            n = (int)in.nval;
            for(int i=0;i<n;i++){
                in.nextToken();
                arr[i] = (int)in.nval;
            }
            ans = turns();
            out.println(ans);
            // 提交的时候把这里给注释了，出循环的时候会一次性输出
            out.flush();
        }

        br.close();
        out.flush();
        out.close();
    }

    /**
     * 1.从右向左压栈，小压大，指体重，相等时压入
     * 2.弹出更新时取max
     * 3.ans取全程max
     * @return 需要的最小轮数
     */
    public static int turns(){
        r = 0;
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
