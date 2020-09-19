import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 统计一个数字在排序数组中出现的次数。
 *
 * 限制：
 * 0 <= 数组长度 <= 50000
 *
 * 注意：本题与主站 34 题相同（仅返回值不同）：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zai-pai-xu-shu-zu-zhong-cha-zhao-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class FindNumberInSortedArrayI_53_I {
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
            line = in.readLine();
            int target = Integer.parseInt(line);

            int ret = new FindNumberInSortedArrayI_53_I().search(nums, target);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int search(int[] nums, int target) {
        int len = nums.length;
        if(len == 0) {
            return 0;
        }
        int left = 0, right = len - 1;
        int foundIndex = -1;
        int result = 0;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                foundIndex = mid;
                result = 1;
                break;
            }
            if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        if(foundIndex == - 1) {
            return 0;
        }
        int i = foundIndex - 1;
        while(i >= 0 && nums[i] == target) {
            result++;
            i--;
        }
        i = foundIndex + 1;
        while(i < len && nums[i] == target) {
            result++;
            i++;
        }
        return result;
    }
}
