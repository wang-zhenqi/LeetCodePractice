import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Given an unsorted array of integers, find the length of longest increasing
 * subsequence.
 *
 * Note:
 *
 * There may be more than one LIS combination, it is only necessary for you to
 * return the length.
 * Your algorithm should run in O(n^2) complexity.
 *
 * Follow up: Could you improve it to O(nlogn) time complexity?
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-increasing-subsequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestIncreasingSubsequence_300 {
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

            int ret = new LongestIncreasingSubsequence_300().lengthOfLIS(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int lengthOfLIS(int[] nums) {
        /*
         * Version 1, DP with pruning.
         * Assume that subSeqLen[i] is the length of the longest increasing
         * subsequence which starts from nums[i].
         *
         * subSeqLen[i] = max{subSeqLen[j]} + 1, where j > i && nums[j] > nums[i].
         *
         * result = max{subSeqLen[i]}, where i is from 0 to nums.length - 1.
         *
         * initialize:
         * subSeqLen[i] = 1
         */
        if(nums == null) {
            return 0;
        }
        int len = nums.length;
        if(len <= 1) {
            return len;
        }

        int[] subSeqLen = new int[len];
        Arrays.fill(subSeqLen, 1);

        int max = 1, nearest;
        for(int i = len - 2; i >= 0; i--) {
            /*
             * nearest = min{nums[j]}, where j > i && nums[j] > nums[i].
             * By defining this variable, the operation will be simplified, but the
             * time complexity won't change.
             */
            nearest = Integer.MAX_VALUE;
            for(int j = i + 1; j < len; j++) {
                if(nums[j] > nums[i] && nums[j] < nearest) {
                    subSeqLen[i] = Math.max(subSeqLen[i], 1 + subSeqLen[j]);
                    nearest = nums[j];
                }
            }
            max = Math.max(max, subSeqLen[i]);
        }

        return max;
    }
}
