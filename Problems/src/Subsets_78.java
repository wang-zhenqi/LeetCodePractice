import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power
 * set).
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/subsets
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Subsets_78 {
    private List<List<Integer>> result;

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

    public static String integerArrayListToString(List<Integer> nums, int length) {
        if (length == 0) {
            return "[]";
        }

        String result = "";
        for(int index = 0; index < length; index++) {
            Integer number = nums.get(index);
            result += Integer.toString(number) + ", ";
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }

    public static String integerArrayListToString(List<Integer> nums) {
        return integerArrayListToString(nums, nums.size());
    }

    public static String int2dListToString(List<List<Integer>> nums) {
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

            List<List<Integer>> ret = new Subsets_78().subsets(nums);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

    private void dfs(int[] nums, int index, boolean choose, List<Integer> set) {
        if(index == nums.length - 1) {
            if(choose) {
                set.add(nums[index]);
            }
            result.add(new ArrayList<>(set));
            return;
        }

        if(choose) {
            set.add(nums[index]);
        }
        int len = set.size();

        dfs(nums, index + 1, true, set);
        if(set.size() > len) {
            set.remove(set.size() - 1);
        }

        dfs(nums, index + 1, false, set);
        if(set.size() > len) {
            set.remove(set.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        /*
         * Version 1, DFS, recursively.
         * For each element in the set, it can be chosen or not. So when all the
         * possibilities are visited, the result is found.
         */
        result = new ArrayList<>();
        List<Integer> set = new ArrayList<>();
        dfs(nums, 0, true, set);
        if(set.size() > 0) {
            set.remove(set.size() - 1);
        }

        dfs(nums, 0, false, set);
        if(set.size() > 0) {
            set.remove(set.size() - 1);
        }
        return result;
    }
}
