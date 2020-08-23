/**
 * Let's play the minesweeper game (Wikipedia, online game)!
 * <p>
 * You are given a 2D char matrix representing the game board. 'M' represents an
 * unrevealed mine, 'E' represents an unrevealed empty square, 'B' represents a
 * revealed blank square that has no adjacent (above, below, left, right, and all
 * 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to
 * this revealed square, and finally 'X' represents a revealed mine.
 * <p>
 * Now given the next click position (row and column indices) among all the
 * unrevealed squares ('M' or 'E'), return the board after revealing this position
 * according to the following rules:
 * <p>
 * If a mine ('M') is revealed, then the game is over - change it to 'X'.
 * If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 * If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 * Return the board when no more squares will be revealed.
 * <p>
 * Note:
 * The range of the input matrix's height and width is [1,50].
 * The click position will only be an unrevealed square ('M' or 'E'), which also
 * means the input board contains at least one clickable square.
 * The input board won't be a stage when game is over (some mines have been
 * revealed).
 * For simplicity, not mentioned rules should be ignored in this problem. For
 * example, you don't need to reveal all the unrevealed mines when the game is
 * over, consider any cases that you will win the game or flag any squares.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minesweeper
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Minesweeper_529 {
    private final int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    private final int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    private int rows;
    private int cols;
    private char[][] board;

    public static void main(String[] args) {
        char[][] board1 = {
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };
        int[] click1 = {3, 0};

        char[][] board2 = {
                {'B', '1', 'E', '1', 'B'},
                {'B', '1', 'M', '1', 'B'},
                {'B', '1', '1', '1', 'B'},
                {'B', 'B', 'B', 'B', 'B'}
        };
        int[] click2 = {0, 2};
        char[][] board = new Minesweeper_529().updateBoard(board2, click2);

        for(char[] chars : board) {
            for(int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + (j == chars.length - 1 ? "" : " "));
            }
            System.out.println();
        }
    }

    private int countMines(int r, int c) {
        int count = 0;
        for(int i = 0; i < 8; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'M') {
                count++;
            }
        }
        return count;
    }

    private void dfs(int r, int c) {
        if(r < 0 || r >= rows || c < 0 || c >= cols || board[r][c] != 'E') {
            return;
        }

        int adjMines = countMines(r, c);
        if(adjMines == 0) {
            board[r][c] = 'B';
            for(int i = 0; i < 8; i++) {
                dfs(r + dr[i], c + dc[i]);
            }
        } else {
            board[r][c] = (char) ('0' + adjMines);
        }
    }

    public char[][] updateBoard(char[][] board, int[] click) {
        this.board = board;
        rows = board.length;
        cols = board[0].length;
        int r = click[0], c = click[1];

        if(board[r][c] == 'M') {
            board[r][c] = 'X';
        } else {
            dfs(r, c);
        }
        return board;
    }
}
