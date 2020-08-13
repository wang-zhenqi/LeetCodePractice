import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 * <p>
 * A sudoku solution must satisfy all of the following rules:
 * <p>
 * Each of the digits 1-9 must occur exactly once in each row.
 * Each of the digits 1-9 must occur exactly once in each column.
 * Each of the the digits 1-9 must occur exactly once in each of the 9
 * 3x3 sub-boxes of the grid.
 * Empty cells are indicated by the character '.'.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sudoku-solver
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SudokuSolver_37 {
    private int[][] rows;
    private int[][] cols;
    private int[][] blocks;
    private Candidates[] candidates;
    private char[][] board;

    public static void main(String[] arg) {
        char[][] board1 = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        new SudokuSolver_37().solveSudoku(board1);
    }

    private boolean dfs(int candIdx) {
        if(candIdx >= 81) {
            return true;
        }

        int row = candidates[candIdx].index / 9;
        int col = candidates[candIdx].index % 9;

        for(int k = 0; k < candidates[candIdx].cand.size(); k++) {
            int tryNumber = candidates[candIdx].cand.get(k);
            int[] block = blocks[row / 3 * 3 + col / 3];

            if((rows[row][tryNumber - 1] == 0) &&
                    (cols[col][tryNumber - 1] == 0) &&
                    (block[tryNumber - 1] == 0)) {
                board[row][col] = (char) (tryNumber + '0');
                rows[row][tryNumber - 1] = 1;
                cols[col][tryNumber - 1] = 1;
                block[tryNumber - 1] = 1;

                if(dfs(candIdx + 1)) {
                    return true;
                } else {
                    board[row][col] = '.';
                    rows[row][tryNumber - 1] = 0;
                    cols[col][tryNumber - 1] = 0;
                    block[tryNumber - 1] = 0;
                }
            }
        }
        return false;
    }

    public void solveSudoku(char[][] board) {
        rows = new int[9][9];
        cols = new int[9][9];
        blocks = new int[9][9];
        candidates = new Candidates[81];
        this.board = board;

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') {
                    continue;
                }

                int[] block = blocks[i / 3 * 3 + j / 3];
                int cellValue = board[i][j] - '0';

                rows[i][cellValue - 1] = 1;
                cols[j][cellValue - 1] = 1;
                block[cellValue - 1] = 1;
            }
        }

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                candidates[i * 9 + j] = new Candidates(i * 9 + j);

                if(board[i][j] == '.') {
                    for(int k = 1; k <= 9; k++) {
                        int[] block = blocks[i / 3 * 3 + j / 3];

                        if((rows[i][k - 1] == 0) &&
                                (cols[j][k - 1] == 0) &&
                                (block[k - 1] == 0)) {
                            candidates[i * 9 + j].cand.add(k);
                        }
                    }
                }
            }
        }

        Arrays.sort(candidates, new MyComparator());

        int candIdx = 0;
        while(candidates[candIdx].cand.size() == 0) {
            candIdx++;
        }

        StringBuilder sb = new StringBuilder();
        if(dfs(candIdx)) {
            sb.append('[');
            for(int i = 0; i < 9; i++) {
                sb.append('[');
                for(int j = 0; j < 9; j++) {
                    sb.append("\"").append(board[i][j]).append("\"");
                    if(j != 8) {
                        sb.append(',');
                    }
                }
                sb.append(']');
                if(i != 8) {
                    sb.append(',');
                }
            }
            sb.append(']');
        }
        System.out.println(sb.toString());
    }

    private static class Candidates {
        int index;
        List<Integer> cand;

        Candidates(int i) {
            index = i;
            cand = new ArrayList<>();
        }
    }

    private static class MyComparator implements Comparator<Candidates> {
        @Override
        public int compare(Candidates o1, Candidates o2) {
            return Integer.compare(o1.cand.size(), o2.cand.size());
        }
    }
}