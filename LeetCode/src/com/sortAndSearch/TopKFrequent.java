package com.sortAndSearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author youngxinler  19-6-6 下午5:46
 * @version 0.1
 **/

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num :
                nums) {
            if (!map.containsKey(num)) {
                map.put(num, 0);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> entries = new ArrayList<>();
        for (Map.Entry<Integer, Integer> e :
                map.entrySet()) {

        }

    }
}
