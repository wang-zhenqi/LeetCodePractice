/**
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。你可以从棋盘的左上角开始
 * 拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。给定一个棋盘及其上面的礼物的价值，请计算
 * 你最多能拿到多少价值的礼物？
 * <p>
 * 提示：
 * 0 < grid.length <= 200
 * 0 < grid[0].length <= 200
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/li-wu-de-zui-da-jie-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MaxValueOfGifts_47 {
    public static void main(String[] args) {
        int[][][] grids = {
                {
                        {1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}
                }
        };

        MaxValueOfGifts_47 obj = new MaxValueOfGifts_47();
        for(int[][] grid : grids) {
            System.out.println(obj.maxValue(grid));
        }
    }

    public int maxValue(int[][] grid) {
        int rows = grid.length;
        if(rows == 0) {
            return 0;
        }
        int cols = grid[0].length;
        if(cols == 0) {
            return 0;
        }

        //Original states array:
        /*int[][] values = new int[rows][cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int up = (i == 0) ? 0 : values[i - 1][j];
                int left = (j == 0) ? 0 : values[i][j - 1];
                values[i][j] = Math.max(up, left) + grid[i][j];
            }
        }
        return values[rows - 1][cols - 1];*/

        //Simplified states array:
        int[] values = new int[cols];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                values[j] = Math.max(
                        (i == 0) ? 0 : values[j],
                        (j == 0) ? 0 : values[j - 1]
                ) + grid[i][j];
            }
        }
        return values[cols - 1];
    }
}
