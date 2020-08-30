import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始
 * 计数）是5，第13位是1，第19位是4，等等。
 * <p>
 * 请写一个函数，求任意第n位对应的数字。
 * <p>
 * 限制：
 * 0 <= n < 2^31
 * 注意：本题与主站 400 题相同：https://leetcode-cn.com/problems/nth-digit/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NthDigit_44 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new NthDigit_44().findNthDigit(n);

            System.out.print(ret);
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
