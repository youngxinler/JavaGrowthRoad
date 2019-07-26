package com.arrayAndStrings;

/**
 * @author youngxinler  19-6-1 下午5:10
 * @version 0.1
 **/

//https://leetcode-cn.com/problems/increasing-triplet-subsequence/
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE, mid = Integer.MAX_VALUE;
        //min是多少无所谓的, min是为了找到mid, 然后只要存在一个数大于mid, 那么说明存在递增的三元子序列.
        //日后不断更新min是为了舍弃掉不适合的mid, (也可以说是为了mid尽可能地变小)
        for (int num :
                nums) {
            if (num < min){
                min = num;
            }else if (num < mid && num > min){
                mid = num;
            }else {
                return true;
            }
        }
        return false;
    }

}
