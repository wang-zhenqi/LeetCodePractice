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
        String s = "aaabaa";

        int ret = new PalindromicSubstrings_647().countSubstrings(s);

        String out = String.valueOf(ret);

        System.out.print(out);
    }

    public int countSubstrings(String s) {
        /*
         * Version 3, dp, O(N^2)
         *
         * dp[i][j] represents whether the substring from position i to j is a
         * palindrome.
         *
         * if j == i && s[j] == s[i], then dp[i][j] == 1
         * else if j - i == 1 && s[i] == s[i], then dp[i][j] == 1
         * else if dp[i+1][j-1] == 1, dp[i][j] == 1.
         *
         * No initial values.
         */
        int length = s.length();
        int count = 0;

        int[][] dp = new int[length][length];

        for(int i = length - 1; i >= 0; i--) {
            for(int j = i; j < length; j++) {
                if((j - i <= 1 || dp[i + 1][j - 1] == 1) &&
                        s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = 1;
                    count++;
                }
            }
        }
        return count;
    }
}
