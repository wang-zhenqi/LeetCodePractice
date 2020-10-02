import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an integer array nums, find the contiguous subarray (containing at least
 * one number) which has the largest sum and return its sum.
 * Follow up: If you have figured out the O(n) solution, try coding another
 * solution using the divide and conquer approach, which is more subtle.
 *
 * Constraints:
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumSubarray_53 {
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

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            int ret = new MaximumSubarray_53().maxSubArray(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        if(nums != null && nums.length != 0) {
            int dp = result = nums[0];
            for(int i = 1; i < nums.length; i++) {
                dp = Math.max(dp + nums[i], nums[i]);
                if(dp > result) {
                    result = dp;
                }
            }
        }
        return result;
    }
}
