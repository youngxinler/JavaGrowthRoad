import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Main {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return wordBreakCore(s, new HashSet(wordDict), 0);
    }

    private List<String> wordBreakCore(String s, Set<String> wordSet, int start) {
        LinkedList<String> res = new LinkedList<String>();
        if (start == s.length()) {
            return res;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (wordSet.contains(s.substring(start, end))) {
                List<String> list = wordBreakCore(s, wordSet, end);
                for (String l : list) {
                    res.add(s.substring(start, end) + (l.equals("") ? "" : " ") + l);
                }
            }
        }
        return res;
    }
}

class Trie {


    private HashSet<String> set;

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        set = new HashSet<>();
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        set.add(word);
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        return set.contains(word);
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        for (String s : set) {
            if (s.indexOf(prefix) == 0) {
                return true;
            }
        }
        return false;
    }
}