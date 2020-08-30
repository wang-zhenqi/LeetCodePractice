import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a string, you need to reverse the order of characters in each word within
 * a sentence while still preserving whitespace and initial word order.
 *
 * Note: In the string, each word is separated by single space and there will not be
 * any extra space in the string.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWordInAStringIII_557 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String ret = new ReverseWordInAStringIII_557().reverseWords(line);
            System.out.print(ret);
        }
    }

    public String reverseWords(String s) {
        String[] splits = s.split(" ");
        StringBuilder sb = new StringBuilder();
        int j = 0;
        for(String split: splits) {
            for(int i = split.length() - 1; i >= 0; i--) {
                sb.append(split.charAt(i));
            }
            j++;
            if(j < splits.length) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
