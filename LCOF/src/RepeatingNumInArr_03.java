/*
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。
 * 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。
 * 请找出数组中任意一个重复的数字。
 * 示例：
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：
 * 2 或 3
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

class RepeatingNumInArr_03 {
    private static int[] stringToIntegerArray(String input) {
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

            int ret = new RepeatingNumInArr_03().findRepeatNumber(nums);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }

    /*
     * Version 2
     * Using the hash function, it won't need to preoccupy the n-element space
     * at the beginning.
     * However, in practice, it's a little bit slower than version 1.
     */
    public int findRepeatNumber(int[] nums) {
        HashSet<Integer> hashSet = new HashSet<>();
        for(int num : nums) {
            if(!hashSet.add(num)) {
                return num;
            }
        }
        return 0;
    }
}