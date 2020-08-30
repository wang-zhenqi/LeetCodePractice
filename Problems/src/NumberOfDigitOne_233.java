import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-digit-one
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOfDigitOne_233 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while((s = bufferedReader.readLine()) != null) {
            int ret = new NumberOfDigitOne_233().countDigitOne(Integer.parseInt(s));
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
