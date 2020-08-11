/**
 * A robot is located at the top-left corner of a m x n grid.
 * <p>
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 * <p>
 * Now consider if some obstacles are added to the grids. How many unique paths
 * would there be?
 * <p>
 * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
 * <p>
 * Note: m and n will be at most 100.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/unique-paths-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class UniquePathsII_63 {
    public static void main(String[] args) {
        int[][] obstacleGrid = {
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0},
                {1, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 1, 0},
                {0, 1, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}
        };

        int ret = new UniquePathsII_63().uniquePathsWithObstacles(obstacleGrid);

        String out = String.valueOf(ret);

        System.out.println(out);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        if(obstacleGrid[rows - 1][cols - 1] == 1) {
            return 0;
        }

        int[][] paths = new int[rows][cols];
        paths[rows - 1][cols - 1] = 1;

        for(int i = rows - 1; i >= 0; i--) {
            for(int j = cols - 1; j >= 0; j--) {
                if(obstacleGrid[i][j] == 1) {
                    paths[i][j] = 0;
                    continue;
                }

                if(i < rows - 1) {
                    paths[i][j] += paths[i + 1][j];
                }
                if(j < cols - 1) {
                    paths[i][j] += paths[i][j + 1];
                }
            }
        }
        return paths[0][0];
    }
}
