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
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int ret = new TranslateNumToStr_46().translateNum(Integer.parseInt(line));
            System.out.println(ret);
        }
    }

    public int translateNum(int num) {
        /*
         * Version 2, DP.
         * Define counts[i] as the numbers of different translations starting from
         * position i in numStr.
         *
         * To make it easier, add an extra element: counts[numStr.length()] = 1.
         * By doing this, I don't need to consider the out of boundary situation.
         *
         * count[i] = count[i + 1] + (if applicable)count[i + 2]
         * As to what condition is 'applicable', see the codes below.
         *
         * The answer is count[0].
         */
        String numStr = String.valueOf(num);
        int[] counts = new int[numStr.length() + 1];
        counts[numStr.length()] = 1;
        for(int i = numStr.length() - 1; i >= 0; i--) {
            counts[i] += counts[i + 1];
            if(i < numStr.length() - 1 &&
                    (numStr.charAt(i) == '1' ||
                            (numStr.charAt(i) == '2' && numStr.charAt(i + 1) < '6'))) {
                counts[i] += counts[i + 2];
            }
        }
        return counts[0];
    }
}
