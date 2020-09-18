import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。
 * 例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 *
 * 示例 2：
 * 输入: " hello world! "
 * 输出:"world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 *
 * 示例 3：
 * 输入: "a good  example"
 * 输出:"example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
 * 注意：本题与主站 151 题相同：https://leetcode-cn.com/problems/reverse-words-in-a-string/
 * 注意：此题对比原题有改动
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReverseWordsInSentence_58_I {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String ret = new ReverseWordsInSentence_58_I().reverseWords(line);
            System.out.print(ret);
        }
    }

    public String reverseWords(String s) {
        /*
         * Version 1.
         * Using member function of class String.
         */
        String[] words = s.trim().split(" +");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]).append(" ");
        }
        if(sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();

        /*
         * Version 2, Stack.
         */
        /*
        Stack<String> stack = new Stack<>();
        StringBuilder tmp = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ' ') {
                if(tmp.length() != 0) {
                    stack.push(tmp.toString());
                }
                tmp.delete(0, tmp.length());
            } else {
                tmp.append(s.charAt(i));
            }
        }
        if(tmp.length() > 0) {
            stack.push(tmp.toString());
        }
        tmp.delete(0, tmp.length());

        while(!stack.empty()) {
            tmp.append(stack.pop()).append(" ");
        }
        if(tmp.length() > 0 && tmp.charAt(tmp.length() - 1) == ' ') {
            tmp.deleteCharAt(tmp.length() - 1);
        }
        return tmp.toString();
         */
    }
}
