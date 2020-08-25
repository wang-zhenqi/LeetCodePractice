import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Given an integer array, your task is to find all the different possible
 * increasing subsequences of the given array, and the length of an increasing
 * subsequence should be at least 2.
 *
 * Constraints:
 *
 * The length of the given array will not exceed 15.
 * The range of integer in the given array is [-100,100].
 * The given array may contain duplicates, and two equal integers should also be
 * considered as a special case of increasing sequence.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/increasing-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class IncreasingSubsequences_491 {
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

    private static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
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

    private static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for (List<Integer> list: nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int[] nums = stringToIntegerArray(line);

            List<List<Integer>> ret = new IncreasingSubsequences_491().findSubsequences(nums);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        HashSet<List<Integer>>[] map = new HashSet[nums.length];
        HashSet<List<Integer>> result = new HashSet<>();
        for(int i = 1; i < nums.length; i++) {
            HashSet<List<Integer>> set = new HashSet<>();
            for(int j = i - 1; j >= 0; j--) {
                if(nums[j] <= nums[i]) {
                    {
                        List<Integer> list = new ArrayList<>();
                        list.add(nums[j]);
                        list.add(nums[i]);
                        set.add(list);
                    }
                    HashSet<List<Integer>> entries = map[j];
                    if(entries != null && !entries.isEmpty()) {
                        for(List<Integer> entry : entries) {
                            List<Integer> element = new ArrayList<>(entry);
                            element.add(nums[i]);
                            set.add(element);
                        }
                    }
                }
            }
            map[i] = set;
            result.addAll(set);
        }
        return new ArrayList<>(result);
    }
}
