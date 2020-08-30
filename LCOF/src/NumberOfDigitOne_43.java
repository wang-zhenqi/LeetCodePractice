import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 *
 * 例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。
 *
 * 限制：
 *
 * 1 <= n < 2^31
 * 注意：本题与主站 233 题相同：https://leetcode-cn.com/problems/number-of-digit-one/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfDigitOne_43 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = bufferedReader.readLine()) != null) {
            int ret = new NumberOfDigitOne_43().countDigitOne(Integer.parseInt(s));
            System.out.println(ret);
        }
    }

    public int countDigitOne(int n) {
        if(n == 0) {
            return 0;
        }

        /*
         * digitPos means the which place of a number: one's place, ten's place,
         * hundredth's place...
         */
        long digitPos = 1;
        int count = 0;

        while(n / digitPos / 10 > 0) {
            /*
             * get the zones:
             * one's place: |1 ~ 10|11 ~ 20|21 ~ 30|..., each place has 1 one
             * ten's place: |10 ~ 109|110 ~ 209|210 ~ 310|..., each place has 10 ones.
             * ...
             */
            int t = (int)((n - digitPos) / digitPos / 10);
            count += digitPos * t;
            //add the tails.
            count += Math.min(n - (digitPos * 10 * t + digitPos - 1), digitPos);
            digitPos *= 10;
        }
        if(n / digitPos == 1) {
            count += (n % digitPos) + 1;
        } else {
            count += digitPos;
        }
        return count;
    }
}
