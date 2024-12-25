package com.why.algo;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

public class LC400 {
    @Test
    public void testP398() {

    }
    class Solution398 {
        Map<Integer, List<Integer>> map = new HashMap<>();
        Random random = new Random();
        public Solution398(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                map.computeIfAbsent(nums[i], list -> new ArrayList<>()).add(i);
            }
        }

        public int pick(int target) {
            List<Integer> nums = map.get(target);
            return nums.get(random.nextInt(nums.size()));
        }
    }
}
