import java.util.ArrayList;
import java.util.List;

/**
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 * <p>
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 * <p>
 * Note:
 * <p>
 * All inputs are consist of lowercase letters a-z.
 * The values of words are distinct.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/word-search-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class WordSearchII_212 {
    private int rows;
    private int cols;
    private char[][] board;
    private List<String> result;
    private boolean[][] scanned;
    private Trie trie;

    public static void main(String[] args) {
        /*char[][] board = {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};*/

        /*char[][] board = {
                {'a', 'b'},
                {'c', 'd'}
        };
        String[] words = {"ab", "cb", "ad", "bd", "ac", "ca", "da", "bc", "db", "adcb", "dabc", "abb", "acb"};*/

        char[][] board = {
                {'a', 'b'},
                {'a', 'a'}
        };
        String[] words = {"aba","baa","bab","aaab","aaa","aaaa","aaba"};
        List<String> result = new WordSearchII_212().findWords(board, words);

        for(String s : result) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private void dfs(int i, int j, String word) {
        word += board[i][j];

        int r = trie.searchPrefix(word);
        if(r == -1) {
            return;
        } else if(r == 1) {
            if(!result.contains(word)) {
                result.add(word);
            }
        }

        scanned[i][j] = true;
        if(i > 0 && !scanned[i - 1][j]) {
            dfs(i - 1, j, word);
        }

        if(i < rows - 1 && !scanned[i + 1][j]) {
            dfs(i + 1, j, word);
        }

        if(j > 0 && !scanned[i][j - 1]) {
            dfs(i, j - 1, word);
        }

        if(j < cols - 1 && !scanned[i][j + 1]) {
            dfs(i, j + 1, word);
        }
        scanned[i][j] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        rows = board.length;
        cols = board[0].length;
        this.board = board;
        result = new ArrayList<>();
        scanned = new boolean[rows][cols];

        trie = new Trie();
        for(String word : words) {
            trie.insert(word);
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                dfs(i, j, "");
            }
        }

        return result;
    }

    private static class Trie {
        Node root;

        Trie() {
            root = new Node();
        }

        public void insert(String word) {
            Node p = root;
            for(char letter : word.toCharArray()) {
                if(p.alphabet[letter - 'a'] == null) {
                    p.alphabet[letter - 'a'] = new Node();
                }
                p = p.alphabet[letter - 'a'];
            }
            p.endWord = true;
        }

        public int searchPrefix(String prefix) {
            Node p = root;
            for(char letter : prefix.toCharArray()) {
                if(p.alphabet[letter - 'a'] == null) {
                    return -1;
                }
                p = p.alphabet[letter - 'a'];
            }
            if(p.endWord) {
                return 1;
            }
            return 0;
        }

        private static class Node {
            Node[] alphabet;
            boolean endWord;

            Node() {
                alphabet = new Node[26];
            }
        }
    }
}
