package com.why.algo;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author Hanyu.Wang
 * @Date 2025/1/23 14:27
 * @Description
 * @Version 1.0
 **/
public class LC250 extends Base {
    @Test
    public void testP213() {
        print(rob(new int[] {2,3,2}));
        print(rob(new int[] {1,2,3,1}));
    }

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int a = robF(Arrays.copyOfRange(nums, 1, nums.length));
        int b = robF(Arrays.copyOfRange(nums, 0, nums.length - 1));
        return Math.max(a, b);
    }

    public int robF(int[] nums) {
        int prePre = 0;
        int pre = nums[0];
        int max = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            max = prePre + nums[i - 1];
            max = Math.max(max, pre);
            prePre = pre;
            pre = max;
        }
        return max;
    }


}
