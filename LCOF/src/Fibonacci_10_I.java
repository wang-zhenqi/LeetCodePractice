import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0, F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Fibonacci_10_I {
    public int fib(int n) {
        if(n < 2) {
            return n;
        }

        final int M = 1000000007;
        int[] ans = {0, 1};
        int p = 0;
        for(int i = 2; i <= n; i++) {
            ans[p] = (ans[0] + ans[1]) % M;
            p ^= 1;
        }
        return ans[p ^ 1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new Fibonacci_10_I().fib(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }
}
