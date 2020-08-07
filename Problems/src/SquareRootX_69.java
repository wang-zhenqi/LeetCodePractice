import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Implement int sqrt(int x).
 *
 * Compute and return the square root of x, where x is guaranteed to be a
 * non-negative integer.
 *
 * Since the return type is an integer, the decimal digits are truncated
 * and only the integer part of the result is returned.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SquareRootX_69 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int x = Integer.parseInt(line);

            int ret = new SquareRootX_69().mySqrt(x);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int mySqrt(int x) {
        /*
         * Version 2, Newton's method. Because of the type of the return value, this
         * problem became very ugly. There are some trick test cases, I tried many times.
         */
        if(x == 0 || x == 1) {
            return x;
        }

        double xn = (double)x;
        while(xn - x / xn > 1e-5) {
            xn = xn / 2 + x / xn / 2;
        }
        return (int)xn;
    }
}
