import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Write a program to find the n-th ugly number.
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 *
 * Note:
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ugly-number-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UglyNumberII_264 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new UglyNumberII_264().nthUglyNumber(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int nthUglyNumber(int n) {
        if(n == 1) {
            return 1;
        }
        int[] uglyNumbers = new int[n];
        uglyNumbers[0] = 1;
        int indexOfTwo = 0, indexOfThree = 0, indexOfFive = 0;
        int nextIndex = 1;
        do {
            uglyNumbers[nextIndex] = Math.min(uglyNumbers[indexOfTwo] * 2, Math.min(uglyNumbers[indexOfThree] * 3, uglyNumbers[indexOfFive] * 5));
            if(uglyNumbers[indexOfTwo] * 2 == uglyNumbers[nextIndex]) {
                indexOfTwo++;
            }
            if(uglyNumbers[indexOfThree] * 3 == uglyNumbers[nextIndex]) {
                indexOfThree++;
            }
            if(uglyNumbers[indexOfFive] * 5 == uglyNumbers[nextIndex]) {
                indexOfFive++;
            }
        } while(++nextIndex < n);
        return uglyNumbers[n - 1];
    }
}
