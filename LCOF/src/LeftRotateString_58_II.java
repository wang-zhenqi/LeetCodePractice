import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。
 * 比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 *
 * 示例 2：
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *
 * 限制：
 * 1 <= k < s.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeftRotateString_58_II {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            String s = line;
            line = in.readLine();
            int n = Integer.parseInt(line);

            String ret = new LeftRotateString_58_II().reverseLeftWords(s, n);
            System.out.println(ret);
        }
    }

    public String reverseLeftWords(String s, int n) {
        //Version 1, String operation
        /*return s.substring(n) + s.substring(0, n);*/

        //Version 2, String operation 2
        /*return (s + s).substring(n, n + s.length());*/

        //Version 3, character swapping
        char[] arr = s.toCharArray();
        swap(arr, 0, arr.length - 1);
        swap(arr, 0, arr.length - n - 1);
        swap(arr, arr.length - n, arr.length - 1);
        return String.valueOf(arr);
    }

    private void swap(char[] arr, int s, int e) {
        while(s < e) {
            char t = arr[s];
            arr[s] = arr[e];
            arr[e] = t;
            s++;
            e--;
        }
    }
}