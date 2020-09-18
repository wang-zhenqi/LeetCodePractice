import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。
 *
 * 示例 1：
 * 输入：nums = [3,4,3,3]
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [9,1,7,9,7,9,7]
 * 输出：1
 *
 * 限制：
 * 1 <= nums.length <= 10000
 * 1 <= nums[i] < 2^31
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AppearanceTimesInArray_56_II {
    private static int[] stringToIntegerArray(String input) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new AppearanceTimesInArray_56_II().singleNumber(nums);
            System.out.println(ret);
        }
    }

    public int singleNumber(int[] nums) {
        int[] numOfOnesOfEachBit = new int[32];
        for(int num : nums) {
            int t = num;
            int b = 0;
            while(t > 0) {
                if((t & 1) == 1) {
                    numOfOnesOfEachBit[b]++;
                }
                b++;
                t >>= 1;
            }
        }

        int result = 0;
        for(int i = 0; i < 32; i++) {
            if(numOfOnesOfEachBit[i] % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }
}
