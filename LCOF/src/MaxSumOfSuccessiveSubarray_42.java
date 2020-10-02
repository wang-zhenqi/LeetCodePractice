import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * 要求时间复杂度为O(n)。
 * <p>
 * 提示：
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 * <p>
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxSumOfSuccessiveSubarray_42 {
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
            int ret = new MaxSumOfSuccessiveSubarray_42().maxSubArray(nums);
            String out = String.valueOf(ret);
            System.out.println(out);
        }
    }

    /*
     * Version 1, DP.
     * Assume that dp[i] represents the maximum sum of the subarray that is ended up
     * with the element nums[i].
     * dp[0] = nums[0]
     * dp[i] = max(dp[i - 1] + nums[i], nums[i]) (i > 0 && i < nums.length)
     * result = max(dp[0], dp[1], ..., dp[n - 1])
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;
        if(nums != null && nums.length != 0) {
            int[] dp = new int[nums.length];
            result = dp[0] = nums[0];
            for(int i = 1; i < dp.length; i++) {
                dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
                if(dp[i] > result) {
                    result = dp[i];
                }
            }
        }
        return result;
    }
}
