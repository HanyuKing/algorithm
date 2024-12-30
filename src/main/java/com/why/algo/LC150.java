package com.why.algo;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LC150 extends Base {

    @Test
    public void testP128() {
        print(longestConsecutive(new int[] {0,0,1,-1}));
        print(longestConsecutive(new int[] {100,4,200,1,3,2}));
        print(longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, 1);
        }
        int max = 0;
        for (int num : nums) {
            Integer count = numCountMap.get(num);
            if (count == null) {
                continue;
            }
            int numTemp = num;
            Integer preNumCount = numCountMap.get(--numTemp);
            while (preNumCount != null) {
                count += preNumCount;
                numCountMap.remove(numTemp);
                preNumCount = numCountMap.get(--numTemp);
            }
            numCountMap.put(num, count);
            max = Math.max(max, count);
        }
        return max;
    }
}
