import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
/**
 * Given two strings s and t , write a function to determine if t is an anagram of s.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-anagram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidAnagram_242 {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> letterMap = new HashMap<>();
        int sLen = s.length();
        int tLen = t.length();
        if(sLen != tLen) {
            return false;
        } else if(sLen == 0) {
            return true;
        }

        for(int i = 0; i < sLen; i++) {
            char curChar = s.charAt(i);
            Integer value;
            if((value = letterMap.get(curChar)) == null) {
                letterMap.put(curChar, 1);
            } else {
                letterMap.put(curChar, value + 1);
            }
        }

        for(int i = 0; i < tLen; i++) {
            char curChar = t.charAt(i);
            Integer value;
            if((value = letterMap.get(curChar)) == null) {
                return false;
            } else {
                letterMap.put(curChar, --value);
                if(value == 0) {
                    letterMap.remove(curChar);
                }
            }
        }

        if(!letterMap.isEmpty()) {
            return false;
        }
        return true;
    }
}

class MainClass_242 {
    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = line;
            line = in.readLine();
            String t = line;

            boolean ret = new ValidAnagram_242().isAnagram(s, t);

            String out = booleanToString(ret);

            System.out.println(out);
        }
    }
}