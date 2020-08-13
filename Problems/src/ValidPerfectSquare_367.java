import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a positive integer num, write a function which returns True if num
 * is a perfect square else False.
 * <p>
 * Follow up: Do not use any built-in library function such as sqrt.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidPerfectSquare_367 {
    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            boolean ret = new ValidPerfectSquare_367().isPerfectSquare(num);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean isPerfectSquare(int num) {
        /*
         * This problem is almost the same as problem 69.
         */
        if(num == 0 || num == 1) {
            return true;
        }

        double xn = num;
        while(xn - num / xn > 1e-5) {
            xn = xn / 2 + num / xn / 2;
        }
        return (int) xn * (int) xn == num;
    }
}
