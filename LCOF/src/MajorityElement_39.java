import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * <p>
 * 限制：
 * 1 <= 数组长度 <= 50000
 * <p>
 * 注意：本题与主站 169 题相同：https://leetcode-cn.com/problems/majority-element/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MajorityElement_39 {
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

            int ret = new MajorityElement_39().majorityElement(nums);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int majorityElement(int[] nums) {
        /*
         * Version 1, HashMap
         */
        /*HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums) {
            if(!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                int t = map.get(num) + 1;
                map.put(num, t);
                if(t > nums.length / 2) {
                    return num;
                }
            }
        }
        return nums[nums.length - 1];*/

        /*
         * Version 2, a tricky method. After sorting, the majority element must appear
         * at the position n / 2.
         */
        /*Arrays.sort(nums);
        return nums[nums.length / 2];*/

        /*
         * Version 3, Boyer-Moore voting algorithm.
         */
        int candidate = nums[0];
        int vote = 1;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == candidate) {
                vote++;
            } else {
                vote--;
                if(vote == 0) {
                    candidate = nums[i];
                    vote = 1;
                }
            }
        }
        return candidate;
    }
}
