import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of
 * all numbers in this range, inclusive.
 *
 * Example 1:
 *
 * Input: [5,7]
 * Output: 4
 * Example 2:
 *
 * Input: [0,1]
 * Output: 0
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BitwiseANDofNumbersRange_201 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            int ret = new BitwiseANDofNumbersRange_201().rangeBitwiseAnd(m, n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int rangeBitwiseAnd(int m, int n) {
        /*
         * If m and n has different number of bits, then the answer will always be 0.
         * Otherwise, pick the MSB, do the same to the rest bits.
         * This method is slow, in the worst case, the time complexity can be O(N^2).
         */
        int ml = 0, nl = 0;
        for(int i = 1; i < 32; i++) {
            if(ml == 0 && (m & (Integer.MIN_VALUE >>> i)) != 0) {
                ml = 32 - i;
            }
            if(nl == 0 && (n & (Integer.MIN_VALUE >>> i)) != 0) {
                nl = 32 - i;
            }
            if(ml != 0 && nl != 0) {
                break;
            }
        }
        if(ml != nl || ml == 0) {
            return 0;
        }
        return (1 << (ml - 1)) ^ rangeBitwiseAnd(m & ((1 << (ml - 1)) - 1), n & ((1 << (nl - 1)) - 1));
    }
}
