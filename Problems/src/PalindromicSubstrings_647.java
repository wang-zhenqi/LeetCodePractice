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
        String s = "";

        int ret = new PalindromicSubstrings_647().countSubstrings(s);

        String out = String.valueOf(ret);

        System.out.print(out);
    }

    public int countSubstrings(String s) {
        /*
         * Version 1, brutal force. O(N^2).
         */
        char[] chars = s.toCharArray();
        int count = chars.length;
        for(int i = 0; i < chars.length; i++) {
            double mid = i + 0.5;
            int right = (int)(i + (mid - i) * 2);
            while(right < chars.length) {
                boolean flag = true;
                for(int j = i; j < mid; j++) {
                    int pair = (int)(j + (mid - j) * 2);
                    if(chars[j] != chars[pair]) {
                        flag = false;
                        break;
                    }
                }
                if(flag) {
                    count++;
                }
                mid += 0.5;
                right = (int)(i + (mid - i) * 2);
            }
        }
        return count;
    }
}
