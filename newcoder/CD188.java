package newcoder;

import java.io.*;

/**
 *  链接:https://www.nowcoder.com/practice/2a2c00e7a88a498693568cef63a4b7bb
 *
 *  题目描述:
 *  单调栈结构(进阶)
 *  描述
 *      给定一个可能含有重复值的数组 arr，找到每一个 i 位置左边和右边离 i 位置最近且值比 arr[i] 小的位置。返回所有位置相应的信息。
 * 输入描述：
 *      第一行输入一个数字 n，表示数组 arr 的长度。
 *      以下一行输入 n 个数字，表示数组的值
 * 输出描述：
 *      输出n行，每行两个数字 L 和 R，如果不存在，则值为 -1，下标从 0 开始。
 *
 * Tag:单调栈，模板题
 *
 * 提交时请把类名改为Main，去掉包名
 *
 * 左老师讲解视频:https://www.bilibili.com/video/BV1HH4y1X7T9
 *
 * 我的解答:  运行时间 898 ms 占用内存 88840KB
 */
public class CD188 {
    static int MAX = 1000001;
    // 存储输入
    static int[] arr = new int[MAX];
    // 栈
    static int[] stack = new int[MAX];
    // 栈顶元素为r-1
    static int r = 0;
    // 答案,[][0]表示左边，[][1]表示右边
    static int ans[][] = new int[MAX][2];
    // 数据量
    static int n = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);

        while(in.nextToken()!=StreamTokenizer.TT_EOF){

            n = (int)in.nval;

            // 输入
            for(int i=0;i<n;i++){
                in.nextToken();
                arr[i] = (int)in.nval;
            }

            compute();

            for(int i = 0;i<n;i++){
                out.println(ans[i][0] + " "+ ans[i][1]);
            }
            //本地跑用，提交时注释，因为下面那个会把全部答案刷出
            //out.flush();
        }

        out.flush();
        out.close();
        br.close();
    }

    /**
     * 使用单调栈计算
     *
     * 这个题是大压小
     * Step 1.
     * 保持严格大小顺序压栈，如果不满足条件，那么弹出，并记录答案，注意-1
     *
     * Step 2.
     * 对栈剩余元素弹出，这一阶段，右侧答案始终为-1
     *
     * Step 3.(当没有重复值的时候可以不做)
     * 修正阶段，从后往前遍历，如果和右侧下标指向的值相同的话就直接把别人的下标抢过来
     */
    public static void compute(){
        r = 0;
        int cur = 0;
        // 1.遍历
        for(int i=0;i<n;i++){
            while(r>0&&arr[stack[r-1]]>=arr[i]){
                //尝试弹出
                cur = stack[--r];
                //左边
                ans[cur][0] = r>0?stack[r-1]:-1;
                //右边
                ans[cur][1] = i;
            }
            stack[r++] = i;
        }

        // 2.弹出剩余元素
        while(r>0){
            cur = stack[--r];
            //左边
            ans[cur][0] = r>0?stack[r-1]:-1;
            //右边
            ans[cur][1] = -1;
        }

        //3.修正
        //n-1右边一定是-1，不用修正
        for(int i = n-2;i>0;i--){
            if(ans[i][1]!=-1&&arr[i] == arr[ans[i][1]]){
                //和答案指向一样
                ans[i][1] = ans[ans[i][1]][1];
            }
        }
    }
}
