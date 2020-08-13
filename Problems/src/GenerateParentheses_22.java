import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class GenerateParentheses_22 {
    private List<String> result;

    private static String stringListToString(List<String> stringList) {
        StringBuilder sb = new StringBuilder("[");
        for(String item : stringList) {
            sb.append(item);
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);

            List<String> ret = new GenerateParentheses_22().generateParenthesis(n);

            String out = stringListToString(ret);

            System.out.print(out);
        }
    }

    private void generate(int leftUsed, int rightUsed, int n, String entry) {
        if(leftUsed == n && rightUsed == n) {
            result.add(entry);
            return;
        }
        if(leftUsed < n) {
            generate(leftUsed + 1, rightUsed, n, entry + "(");
        }
        if(rightUsed < n && rightUsed < leftUsed) {
            generate(leftUsed, rightUsed + 1, n, entry + ")");
        }
    }

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        generate(0, 0, n, "");
        return result;
    }
}
