package com.design;

import java.util.HashSet;
import java.util.Set;

/**
 * @author youngxinler  2019/7/1 10:01
 **/

//Insert Delete GetRandom O(1)
public class RandomizedSet {

    private Set<Integer> hashSet;

    public RandomizedSet() {
        hashSet = new HashSet<>();
    }


    public boolean insert(int val) {
        return hashSet.add(val);
    }


    public boolean remove(int val) {
        return hashSet.remove(val);
    }


    public int getRandom() {
        if (hashSet.size() == 0) {
            return 0;
        }
        Integer[] data = hashSet.toArray(new Integer[hashSet.size()]);
        int index = (int) (Math.random() * hashSet.size());
        return data[index];
    }

}
