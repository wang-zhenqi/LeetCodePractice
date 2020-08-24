import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a non-empty string check if it can be constructed by taking a substring
 * of it and appending multiple copies of the substring together. You may assume
 * the given string consists of lowercase English letters only and its length will
 * not exceed 10000.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/repeated-substring-pattern
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class RepeatedSubstringPattern_459 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        RepeatedSubstringPattern_459 obj = new RepeatedSubstringPattern_459();
        while((line = bufferedReader.readLine()) != null) {
            System.out.println(obj.repeatedSubstringPattern(line));
        }
    }

    public boolean repeatedSubstringPattern(String s) {
        /*
         * If the string s has a repeated pattern, then the concatenation of two s
         * must has s as its substring. If the substring doesn't start from the
         * position 0, nor position s.length, we can say s has a pattern.
         *
         * For example: s = "abcabc", s + s = "abcabcabcabc", it can find s in s + s at
         * position 3: "abc(abcabc)abc"
         */
        return ((s + s).indexOf(s, 1) != s.length());
    }
}
