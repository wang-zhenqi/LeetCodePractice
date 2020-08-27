/**
 * 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 *
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 *
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的
 * 正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 *
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 *
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的
 * 函数不需要进行转换。
 *
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 *
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为[−2^31, 2^31 − 1]。如果数值超过这个
 * 范围，请返回 INT_MAX (2^31 − 1) 或INT_MIN (−2^31) 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-zi-fu-chuan-zhuan-huan-cheng-zheng-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class StringToInteger_67 {
    public static void main(String[] args) {
        int ans = new StringToInteger_67().strToInt("-91283472332");
        System.out.println(ans);
    }

    public int strToInt(String str) {
        str = str.trim();
        if(str.isEmpty()) {
            return 0;
        }
        long answer = 0;
        boolean valid = false;
        int posOrNeg = str.charAt(0) == '-' ? -1 : 1;
        int i = (str.charAt(0) == '+' || str.charAt(0) == '-') ? 1 : 0;
        while(i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
            valid = true;
            answer = answer * 10 + (str.charAt(i) - '0');
            if(posOrNeg > 0 && answer > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if(posOrNeg < 0 && answer > (long)Integer.MAX_VALUE + 1) {
                return Integer.MIN_VALUE;
            }
            i++;
        }
        return !valid ? 0 : (int) (posOrNeg * answer);
    }
}
