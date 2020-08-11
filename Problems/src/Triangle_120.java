import java.util.ArrayList;
import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * The adjacent number means the same index as the upper row, or the index + 1 of
 * the upper row.
 *
 * Note:
 *
 * Bonus point if you are able to do this using only O(n) extra space,
 * where n is the total number of rows in the triangle.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/triangle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Triangle_120 {
    public static void main(String[] args) {
        int[][] data = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 8, 6, 3}
        };

        List<List<Integer>> triangle = new ArrayList<>();
        for(int[] datum : data) {
            List<Integer> row = new ArrayList<>();
            for(int i : datum) {
                row.add(i);
            }
            triangle.add(row);
        }

        int ret = new Triangle_120().minimumTotal(triangle);

        String out = String.valueOf(ret);

        System.out.println(out);
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        /*
         * Version 2, spatial optimized DP.
         * dp[0] ~ dp[i] represents the minimum sum from bottom to
         * triangle[i][0] ~ triangle[i][i] respectively.
         * dp[j] = min(dp[j], dp[j+1]) + triangle[i][j].
         * dp[0] is the answer.
         */
        int rowNum = triangle.size();
        int[] dp = new int[rowNum];

        for(int j = 0 ; j < rowNum; j++) {
            dp[j] = triangle.get(rowNum - 1).get(j);
        }

        for(int i = rowNum - 2; i >= 0; i--) {
            for(int j = 0 ; j < i + 1; j++) {
                dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j);
            }
        }
        return dp[0];
    }
}
