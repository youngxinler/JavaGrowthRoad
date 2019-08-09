package com.backtrack;

/**
 * @author youngxinler  19-6-17 下午4:43
 * @version 0.1
 **/

//bfs
public class Exist {
    public boolean exist(char[][] board, String word){
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0) && backTrack(board, word, visited, i, j, 0)){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean backTrack(char[][] board, String word, boolean[][] visited,
                              int row, int col, int index){
        if (index == word.length()){
            return true;
        }
        if (row < 0 || col < 0 || row >= board.length || col >= board[0].length ||
            visited[row][col] || board[row][col] != word.charAt(index)){
            return false;
        }
        visited[row][col] = true;
        if (backTrack(board, word, visited, row + 1, col, index + 1) ||
            backTrack(board, word, visited, row - 1, col, index + 1) ||
            backTrack(board, word, visited, row, col + 1, index + 1) ||
            backTrack(board, word, visited, row, col - 1, index + 1)){
            return true;
        }
        visited[row][col] = false;
        return false;
    }
}
