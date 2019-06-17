package com.backtrack;

/**
 * @author youngxinler  19-6-17 下午4:43
 * @version 0.1
 **/

public class Exist {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (word.charAt(0) == board[i][j] && backTrack(i, j, 0, visited, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean backTrack(int i, int j, int index, boolean[][] visited, char[][] board, String word) {
        if (index == word.length()) return true;
        //判断边界, 以及节点是否被重复使用.
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j]) {
            return false;
        }
        //防止节点复用.
        visited[i][j] = true;
        //dfs, 向4个方向探路, 有一个方向true即可.
        if (backTrack(i + 1, j, index + 1, visited, board, word) ||
                backTrack(i - 1, j, index + 1, visited, board, word)
                || backTrack(i, j + 1, index + 1, visited, board, word)
                || backTrack(i, j - 1, index + 1, visited, board, word)) {
            return true;
        }
        //如果搜索的方向失败, 则恢复节点为false.
        visited[i][j] = false;
        return false;
    }
}
