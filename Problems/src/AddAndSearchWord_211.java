/**
 * Design a data structure that supports the following two operations:
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string
 * containing only letters a-z or '.'. A '.' means it can represent any one letter.
 * <p>
 * Note:
 * You may assume that all words are consist of lowercase letters a-z.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-and-search-word-data-structure-design
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddAndSearchWord_211 {
    private final Node root;

    /**
     * Initialize your data structure here.
     */
    public AddAndSearchWord_211() {
        root = new Node();
    }

    public static void main(String[] args) {
        AddAndSearchWord_211 trie = new AddAndSearchWord_211();
        trie.addWord("bad");
        trie.addWord("dad");
        trie.addWord("mad");
        System.out.println("search \"pad\": " + trie.search("pad"));
        System.out.println("search \"bad\": " + trie.search("bad"));
        System.out.println("search \".ad\": " + trie.search(".ad"));
        System.out.println("search \"b..\": " + trie.search("b.."));
    }

    private boolean searchFromHere(Node level, String subSequence) {
        int len = subSequence.length();
        if(len == 0) {
            return level.wordEnd;
        }

        char c = subSequence.charAt(0);
        if(c == '.') {
            for(int i = 0; i < 26; i++) {
                if(level.alphabet[i] != null) {
                    if(searchFromHere(level.alphabet[i], len == 1 ? "" : subSequence.substring(1))) {
                        return true;
                    }
                }
            }
        } else {
            if(level.alphabet[c - 'a'] == null) {
                return false;
            }
            return searchFromHere(level.alphabet[c - 'a'], len == 1 ? "" : subSequence.substring(1));
        }
        return false;
    }

    /**
     * Adds a word into the data structure.
     */
    public void addWord(String word) {
        Node p = root;
        for(char c : word.toCharArray()) {
            if(p.alphabet[c - 'a'] == null) {
                p.alphabet[c - 'a'] = new Node();
            }
            p = p.alphabet[c - 'a'];
        }
        p.wordEnd = true;
    }

    /**
     * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
     */
    public boolean search(String word) {
        return searchFromHere(root, word);
    }

    private class Node {
        Node[] alphabet;
        boolean wordEnd;

        Node() {
            alphabet = new Node[26];
        }
    }
}
