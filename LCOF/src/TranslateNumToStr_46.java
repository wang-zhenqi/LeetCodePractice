import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，
 * ……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 * <p>
 * 提示：
 * 0 <= num < 231
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class TranslateNumToStr_46 {
    private int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int ret = new TranslateNumToStr_46().translateNum(Integer.parseInt(line));
            System.out.println(ret);
        }
    }

    public int translateNum(int num) {
        String numStr = String.valueOf(num);
        dfs(numStr, 0);
        return count;
    }

    private void dfs(String numStr, int index) {
        if(index == numStr.length()) {
            count++;
            return;
        }
        dfs(numStr, index + 1);
        if(index < numStr.length() - 1 &&
                (numStr.charAt(index) == '1' ||
                        (numStr.charAt(index) == '2' && numStr.charAt(index + 1) < '6'))) {
            dfs(numStr, index + 2);
        }
    }
}
