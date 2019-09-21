package com.others;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youngxinler  2019/9/21
 **/
public class SingleNumber {
    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                map.put(nums[i], 2);
            }else {
                map.put(nums[i], 1);
            }
        }
        for(int num : nums){
            if(map.get(num) == 1)return num;
        }
        throw new IllegalStateException("error");
    }


}
