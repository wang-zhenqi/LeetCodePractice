/**
 * Given a string, your task is to count how many palindromic substrings in this
 * string.
 *
 * The substrings with different start indexes or end indexes are counted as
 * different substrings even they consist of same characters.
 *
 * Note: The input string length won't exceed 1000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PalindromicSubstrings_647 {
    public static void main(String[] args) {
        //String s = "aaabaa"; // a, aa, aaa, a, aa, aabaa, a, aba, b, a, aa, a - 12
        String s = "abcbabccaccb";

        int ret = new PalindromicSubstrings_647().countSubstrings(s);

        String out = String.valueOf(ret);

        System.out.print(out);
    }

    public int countSubstrings(String s) {
        /*
         * Version 2, pruning. Treat each letter as the middle one, compare the
         * letters in front of and behind it.
         */
        int length = s.length();
        int count = length;

        for(int i = 0; i < length; i++) {
            byte flag = 3;
            for(int step = 1; (flag & 3) > 0 && i - step >= 0 && i + step <= length; step++) {
                if((flag & 1) == 1 && i + step < length &&
                        s.charAt(i - step) == s.charAt(i + step)) {
                    count++;
                } else {
                    flag &= -2;
                }
                if((flag & 2) == 2 && s.charAt(i - step) == s.charAt(i + step - 1)) {
                    count++;
                } else {
                    flag &= -3;
                }
            }
        }
        return count;
    }
}
