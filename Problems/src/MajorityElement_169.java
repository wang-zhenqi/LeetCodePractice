import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an array of size n, find the majority element. The majority element is
 * the element that appears more than ⌊ n/2 ⌋ times.
 *
 * You may assume that the array is non-empty and the majority element always
 * exist in the array.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/majority-element
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement_169 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new MajorityElement_169().majorityElement(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int vote = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] != candidate) {
                vote--;
                if(vote <= 0) {
                    candidate = nums[i];
                    vote = 1;
                }
            } else {
                vote++;
            }
        }
        return candidate;
    }
}
