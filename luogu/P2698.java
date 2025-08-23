package luogu;

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 链接:https://www.luogu.com.cn/problem/P2698
 * <h1>P2698 [USACO12MAR] Flowerpot S</h1>
 * 老板需要你帮忙浇花。给出 N 滴水的坐标，(x,y) 表示水滴最初的坐标。
 * <br>
 * 每滴水均以每秒 1 个单位长度的速度下落。你需要把花盆放在 x 轴上的某个位置，使得花盆接到第 1 滴水与最后 1 滴水之间的时间差至少为 D。
 * <br>
 * 如果水滴落在 x 轴上的位置与花盆的边沿对齐，也认为被接住。
 * <br>
 * 给出 N 滴水的坐标和时间差 D ，请算出最小的花盆宽度 W。
 * <h2>输入格式</h2>
 * 第一行 2 个整数 N 和 D。
 * <br>
 * 接下来 N 行，每行 2 个整数，表示水滴的坐标 (x,y)。
 * <h2>输出格式</h2>
 * 一行 1 个整数，表示最小的花盆宽度。如果无法构造出满足题意的花盆，则输出 −1。
 * <h2>输入输出样例</h2>
 * 输入 #1<br>
 * 4 5<br>
 * 6 3<br>
 * 2 4<br>
 * 4 10<br>
 * 12 15<br>
 * 输出 #1<br>
 * 2
 * <h2>说明/提示</h2>
 * <h3>【样例解释】</h3>
 * 有 4 滴水，初始位置分别在 (6,3)，(2,4)，(4,10)，(12,15)。水滴至少用 5 秒时间先后落入花盆。花盆的宽度为 2 是必须且足够的，此时把花盆放在 x=4…6 的位置，它可以接到水滴 1 和 3 ，之间的时间差为 10−3=7，满足条件
 * <h3>【数据范围】</h3>
 * 40% 的数据：1≤N≤1000 ，1≤D≤2000。
 * 100% 的数据：1≤N≤10^5，1≤D≤10^6，0≤x,y≤10^6。
 * <h2>Tip</h2>
 * Tag:单调队列<br>
 * 请把包名去掉，把类名改为Main之后提交<br>
 * 左老师讲解链接:https://www.bilibili.com/video/BV11h4y1w7Bu
 * <h2>解题思路</h2>
 * <ul>首先花盆越长越符合条件</ul>
 * <ul>其次单调队列可以分别维护最大值和最小值</ul>
 * <ul>可以通过单调队列判断是否满足条件，然后控制滑动窗口的收缩</ul>
 */
public class P2698 {
    static int MAX = 100001;
    static int[] minDeque = new int[MAX];
    static int minh,mint;
    static int[] maxDeque = new int[MAX];
    static int maxh,maxt;
    static int[][] arr = new int[MAX][2];
    static int N,D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer in = new StreamTokenizer(br);
        PrintWriter out = new PrintWriter(System.out);

        while(in.nextToken()!=StreamTokenizer.TT_EOF){
            N = (int)in.nval;
            in.nextToken();
            D = (int)in.nval;


            for(int i=0;i<N;i++){
                in.nextToken();
                arr[i][0] = (int)in.nval;
                in.nextToken();
                arr[i][1] = (int)in.nval;
            }
            int ans = compute();
            out.println(ans == Integer.MAX_VALUE?-1:ans);
            // 提交的时候把下一行flush注释掉，提高io效率
            out.flush();

        }

        out.flush();
        out.close();
        br.close();
    }

    public static int compute(){
        Arrays.sort(arr,0,N, Comparator.comparingInt(a -> a[0]));
        minh=mint=maxh=maxt=0;
        int ans = Integer.MAX_VALUE;
        //遍历
        for(int left=0,right=0;left<N;left++){
            // 加入右侧直到符合条件为止
            while(right<N&&!OK()){
                push(right++);
            }
            if(OK()){
                ans = Math.min(ans,arr[right-1][0]-arr[left][0]);
            }
            poll(left);
        }

        return ans;
    }

    /**
     * 判断当前窗口有是否满足条件
     */
    public static boolean OK(){
        int min = minh==mint?0:arr[minDeque[minh]][1];
        int max = maxh==maxt?0:arr[maxDeque[maxh]][1];
        return max-min>=D;
    }

    /**
     * 将数字弹入栈
     * @param index 入栈数字下标
     */
    public static void push(int index){
        //先尝试弹出
        // 从大到小
        while(maxh<maxt&&arr[index][1]>=arr[maxDeque[maxt-1]][1]){
            //弹出Deque
            maxt--;
        }
        maxDeque[maxt++] = index;
        while(minh<mint&&arr[index][1]<=arr[minDeque[mint-1]][1]){
            mint--;
        }
        minDeque[mint++] = index;
    }

    /**
     * 将数字弹出Deque
     * @param index 弹出的位置
     */
    public static void poll(int index){
        if(index == maxDeque[maxh]&&maxh < maxt){
            maxh++;
        }
        if(index==minDeque[minh]&&minh<mint){
            minh++;
        }
    }
}
