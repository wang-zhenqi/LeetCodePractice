import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个
 * 数组中的逆序对的总数。
 * <p>
 * 限制：0 <= 数组长度 <= 50000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ReversePair_51 {
    public static int[] stringToIntegerArray(String input) {
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

            int ret = new ReversePair_51().reversePairs(nums);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }

    public int reversePairs(int[] nums) {
        /*
         * Version 2, Divide and Conquer, Merge sort, time complexity: O(NlogN).
         * It should be noticed that for an element nums[i], no matter how the
         * right-hand side elements are arranged, the number of reverse pairs
         * (nums[i], nums[j]) (0 <= i < j < nums.length) is always determined.
         * So this problem can be changed to a merge sort problem.
         */
        int len = nums.length;
        if(len <= 1) {
            return 0;
        }
        return reversePairs(nums, 0, len);
    }

    private int reversePairs(int[] nums, int left, int right) {
        if(left == right - 1) {
            return 0;
        }

        int[] sorted = new int[right - left];
        int result = 0;
        int t = 0;
        int l = left;
        int m = left + (right - left) / 2;
        int r = m;

        result += reversePairs(nums, left, m);
        result += reversePairs(nums, m, right);

        while(l < m && r < right) {
            if(nums[l] > nums[r]) {
                result += (m - l);
                sorted[t++] = nums[r++];
            } else {
                sorted[t++] = nums[l++];
            }
        }
        while(l < m) {
            sorted[t++] = nums[l++];
        }
        while(r < right) {
            sorted[t++] = nums[r++];
        }
        System.arraycopy(sorted, 0, nums, left, right - left);

        return result;
    }
}
