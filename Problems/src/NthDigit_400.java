import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7,
 * 8, 9, 10, 11, ...
 *
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 *
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is
 * a 0, which is part of the number 10.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/nth-digit
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NthDigit_400 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new NthDigit_400().findNthDigit(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int findNthDigit(int n) {
        if(n < 10) {
            return n;
        }

        long digit = 1;
        long nums = 9;
        while(n - digit * nums > 0) {
            n -= digit * nums;
            digit++;
            nums *= 10;
        }

        //Now we've got that the target number has *digit* digits.
        //And then calculate the target number is:
        String targetStr = Long.toString((n - 1) / digit + nums / 9);
        return targetStr.charAt((int) ((n - 1) % digit)) - '0';
    }
}
