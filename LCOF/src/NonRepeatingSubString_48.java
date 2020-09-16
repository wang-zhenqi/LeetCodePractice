import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * <p>
 * 提示：
 * s.length <= 40000
 * 注意：本题与主站 3 题相同：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class NonRepeatingSubString_48 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int ret = new NonRepeatingSubString_48().lengthOfLongestSubstring(line);
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
