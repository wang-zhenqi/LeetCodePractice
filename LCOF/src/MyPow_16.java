import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

/**
 * 实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，
 * 同时不需要考虑大数问题。
 *
 * 说明:
 *
 * -100.0 < x < 100.0
 * n是 32 位有符号整数，其数值范围是[−2^31, 2^31 − 1] 。
 * 注意：本题与主站 50 题相同：https://leetcode-cn.com/problems/powx-n/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zhi-de-zheng-shu-ci-fang-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MyPow_16 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            double x = Double.parseDouble(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            double ret = new MyPow_16().myPow(x, n);

            System.out.print(ret);
        }
    }

    public double myPow(double x, int n) {
        //Rapid power number calculation
        if(x == 0) {
            return 0;
        }

        if(n == 0) {
            return 1;
        }

        //defining the 't' is for the case of n = 0xFFFFFFFF
        //Another way is to define a variable of type long to store the parameter n.
        double t = 1, ret = 1;
        if(n < 0) {
            x = 1 / x;
            t = x;
            n = -(n + 1);   //in case n = 0xFFFFFFFF, -n will be overflown
        }

        while(n > 0) {
            if((n & 1) == 1) {
                ret *= x;
            }
            x *= x;
            n >>= 1;
        }
        return t * ret;
    }
}
