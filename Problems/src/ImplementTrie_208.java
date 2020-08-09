/**
 * Implement a trie with insert, search, and startsWith methods.
 * <p>
 * Note:
 * You may assume that all inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-trie-prefix-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ImplementTrie_208 {
    private final TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public ImplementTrie_208() {
        root = new TrieNode();
    }

    public static void main(String[] args) {
        ImplementTrie_208 trie = new ImplementTrie_208();
        System.out.println("insert apple");
        trie.insert("apple");
        System.out.println("search apple: " + trie.search("apple"));   // returns true
        System.out.println("search app: " + trie.search("app"));     // returns false
        System.out.println("startwith app: " + trie.startsWith("app"));      // returns true
        System.out.println("insert app");
        trie.insert("app");
        System.out.println("search app: " + trie.search("app"));     // returns true
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode pointer = root;
        for(char letter : word.toCharArray()) {
            if(pointer.alphabet[letter - 'a'] == null) {
                pointer.alphabet[letter - 'a'] = new TrieNode();
            }
            pointer = pointer.alphabet[letter - 'a'];
        }
        pointer.endOfWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode pointer = root;
        for(char letter : word.toCharArray()) {
            if(pointer.alphabet[letter - 'a'] == null) {
                return false;
            }
            pointer = pointer.alphabet[letter - 'a'];
        }
        return pointer.endOfWord;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode pointer = root;
        for(char letter : prefix.toCharArray()) {
            if(pointer.alphabet[letter - 'a'] == null) {
                return false;
            }
            pointer = pointer.alphabet[letter - 'a'];
        }
        return true;
    }

    private static class TrieNode {
        TrieNode[] alphabet;
        boolean endOfWord;

        TrieNode() {
            int ALPHABET_SIZE = 26;
            alphabet = new TrieNode[ALPHABET_SIZE];
            endOfWord = false;
        }
    }
}
