/**
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每
 * 一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，
 * 在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * <p>
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * <p>
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入
 * 这个格子。
 * <p>
 * 提示：
 * <p>
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class PathInAMatrix_12 {
    private final int[] dr = {-1, 1, 0, 0};
    private final int[] dc = {0, 0, -1, 1};
    private int rows;
    private int cols;
    private char[] word;
    private char[][] board;

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        /*char[][] board = {
                {'A'}
        };*/
        /*char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'E', 'S'},
                {'A', 'D', 'E', 'E'}
        };*/
        System.out.println(new PathInAMatrix_12().exist(board, "ABFDECCES"));
    }

    public boolean exist(char[][] board, String word) {
        /*
         * DFS.
         * By the way, BFS will not work.
         */
        if(board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        rows = board.length;
        cols = board[0].length;

        this.word = word.toCharArray();
        this.board = board;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == word.charAt(0)) {
                    if(dfs(i, j, 0)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean dfs(int r, int c, int index) {
        if(r < 0 || r > rows - 1 || c < 0 || c > cols - 1 ||
                board[r][c] != word[index]) {
            return false;
        }

        if(index == word.length - 1) {
            return true;
        }

        char tmp = board[r][c];
        board[r][c] = '.';

        for(int i = 0; i < 4; i++) {
            if(dfs(r + dr[i], c + dc[i], index + 1)) {
                return true;
            }
        }
        board[r][c] = tmp;

        return false;
    }
}
