import java.util.HashSet;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * <p>
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 * <p>
 * Constraints:
 * <p>
 * board and word consists only of lowercase and uppercase English letters.
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordSearch_79 {
    private HashSet<Integer> scanned;
    private char[][] board;
    private char[] word;
    private int rows;
    private int cols;

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(new WordSearch_79().exist(board, "ABCD"));
    }

    private boolean found(int i, int j, int curIdx) {
        if(curIdx > word.length - 1) {
            return true;
        }

        if(i > 0 && !scanned.contains((i - 1) * cols + j) &&
                board[i - 1][j] == word[curIdx]) {
            scanned.add((i - 1) * cols + j);
            if(found(i - 1, j, curIdx + 1)) {
                return true;
            } else {
                scanned.remove((i - 1) * cols + j);
            }
        }

        if(i < rows - 1 && !scanned.contains((i + 1) * cols + j) &&
                board[i + 1][j] == word[curIdx]) {
            scanned.add((i + 1) * cols + j);
            if(found(i + 1, j, curIdx + 1)) {
                return true;
            } else {
                scanned.remove((i + 1) * cols + j);
            }
        }

        if(j > 0 && !scanned.contains(i * cols + j - 1) &&
                board[i][j - 1] == word[curIdx]) {
            scanned.add(i * cols + j - 1);
            if(found(i, j - 1, curIdx + 1)) {
                return true;
            } else {
                scanned.remove(i * cols + j - 1);
            }
        }

        if(j < cols - 1 && !scanned.contains(i * cols + j + 1) &&
                board[i][j + 1] == word[curIdx]) {
            scanned.add(i * cols + j + 1);
            if(found(i, j + 1, curIdx + 1)) {
                return true;
            } else {
                scanned.remove(i * cols + j + 1);
            }
        }

        return false;
    }

    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.word = word.toCharArray();
        rows = board.length;
        cols = board[0].length;
        scanned = new HashSet<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(board[i][j] == word.charAt(0)) {
                    scanned.add(i * cols + j);
                    if(found(i, j, 1)) {
                        return true;
                    } else {
                        scanned.remove(i * cols + j);
                    }
                }
            }
        }
        return false;
    }
}
