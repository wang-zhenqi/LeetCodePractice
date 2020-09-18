import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句
 * （A?B:C）。
 *
 * 示例 1：
 * 输入: n = 3
 * 输出:6
 *
 * 示例 2：
 * 输入: n = 9
 * 输出:45
 *
 * 限制：
 * 1 <= n <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qiu-12n-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumOfOneToN_64 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            int ret = new SumOfOneToN_64().sumNums(n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int sumNums(int n) {
        boolean flag = (n > 1) && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
