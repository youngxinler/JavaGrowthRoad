package com.treesAndGraphs;

public class NumIslands {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length < 1 || grid[0].length < 1) {
            return 0;
        }
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && bfs(i, j, grid)) {
                    res++;
                }
            }
        }
        return res;
    }

    private boolean bfs(int line, int col, char[][] grid) {
        if (inMap(line, col, grid) && grid[line][col] == '1') {
            grid[line][col] = '2';
            bfs(line - 1, col, grid);
            bfs(line + 1, col, grid);
            bfs(line, col - 1, grid);
            bfs(line, col + 1, grid);
            return true;
        }
        return false;
    }

    private boolean inMap(int line, int col, char[][] grid) {
        if (line > grid.length - 1 || line < 0 || col < 0 || col > grid.length - 1) {
            return false;
        }
        return true;
    }
}
