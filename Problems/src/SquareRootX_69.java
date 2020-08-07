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
         * Version 1, binary search.
         * In this solution, I made it much practical than the problem description says.
         * It can actually have the precision of 1e-9, and this value should be
         * changeable. In order to solve it, I just return its integer part.
         */
        double left = 0.0, right = (double)x;
        double mid = 0.0;
        while(right - left > 1e-9) {
            mid = left + (right - left) / 2;
            if(mid * mid - x > 1e-9) {
                right = mid;
            } else if(mid * mid - x < -1e-9) {
                left = mid;
            } else {
                break;
            }
        }
        return (int)(mid + 1e-9);
    }
}
