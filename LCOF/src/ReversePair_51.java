import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

            System.out.print(out);
        }
    }

    public int reversePairs(int[] nums) {
        /*
         * Version 1, brutal force, but too time consuming, OT.
         */
        int totalNum = nums.length;
        int[] counts = new int[totalNum];
        ArrayList<Integer> greaterThan = new ArrayList<>();
        for(int current = 0; current < totalNum - 1; current++) {
            if(counts[current] > 0) {
                continue;
            }
            for(int compare = current + 1; compare < totalNum; compare++) {
                if(nums[compare] < nums[current]) {
                    counts[current]++;
                } else {
                    greaterThan.add(compare);
                    counts[compare] = -counts[current];
                    for(int numIdx : greaterThan) {
                        if(nums[compare] < nums[numIdx]) {
                            counts[numIdx]++;
                        }
                    }
                }
            }
            for(int numIdx : greaterThan) {
                counts[numIdx] += counts[current];
            }
            greaterThan.clear();
        }
        int result = 0;
        for(int i = 0; i < totalNum - 1; i++) {
            result += counts[i];
        }
        return result;
    }
}
