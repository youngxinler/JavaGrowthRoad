package com.sortAndSearch;

import java.util.*;

/**
 * @author youngxinler  19-6-6 下午5:46
 * @version 0.1
 **/

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int num :
                nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return map.get(o1) - map.get(o2);
            }
        });
        for (int key :
                map.keySet()) {
            if (queue.size() < k) {
                queue.add(key);
            } else if (map.get(key) > map.get(queue.peek())) {
                queue.remove();
                queue.add(key);
            }
        }
        List<Integer> ans = new ArrayList<>(queue.size());
        for (int num :
                queue.toArray(new Integer[queue.size()])) {
            ans.add(num);
        }
        Collections.reverse(ans);
        return ans;
    }
}
