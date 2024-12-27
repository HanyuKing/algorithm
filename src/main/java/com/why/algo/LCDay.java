package com.why.algo;

import org.junit.Test;

import java.util.*;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/27 10:22
 * @Description
 * @Version 1.0
 **/
public class LCDay extends Base {

    @Test
    public void testP3159() {
        int[] nums = new int[] {1,3,1,7};
        int[] queries = new int[] {1,3,2,4};
        int x = 1;
        print(occurrencesOfElement(nums, queries, x)); // 0,-1,2,-1
    }

    public int[] occurrencesOfElement(int[] nums, int[] queries, int x) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.computeIfAbsent(nums[i], a -> new ArrayList<>()).add(i);
        }
        List<Integer> targetNums = map.get(x);
        int[] result = new int[queries.length];
        if (targetNums == null) {
            Arrays.fill(result, -1);
            return result;
        }
        int size = targetNums.size();
        for (int i = 0; i < queries.length; i++) {
            if (queries[i] > size) {
                result[i] = -1;
            } else {
                result[i] = targetNums.get(queries[i] - 1);
            }
        }
        return result;
    }
}
