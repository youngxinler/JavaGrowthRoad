package com.backtrack;

/**
 * @author youngxinler  19-6-17 下午4:43
 * @version 0.1
 **/

//dfs
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
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || visited[i][j] ||
                word.charAt(index) != board[i][j]) {
            return false;
        }
        visited[i][j] = true;
        if (backTrack(i + 1, j, index + 1, visited, board, word) ||
                backTrack(i - 1, j, index + 1, visited, board, word)
                || backTrack(i, j + 1, index + 1, visited, board, word)
                || backTrack(i, j - 1, index + 1, visited, board, word)) {
            return true;
        }
        visited[i][j] = false;
        return false;
    }




//    public boolean exist(char[][] board, String word){
//        boolean[][] visited = new boolean[board.length][board[0].length];
//        for (int i = 0; i < board.length; i++) {
//            for (int j = 0; j < board[0].length; j++) {
//                if (word.charAt(0) == board[i][j] &&
//                        dfs(board, i, j, 0, word, visited)){
//                    return true;
//                }
//            }
//        }
//        return false;
//    }
//
//    private boolean dfs(char[][] board, int col, int row, int index, String word, boolean[][] visited){
//        if (index == word.length()){
//            return true;
//        }
//        if (col < 0 || col >= board[0].length || row < 0 || row >= board.length ||
//                visited[row][col] || word.charAt(index) != board[row][col]){
//            return false;
//        }
//        visited[row][col] = true;
//        if (dfs(board, col - 1, row, index + 1, word, visited) ||
//                dfs(board, col + 1, row, index + 1, word, visited) ||
//                dfs(board, col, row - 1, index + 1, word, visited) ||
//                dfs(board, col, row + 1, index + 1, word, visited)){
//            return true;
//        }
//        visited[row][col] = false;
//        return false;
//    }
}
