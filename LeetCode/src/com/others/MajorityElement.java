package com.others;

import java.util.HashMap;
import java.util.Map;

/**
 * @author youngxinler  2019/7/16 9:10
 **/
public class MajorityElement {

    //hashMap 效率低的丫皮
    public int majorityElement_1(int[] nums) {
        if (nums.length == 1) return nums[0];
        final Map<Integer, Integer> map = new HashMap<>(16);
        int half = nums.length / 2;
        for (int num :
                nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
                if (map.get(num) > half) return num;
            } else {
                map.put(num, 1);
            }
        }
        return -1;
    }

    //两个相遇的数如果不相同会抵消times的数量, 只有数量大于数组长度一半的数最后才会留下来.
    public int majorityElement(int[] nums) {
        int ans = nums[0];
        int times = 1;
        for (int i = 0; i < nums.length; i++) {
            times += (ans == nums[i] ? 1 : -1);
            if (times == 0) ans = nums[i];
        }
        return ans;
    }
}
