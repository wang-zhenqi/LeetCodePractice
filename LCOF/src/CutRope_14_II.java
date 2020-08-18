import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
 * k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？例如，当绳子的长度是8时，
 * 我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 * <p>
 * 提示：2 <= n <= 1000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CutRope_14_II {
    private final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new CutRope_14_II().cuttingRope(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }

        long res;
        switch(n % 3) {
            case 2:
                res = pow(3, n / 3) * 2 % MOD;
                break;
            case 1:
                res = pow(3, n / 3 - 1) * 4 % MOD;
                break;
            default:
                res = pow(3, n / 3);
                break;
        }
        return (int) res;
    }

    private long pow(long n, int x) {
        if(x == 0) {
            return 1;
        }
        if(x == 1) {
            return (int) n;
        }
        long res = 1;
        while(x != 0) {
            if((x & 1) == 1) {
                res = res * n % MOD;
            }
            n = n * n % MOD;
            x >>= 1;
        }
        return res;
    }
}
