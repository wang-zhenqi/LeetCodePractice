import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9表示成二进制是 1001，有 2 位是 1。
 * 因此，如果输入 9，则该函数输出 2。
 * <p>
 * 注意：本题与主站 191 题相同：https://leetcode-cn.com/problems/number-of-1-bits/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOf1Bits_15 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new NumberOf1Bits_15().hammingWeight(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int result = 0;
        while(n != 0) {
            n &= n - 1; //remove the last '1' bit in the number
            result++;
        }
        return result;
    }
}
