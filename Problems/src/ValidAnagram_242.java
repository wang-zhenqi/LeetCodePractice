import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidAnagram_242 {
    private static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            String s = line;
            line = in.readLine();
            String t = line;

            boolean ret = new ValidAnagram_242().isAnagram(s, t);

            String out = booleanToString(ret);

            System.out.println(out);
        }
    }

    public boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) {
            return false;
        }

        int[] count = new int[26];
        char[] sChar = s.toCharArray();
        char[] tChar = t.toCharArray();

        for(int i = 0; i < sLen; i++) {
            count[sChar[i] - 'a']++;
        }

        for(int i = 0; i < tLen; i++) {
            count[tChar[i] - 'a']--;
            if(count[tChar[i] - 'a'] < 0) {
                return false;
            }
        }

        return true;
    }
}