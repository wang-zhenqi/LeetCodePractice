import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given an integer array nums, find the contiguous subarray within an array
 * (containing at least one number) which has the largest product.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaximumProductSubarray_152 {
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

            int ret = new MaximumProductSubarray_152().maxProduct(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int maxProduct(int[] nums) {
        /*
         * DP. In each iteration, dp[0] means the maximum product, dp[1] means the
         * minimum product, and ans records the maximum product so far.
         * After the whole process, return ans.
         */

        int[] dp = new int[2];
        int ans = nums[0];
        dp[0] = nums[0];
        dp[1] = nums[0];

        int tmp;
        for(int i = 1; i < nums.length; i++) {
            tmp = dp[0];
            dp[0] = Math.max(nums[i], Math.max(dp[0] * nums[i], dp[1] * nums[i]));
            dp[1] = Math.min(nums[i], Math.min(tmp * nums[i], dp[1] * nums[i]));
            ans = Math.max(dp[0], ans);
        }

        return ans;
    }
}
