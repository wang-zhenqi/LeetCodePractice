/**
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 * Only the space character ' ' is considered as whitespace character.
 * Assume we are dealing with an environment which could only store integers
 * within the 32-bit signed integer range: [−2^31, 2^31 − 1]. If the numerical
 * value is out of the range of representable values, INT_MAX (2^31 − 1) or
 * INT_MIN (−2^31) is returned.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/string-to-integer-atoi
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AtoI_8 {
    public static void main(String[] args) {
        int ans = new AtoI_8().myAtoi("238423");
        System.out.println(ans);
    }

    public int myAtoi(String str) {
        str = str.trim();

        int length = str.length();
        if(length == 0) {
            return 0;
        }

        int answer = 0;
        boolean valid = false;
        int posOrNeg = str.charAt(0) == '-' ? -1 : 1;

        int i = (str.charAt(0) == '+' || str.charAt(0) == '-') ? 1 : 0;
        if(i >= length) {
            return 0;
        }

        char ch = str.charAt(i);
        while(ch >= '0' && ch <= '9') {
            valid = true;
            if(posOrNeg > 0 && (answer > 214748364 || answer == 214748364 && ch > '7')) {
                return Integer.MAX_VALUE;
            } else if(posOrNeg < 0 && (answer > 214748364 || answer == 214748364 && ch > '8')) {
                return Integer.MIN_VALUE;
            }
            answer = answer * 10 + (str.charAt(i) - '0');
            if(++i >= length) {
                break;
            }
            ch = str.charAt(i);
        }
        
        return !valid ? 0 : posOrNeg * answer;
    }
}
