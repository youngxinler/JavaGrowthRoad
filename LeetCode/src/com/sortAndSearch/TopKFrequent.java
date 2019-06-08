package com.sortAndSearch;

import java.util.*;

/**
 * @author youngxinler  19-6-6 下午5:46
 * @version 0.1
 **/

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int num :
                nums) {
            hashMap.put(num, hashMap.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return hashMap.get(integer) - hashMap.get(t1);
            }
        });
        for (int key : hashMap.keySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else if (hashMap.get(key) > hashMap.get(priorityQueue.peek())) {
                priorityQueue.remove();
                priorityQueue.add(key);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.remove());
        }
        Collections.reverse(res);
        return res;
    }
}
