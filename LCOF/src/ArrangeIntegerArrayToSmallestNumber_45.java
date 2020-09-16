import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
 * <p>
 * 提示:
 * 0 < nums.length <= 100
 * <p>
 * 说明:
 * 输出结果可能非常大，所以你需要返回一个字符串而不是整数
 * 拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ArrangeIntegerArrayToSmallestNumber_45 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if(input.length() == 0) {
            return new int[0];
        }

        String[] parts = input.split(",");
        int[] output = new int[parts.length];
        for(int index = 0; index < parts.length; index++) {
            String part = parts[index].trim();
            output[index] = Integer.parseInt(part);
        }
        return output;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            String ret = new ArrangeIntegerArrayToSmallestNumber_45().minNumber(nums);

            System.out.print(ret);
        }
    }

    public String minNumber(int[] nums) {
        int length = nums.length;

        String[] numStrs = new String[nums.length];
        for(int i = 0; i < length; i++) {
            numStrs[i] = Integer.toString(nums[i]);
        }

        Arrays.sort(numStrs, (o1, o2) -> (o1 + o2).compareTo(o2 + o1));

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append(numStrs[i]);
        }
        return sb.toString();
    }
}
