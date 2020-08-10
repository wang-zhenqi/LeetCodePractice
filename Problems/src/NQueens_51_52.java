import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The n-queens puzzle is the problem of placing n queens on an
 * n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 *
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * P.S. This problem is very similar to the problem 52. So I combined them together.
 */
public class NQueens_51_52 {
    private List<List<String>> result;
    private int n;
    private int solutions;

    public static void main(String[] args) {
        List<List<String>> result = new NQueens_51_52().solveNQueens(8);

        for(List<String> solution : result) {
            for(String line : solution) {
                System.out.println(line);
            }
            System.out.println('\n');
        }
    }

    private void dfs(int row, List<String> lines, int col, int slash, int backSlash) {
        if(row >= n) {
            solutions++;
            result.add(new ArrayList<>(lines));
            return;
        }

        int pos = (~(col | slash | backSlash)) & ((1 << n) - 1);
        while(pos != 0) {
            int p = pos & (-pos);
            StringBuilder line = new StringBuilder();
            for(int i = n - 1; i >= 0; i--) {
                if((p & (1 << i)) == 0) {
                    line.append('.');
                } else {
                    line.append('Q');
                }
            }
            lines.add(line.toString());
            dfs(row + 1, lines, col | p, (slash | p) << 1, (backSlash | p) >> 1);
            pos &= (pos - 1);
            lines.remove(lines.size() - 1);
        }
    }

    public List<List<String>> solveNQueens(int n) {
        /*
         * Version 2, DFS + bit operation.
         */
        result = new ArrayList<>();

        this.n = n;
        solutions = 0;

        List<String> lines = new ArrayList<>();
        dfs(0, lines, 0, 0, 0);

        System.out.println("Total solutions: " + solutions);
        return result;
    }
}
