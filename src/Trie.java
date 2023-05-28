/**
 * Trie data structure for efficient prefix-based string operations.
 */
class Trie {

    TrieNode root;

    /**
     * Initializes a new instance of the Trie class.
     * The root of the trie is created as an empty node.
     */
    public Trie() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     *
     * @param word The word to be inserted into the trie.
     */
    public void insert(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.nextChars[word.charAt(i) - 'a'] == null) {
                current.nextChars[word.charAt(i) - 'a'] = new TrieNode();
            }
            current = current.nextChars[word.charAt(i) - 'a'];
        }
        current.endOfWord = true;
    }

    /**
     * Searches for a word in the trie.
     *
     * @param word The word to search for in the trie.
     * @return True if the word exists in the trie, false otherwise.
     */
    public boolean search(String word) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            if (current.nextChars[word.charAt(i) - 'a'] == null) {
                return false;
            }
            current = current.nextChars[word.charAt(i) - 'a'];
        }
        return current.endOfWord;
    }

    /**
     * Checks if there is any word in the trie that starts with the given prefix.
     *
     * @param prefix The prefix to check in the trie.
     * @return True if there is any word in the trie that starts with the prefix, false otherwise.
     */
    public boolean startsWith(String prefix) {
        TrieNode current = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (current.nextChars[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            current = current.nextChars[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}
