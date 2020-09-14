import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 * <p>
 * 说明:
 * <p>
 * 1 是丑数。
 * n 不超过1690。
 * 注意：本题与主站 264 题相同：https://leetcode-cn.com/problems/ugly-number-ii/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/chou-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UglyNumber_49 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new UglyNumber_49().nthUglyNumber(n);

            String out = String.valueOf(ret);

            System.out.println(out);
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
