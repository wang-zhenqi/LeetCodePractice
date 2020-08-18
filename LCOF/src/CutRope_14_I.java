import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为
 * k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * <p>
 * 提示：2 <= n <= 58
 * 注意：本题与主站 343 题相同：https://leetcode-cn.com/problems/integer-break/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class CutRope_14_I {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new CutRope_14_I().cuttingRope(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int cuttingRope(int n) {
        if(n <= 3) {
            return n - 1;
        }

        int[] maxProduct = new int[n + 1];
        maxProduct[1] = 1;
        maxProduct[2] = 2;
        maxProduct[3] = 3;

        for(int i = 4; i <= n; i++) {
            int tmpMax = 0;
            for(int j = i / 2; j >= 1; j--) {
                tmpMax = Math.max(tmpMax, j * maxProduct[i - j]);
            }
            maxProduct[i] = tmpMax;
        }
        return maxProduct[n];
    }
}
