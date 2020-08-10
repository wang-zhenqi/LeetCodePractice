import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Write a function that takes an unsigned integer and return the number of '1'
 * bits it has (also known as the Hamming weight).
 *
 * Note:
 *
 * Note that in some languages such as Java, there is no unsigned integer type.
 * In this case, the input will be given as signed integer type and should not
 * affect your implementation, as the internal binary representation of the integer
 * is the same whether it is signed or unsigned.
 * In Java, the compiler represents the signed integers using 2's complement
 * notation. Therefore, the input '11111111111111111111111111111101' represents
 * the signed integer -3.
 *
 * Follow up:
 * If this function is called many times, how would you optimize it?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-1-bits
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NumberOf1Bits_191 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new NumberOf1Bits_191().hammingWeight(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int hammingWeight(int n) {
        int result = 0;
        while(n != 0) {
            n &= n - 1; //remove the last '1' bit in the number
            result++;
        }
        return result;
    }
}
