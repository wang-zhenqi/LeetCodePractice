/**
 * 一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的
 * n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 *
 * 限制：
 * 1 <= 数组长度 <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/que-shi-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MissingNumber_53_II {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 5};
        System.out.println(new MissingNumber_53_II().missingNumber(nums));
    }

    public int missingNumber(int[] nums) {
        /*
         * Version 2, binary search.
         * When looking for some element at a sorted array, the first solution that
         * occurs should be the binary search.
         * Watch out for the boundaries.
         */
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int mid = i + (j - i) / 2;
            if(nums[mid] == mid) {
                i = mid + 1;
            } else {
                j = mid - 1;
            }
        }
        return i;
    }
}
