package com.sortAndSearch;

/**
 * @author youngxinler  19-6-23 下午4:30
 * @version 0.1
 **/

public class SearchMatrix {
    //通过判断末尾元素进行二分查找
    public boolean searchMatrix_2(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        final int rawLen = matrix[0].length - 1;
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] <= target && target <= matrix[i][rawLen]) {
                if (binarySearch(matrix[i], target)) return true;
            }
        }
        return false;
    }

    private boolean binarySearch(int[] nums, int target) {
        int l = 0, r = nums.length - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return true;
            } else {
                if (nums[mid] > target) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        return false;
    }


    public boolean searchMatrix(int[][] matrix, int target){
        if (matrix.length == 0) return false;
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0){
            if (matrix[row][col] > target){
                col--;
            }else if (matrix[row][col] < target){
                row++;
            }else {
                return true;
            }
        }
        return false;
    }
}
