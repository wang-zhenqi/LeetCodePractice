import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear
 * more than ⌊ n/3 ⌋ times.
 *
 * Note: The algorithm should run in linear time and in O(1) space.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElementII_229 {
    private static int[] stringToIntegerArray(String input) {
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

    private static String integerArrayListToString(List<Integer> nums, int length) {
        if(length == 0) {
            return "[]";
        }

        StringBuilder result = new StringBuilder();
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result.append(number).append(", ");
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    private static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            List<Integer> ret = new MajorityElementII_229().majorityElement(nums);

            String out = integerArrayListToString(ret);

            System.out.print(out);
        }
    }

    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();

        Integer candidate1 = null, candidate2 = null;
        int vote1 = 0, vote2 = 0;

        for(int num : nums) {
            if(candidate1 != null && candidate1 == num) {
                vote1++;
                continue;
            }
            if(candidate2 != null && candidate2 == num) {
                vote2++;
                continue;
            }
            if(vote1 == 0) {
                candidate1 = num;
                vote1 = 1;
                continue;
            }
            if(vote2 == 0) {
                candidate2 = num;
                vote2 = 1;
                continue;
            }
            vote1--;
            vote2--;
        }

        vote1 = 0;
        vote2 = 0;
        for(int num : nums) {
            if(num == candidate1) {
                vote1++;
            }
            if(candidate2 != null && num == candidate2) {
                vote2++;
            }
        }
        if(vote1 > nums.length / 3) {
            result.add(candidate1);
        }
        if(vote2 > nums.length / 3) {
            result.add(candidate2);
        }

        return result;
    }
}
