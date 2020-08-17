import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeapFrog_10_II {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new LeapFrog_10_II().numWays(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int numWays(int n) {
        /*
         * 就是换了一种形式的斐波那契数列
         */
        if(n < 2) {
            return 1;
        }
        int[] states = {1, 1};
        for(int i = 2; i <= n; i++) {
            states[i & 1] = (states[0] + states[1]) % 1000000007;
        }
        return states[n & 1];
    }
}
