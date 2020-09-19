import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，
 * Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 *
 * 限制：
 * 数组长度为 5
 * 数组的数取值为 [0, 13] .
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/bu-ke-pai-zhong-de-shun-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Straight_61 {
    public static int[] stringToIntegerArray(String input) {
        input = input.trim();
        input = input.substring(1, input.length() - 1);
        if (input.length() == 0) {
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

    public static String booleanToString(boolean input) {
        return input ? "True" : "False";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            boolean ret = new Straight_61().isStraight(nums);

            String out = booleanToString(ret);

            System.out.print(out);
        }
    }

    public boolean isStraight(int[] nums) {
        int[] cards = new int[14];
        int min = 14, max = 0;
        for(int num : nums) {
            if(num != 0) {
                cards[num]++;
                if(cards[num] > 1) {
                    return false;
                }
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
        }
        return max - min <= 4;
    }
}