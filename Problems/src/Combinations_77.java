import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of
 * 1 ... n.
 * <p>
 * You may return the answer in any order.
 * <p>
 * Constraints:
 * 1 <= n <= 20
 * 1 <= k <= n
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/combinations
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Combinations_77 {
    private List<List<Integer>> result;
    private int k;

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

    private static String int2dListToString(List<List<Integer>> nums) {
        StringBuilder sb = new StringBuilder("[");
        for(List<Integer> list : nums) {
            sb.append(integerArrayListToString(list));
            sb.append(",");
        }

        sb.setCharAt(sb.length() - 1, ']');
        return sb.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int n = Integer.parseInt(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            List<List<Integer>> ret = new Combinations_77().combine(n, k);

            String out = int2dListToString(ret);

            System.out.print(out);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        this.k = k;
        result = new ArrayList<>();
        if(n < k) {
            return result;
        }

        if(n == k) {
            List<Integer> element = new ArrayList<>();
            for(int i = 0; i < n; i++) {
                element.add(i + 1);
            }
            result.add(element);
            return result;
        }

        int[] arr = new int[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        List<Integer> selected = new ArrayList<>();
        pickup(arr, 0, selected);
        return result;
    }

    private void pickup(int[] arr, int startIndex, List<Integer> selected) {
        if(selected.size() == k) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for(int i = startIndex; i <= arr.length - k + selected.size(); i++) {
            selected.add(arr[i]);
            pickup(arr, i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }
}
