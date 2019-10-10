package com.sortAndSearch;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        IArraySort sort = new BucketSort();
        int[] nums = new int[]{1, 5, 9, 3, 5, 7};
        System.out.println(Arrays.toString(nums));
        nums = sort.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
