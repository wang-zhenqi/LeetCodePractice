import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 *
 * 提示：
 *
 * a, b 均可能是负数或 0
 * 结果不会溢出 32 位整数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SumUp_65 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int a = Integer.parseInt(line);
            line = in.readLine();
            int b = Integer.parseInt(line);

            int ret = new SumUp_65().add(a, b);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }

    /*
     * To get the sum of two numbers, there are three steps:
     * 1. Add the two numbers digit-wisely.
     * 2. Calculate the carry.
     * 3. Add up the carry.
     *
     * Since we couldn't use '+', '-', '*' and '/', the bitwise operation is the only
     * choice. 'xor' is used to add the two numbers bitwisely, 'and' and 'left shift'
     * is used to get the carry.
     */
    public int add(int a, int b) {
        while(b != 0) {
            int c = (a & b) << 1;
            a ^= b;
            b = c;
        }
        return a;
    }
}
