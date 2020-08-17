import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次
 * 可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
 * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人
 * 能够到达多少个格子？
 * <p>
 * 提示：
 * <p>
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MovingAreaOfARobot_13 {
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};
    private int[][] area;
    private int rows;
    private int cols;
    private int bound;
    private int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = in.readLine()) != null) {
            int m = Integer.parseInt(line);
            line = in.readLine();
            int n = Integer.parseInt(line);
            line = in.readLine();
            int k = Integer.parseInt(line);

            int ret = new MovingAreaOfARobot_13().movingCount(m, n, k);

            String out = String.valueOf(ret);

            System.out.println(out);
        }
    }

    private void dfs(int r, int c) {
        if(!validPos(r, c)) {
            return;
        }

        area[r][c] = 1;
        count++;
        for(int i = 0; i < 4; i++) {
            dfs(r + dr[i], c + dc[i]);
        }
    }

    private boolean validPos(int r, int c) {
        if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 || area[r][c] != 0) {
            return false;
        }
        if(digitSum(r) + digitSum(c) > bound) {
            area[r][c] = -1;
            return false;
        }
        return true;
    }

    private int digitSum(int n) {
        int res = 0;
        while(n > 0) {
            res += n % 10;
            n /= 10;
        }
        return res;
    }

    public int movingCount(int m, int n, int k) {
        /*
         * Version 1, DFS.
         */
        area = new int[m][n];
        rows = m;
        cols = n;
        bound = k;

        dfs(0, 0);
        return count;
    }
}
