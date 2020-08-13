import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
    /**
     * Insert the element ino the array lis in increasing order.
     * @param lis the longest increasing subsequence, so far.
     * @param length the last element that is actually in the lis.
     * @param element the element to be inserted.
     * @return return the actual current length of the lis.
     */
    private int binaryInsert(int[] lis, int length, int element) {
        if(length == 0) {
            lis[0] = element;
            return 1;
        }

        int leftBound = 0, midElement, rightBound = length - 1;
        while(leftBound < rightBound) {
            midElement = leftBound + (rightBound - leftBound) / 2;
            if(lis[midElement] >= element) {
                rightBound = midElement;
            } else {
                leftBound = midElement + 1;
            }
        }
        if(lis[rightBound] < element) {
            lis[rightBound + 1] = element;
            return length + 1;
        } else {
            lis[rightBound] = element;
            return length;
        }
    }

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
         * Version 2, a tricky method.
         * Define a array called lis(longest increasing subsequence) to simulate
         * the process of finding the LIS.
         * The array lis stores the possible subsequence, but not necessarily the
         * exact LIS of the whole array.
         * The basic idea is trying to put the smallest elements into the array in
         * the right order.
         *
         * The process is as follows:
         * for i in 0 to nums.length - 1:
         *     if lis is empty:
         *         lis.add(nums[i])
         *     else:
         *         if nums[i] > all elements in lis:
         *             lis.add(nums[i])
         *         else:
         *             // This step use binary search to reduce the time complexity
         *             replace the smallest element in lis that is larger than nums[i]
         * return lis.length
         *
         * The reason why this method can work is that replacing an element with a
         * smaller one doesn't affect the length of lis.
         */
        if(nums == null) {
            return 0;
        }
        int len = nums.length;
        if(len <= 1) {
            return len;
        }

        int[] lis = new int[len];
        int currentLen = 0;
        for(int num : nums) {
            currentLen = binaryInsert(lis, currentLen, num);
        }

        return currentLen;
    }
}
