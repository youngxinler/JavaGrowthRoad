package com.arrayAndStrings;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        PrefixTree root = new PrefixTree();
        for (String str : words) {
            root.insert(str);
        }
        Set<String> res = new HashSet<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, new boolean[board.length][board[0].length], res, new StringBuilder(), root.root, i, j);
            }
        }
        return new LinkedList<>(res);
    }

    private void dfs(char[][] board, boolean[][] visited, Set<String> res, StringBuilder sb, TrieNode cur, int row, int col) {
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length)
            return;
        if (visited[row][col])
            return;
        if (!cur.containKey(board[row][col]))
            return;
        sb.append(board[row][col]);
        cur = cur.get(board[row][col]);
        if (cur.isEnd()) {
            res.add(sb.toString());
        }

        visited[row][col] = true;
        dfs(board, visited, res, sb, cur, row + 1, col);
        dfs(board, visited, res, sb, cur, row - 1, col);
        dfs(board, visited, res, sb, cur, row, col + 1);
        dfs(board, visited, res, sb, cur, row, col - 1);
        visited[row][col] = false;
        sb.deleteCharAt(sb.length() - 1);
    }
}


class PrefixTree {
    TrieNode root;

    public PrefixTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containKey(ch)) {
                node.put(ch, new TrieNode());
            }
            node = node.get(ch);
        }
        node.setEnd();
    }

    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.containKey(ch)) {
                node = node.get(ch);
            } else {
                return null;
            }
        }
        return node;
    }

    public boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isEnd();
    }

    public boolean startWith(String word) {
        TrieNode node = searchPrefix(word);
        return node != null;
    }
}

class TrieNode {

    private static final int R = 26;
    private TrieNode[] links;
    private boolean isEnd;

    public TrieNode() {
        links = new TrieNode[R];
    }

    public boolean containKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}
