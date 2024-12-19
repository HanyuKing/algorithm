package com.why.algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/19 10:25
 * @Description
 * @Version 1.0
 **/
public class LC extends Base {
    @Test
    public void testP3285() {
        int[] height = new int[] {10, 1, 10, 1, 10};
        int threshold = 3;

        print(stableMountains(height, threshold));
    }

    public List<Integer> stableMountains(int[] height, int threshold) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < height.length - 1; i++) {
            if (height[i] > threshold) {
                result.add(i + 1);
            }
        }
        return result;
    }
}
