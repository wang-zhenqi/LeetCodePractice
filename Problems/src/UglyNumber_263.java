import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Write a program to check whether a given number is an ugly number.
 * <p>
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * <p>
 * Note:
 * 1 is typically treated as an ugly number.
 * Input is within the 32-bit signed integer range: [−2^31, 2^31 − 1].
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UglyNumber_263 {
    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int num = Integer.parseInt(line);

            boolean ret = new UglyNumber_263().isUgly(num);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean isUgly(int num) {
        if(num <= 0) {
            return false;
        }
        if(num == 1) {
            return true;
        }

        if(num % 2 == 0) {
            return isUgly(num / 2);
        } else if(num % 3 == 0) {
            return isUgly(num / 3);
        } else if(num % 5 == 0) {
            return isUgly(num / 5);
        } else {
            return false;
        }
    }
}
