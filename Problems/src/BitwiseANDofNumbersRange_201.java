import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of
 * all numbers in this range, inclusive.
 * <p>
 * Example 1:
 * <p>
 * Input: [5,7]
 * Output: 4
 * Example 2:
 * <p>
 * Input: [0,1]
 * Output: 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bitwise-and-of-numbers-range
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BitwiseANDofNumbersRange_201 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
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
         * Version 2, optimization from version 1.
         * You'll find that version 1 is actually finding the longest common prefix
         * of m and n if you take a close look at the solution. And doing so is not
         * hard. The time complexity is O(logN).
         */
        while(m < n) {
            n &= (n - 1);
        }
        return n;
    }
}
