import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a positive integer n, break it into the sum of at least two positive
 * integers and maximize the product of those integers. Return the maximum product
 * you can get.
 * <p>
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/integer-break
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IntegerBreak_343 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new IntegerBreak_343().integerBreak(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int integerBreak(int n) {
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
