import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given a string, find the length of the longest substring without
 * repeating characters.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeatingCharacters_3 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int ret = new LongestSubstringWithoutRepeatingCharacters_3().lengthOfLongestSubstring(line);
            System.out.print(ret);
        }
    }

    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.isEmpty()) {
            return 0;
        }

        //This array stores the index of the first character s.charAt(index)
        //that occurs in the window.
        int[] occurrencesIdx = new int[128];
        Arrays.fill(occurrencesIdx, -1);  //-1 means not occurred.

        int windowBegin = 0, maxLen = 0, curLen = 0;

        for(int windowEnd = 0; windowEnd < s.length(); windowEnd++) {
            int lastOccurred = occurrencesIdx[s.charAt(windowEnd)];
            if(lastOccurred >= windowBegin) {
                maxLen = Math.max(maxLen, curLen);
                curLen = windowEnd - lastOccurred - 1;
                windowBegin = lastOccurred + 1;
                if(windowBegin + maxLen >= s.length()) {
                    break;
                }
            }
            occurrencesIdx[s.charAt(windowEnd)] = windowEnd;
            curLen++;
        }
        return Math.max(maxLen, curLen);
    }
}
