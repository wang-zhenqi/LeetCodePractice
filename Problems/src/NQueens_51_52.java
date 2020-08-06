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
    private Set<Integer> colSet;
    private Set<Integer> slashSet;
    private Set<Integer> backSlashSet;
    private int n;
    private int solutions;

    public static void main(String[] arge) {
        List<List<String>> result = new NQueens_51_52().solveNQueens(8);

        for(List<String> solution : result) {
            for(String line : solution) {
                System.out.println(line);
            }
            System.out.println('\n');
        }
    }

    private void dfs(int row, List<String> lines) {
        for(int col = 0; col < n; col++) {
            if(!colSet.contains(col) &&
                    !slashSet.contains(row + col) &&
                    !backSlashSet.contains(row - col)) {
                colSet.add(col);
                slashSet.add(row + col);
                backSlashSet.add(row - col);
                StringBuilder line = new StringBuilder();
                for(int i = 0; i < n; i++) {
                    if(i != col) {
                        line.append(".");
                    } else {
                        line.append("Q");
                    }
                }
                lines.add(line.toString());
                if(row < n - 1) {
                    dfs(row + 1, lines);
                } else {
                    List<String> solution = new ArrayList<>(lines);
                    result.add(solution);
                    solutions++;
                }
                colSet.remove(col);
                slashSet.remove(row + col);
                backSlashSet.remove(row - col);
                lines.remove(lines.size() - 1);
            }
        }
    }

    public List<List<String>> solveNQueens(int n) {
        /*
         * Version 1, DFS pruning.
         */
        result = new ArrayList<>();
        colSet = new HashSet<>();
        slashSet = new HashSet<>();
        backSlashSet = new HashSet<>();
        this.n = n;
        solutions = 0;

        List<String> lines = new ArrayList<>();
        dfs(0, lines);

        System.out.println("Total solutions: " + solutions);
        return result;
    }
}
