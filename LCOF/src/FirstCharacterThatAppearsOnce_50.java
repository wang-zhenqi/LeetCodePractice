import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 限制：
 * 0 <= s 的长度 <= 50000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FirstCharacterThatAppearsOnce_50 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            char ret = new FirstCharacterThatAppearsOnce_50().firstUniqChar(line);
            System.out.println(ret);
        }
    }

    public char firstUniqChar(String s) {
        if(s.length() == 0) {
            return ' ';
        }
        char[] chars = s.toCharArray();
        int[] appearence = new int[26];
        for(char ch : chars) {
            appearence[ch - 'a']++;
        }
        for(char ch : chars) {
            if(appearence[ch - 'a'] == 1) {
                return ch;
            }
        }
        return ' ';
    }
}
