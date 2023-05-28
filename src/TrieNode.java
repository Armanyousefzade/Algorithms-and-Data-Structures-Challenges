/**
 * TrieNode represents a node in the Trie data structure.
 * Each node contains an array of child nodes for each possible character (a-z) and a flag indicating the end of a word.
 */
public class TrieNode {
    TrieNode[] nextChars = new TrieNode[26];
    boolean endOfWord;
}
