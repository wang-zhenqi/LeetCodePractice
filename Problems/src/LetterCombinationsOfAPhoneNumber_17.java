import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible
 * letter combinations that the number could represent.
 * <p>
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below. Note that 1 does not map to any letters.
 * <p>
 * Note:
 * <p>
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LetterCombinationsOfAPhoneNumber_17 {
    private final char[][] ALPHABETS = {
            {},
            {},
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };
    private List<String> list;

    public static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        if(stringList.size() > 0) {
            for(String item : stringList) {
                sb.append(item);
                sb.append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            String digits = line.substring(1, line.length() - 1);

            List<String> ret = new LetterCombinationsOfAPhoneNumber_17().letterCombinations(digits);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }

    public List<String> letterCombinations(String digits) {
        /*
         * DFS. Using StringBuilder, instead of String with "+" operation, will
         * tremendously reduce the time of generating strings.
         */
        list = new ArrayList<>();
        if(digits != null && digits.length() > 0 && !digits.contains("1")) {
            StringBuilder element = new StringBuilder();
            dfs(digits, 0, element);
        }
        return list;
    }

    private void dfs(String digits, int index, StringBuilder element) {
        if(index == digits.length() - 1) {
            for(char letter : ALPHABETS[digits.charAt(index) - '0']) {
                list.add(element.append(letter).toString());
                element.deleteCharAt(index);
            }
            return;
        }

        for(char letter : ALPHABETS[digits.charAt(index) - '0']) {
            dfs(digits, index + 1, element.append(letter));
            element.deleteCharAt(index);
        }
    }
}
