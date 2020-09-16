import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 * 限制：1 <= s 的长度 <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArrangementOfAString_38 {
    List<String> result;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            String[] rets = new ArrangementOfAString_38().permutation(line);
            if(rets != null) {
                for(String ret : rets) {
                    System.out.print(ret + " ");
                }
            }
            System.out.println();
        }
    }

    public String[] permutation(String s) {
        int length = s.length();
        if(length == 0) {
            return null;
        }
        char[] chars = s.toCharArray();
        result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(chars, sb);
        String[] ans = new String[result.size()];
        result.toArray(ans);
        return ans;
    }

    private void dfs(char[] chars, StringBuilder sb) {
        if(chars.length == 1) {
            sb.append(chars[0]);
            result.add(sb.toString());
            sb.deleteCharAt(sb.length() - 1);
            return;
        }

        HashSet<Character> visited = new HashSet<>();
        for(int i = 0; i < chars.length; i++) {
            if(visited.contains(chars[i])) {
                continue;
            }
            char t = chars[0];
            chars[0] = chars[i];
            chars[i] = t;
            visited.add(chars[0]);
            sb.append(chars[0]);
            dfs(Arrays.copyOfRange(chars, 1, chars.length), sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
