import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A robot is located at the top-left corner of a m x n grid.
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 *
 * How many possible unique paths are there?
 *
 * Constraints:
 * 1 <= m, n <= 100
 * It's guaranteed that the answer will be less than or equal to 2 * 10 ^ 9.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniquePaths_62 {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line = in.readLine()) != null) {
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);

            int ret = new UniquePaths_62().uniquePaths(m, n);

            String out = String.valueOf(ret);

            System.out.print(out);
        }
    }

    public int uniquePaths(int m, int n) {
        /*
         * Version 2, DP.
         */
        if(m == 1 || n == 1) {
            return 1;
        }

        int[][] paths = new int[n][m];
        for(int i = n - 1; i >= 0; i--) {
            paths[i][m - 1] = 1;
        }

        for(int j = m - 2; j >= 0; j--) {
            paths[n - 1][j] = 1;
        }

        for(int i = n - 2; i >= 0; i--) {
            for(int j = m - 2; j >= 0; j--) {
                paths[i][j] = paths[i + 1][j] + paths[i][j + 1];
            }
        }
        return paths[0][0];
    }
}
