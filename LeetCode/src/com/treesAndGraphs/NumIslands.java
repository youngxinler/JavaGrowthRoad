package com.treesAndGraphs;

public class NumIslands {
    public int numIslands(char[][] grid){
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    count++;
                    bfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int row, int col){
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length){
            return;
        }
        if (grid[row][col] == '1'){
            grid[row][col] = '2';
            bfs(grid, row - 1, col);
            bfs(grid, row + 1, col);
            bfs(grid, row, col + 1);
            bfs(grid, row, col - 1);
        }
    }
}
