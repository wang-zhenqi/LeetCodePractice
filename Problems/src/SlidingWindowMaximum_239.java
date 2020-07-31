import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given an array nums, there is a sliding window of size k which is moving
 * from the very left of the array to the very right.
 * You can only see the k numbers in the window. Each time the sliding window
 * moves right by one position. Return the max element of the sliding window.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sliding-window-maximum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

public class SlidingWindowMaximum_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> windowIndices = new LinkedList<>();
        int i = 0;
        for(int curIndex = 0; curIndex < nums.length; curIndex++) {
            if(!windowIndices.isEmpty() && curIndex - windowIndices.peekFirst() == k) {
                windowIndices.pollFirst();
            }

            while(!windowIndices.isEmpty() && nums[curIndex] > nums[windowIndices.peekLast()]) {
                windowIndices.pollLast();
            }

            windowIndices.offerLast(curIndex);
            if(curIndex >= k - 1) {
                result[i++] = nums[windowIndices.peekFirst()];
            }
        }
        return result;
    }
}

class MainClass_239 {
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

    public static String integerArrayToString(int[] nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            int number = nums[index];
            result += number + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayToString(int[] nums) {
        return integerArrayToString(nums, nums.length);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int[] ret = new SlidingWindowMaximum_239().maxSlidingWindow(nums, k);

            String out = integerArrayToString(ret);

            System.out.print(out);
        }
    }
}