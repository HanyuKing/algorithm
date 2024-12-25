package com.why.algo;

import org.junit.Test;

import java.util.*;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/25 19:09
 * @Description
 * @Version 1.0
 **/
public class LC100 extends Base {
    @Test
    public void testP53() {
        int[] nums = new int[] {-2,1,-3,4,-1,2,1,-5,4};
        print(maxSubArray(nums)); // [4,-1,2,1] 6

        nums = new int[] {5,4,-1,7,8};
        print(maxSubArray(nums)); // 23
    }
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int curr = Math.max(nums[i], nums[i] + pre);
            maxSum = Math.max(maxSum, curr);
            pre = curr;
        }
        return maxSum;
    }
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int maxSum = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], nums[i] + dp[i - 1]);
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }

    @Test
    public void testP54() {
        int[][] matrix = new int[][] {
                {3},
                {2}};
        print(spiralOrder(matrix));

        matrix = new int[][] {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12}
        };
        print(spiralOrder(matrix));
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        int l = 0;
        int u = 0;
        int r = matrix[0].length - 1;
        int d = matrix.length - 1;
        List<Integer> result = new ArrayList<>();

        while (l <= r || u <= d) {
            int i = l;
            while (i <= r && u <= d) {
                result.add(matrix[u][i++]);
            }
            u++;
            i = u;
            while (i <= d && l <= r) {
                result.add(matrix[i++][r]);
            }
            r--;
            i = r;
            while (i >= l && u <= d) {
                result.add(matrix[d][i--]);
            }
            d--;
            i = d;
            while (i >= u && l <= r) {
                result.add(matrix[i--][l]);
            }
            l++;
        }

        return result;
    }

    @Test
    public void testP55() {
        int[] nums = new int[] {2,3,1,1,4};
        print(canJump(nums)); // true

        nums = new int[] {3,2,1,0,4};
        print(canJump(nums)); // false

        nums = new int[] {1,2,3};
        print(canJump(nums)); // true
    }

    public boolean canJump(int[] nums) {
        int maxIndex = nums[0];
        for (int i = 1; i <= maxIndex && i < nums.length; i++) {
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }
        return maxIndex >= nums.length - 1;
    }

    @Test
    public void testP56() {
        int[][] intervals = new int[][]{{1,4},{1,5}}; // [[1,6],[8,10],[15,18]]
        print(merge2(intervals));
    }

    public int[][] merge2(int[][] intervals) {
        TreeSet<int[]> treeSet = new TreeSet<>(Comparator.comparingInt(o -> o[0]));
        treeSet.addAll(Arrays.asList(intervals));

        int index = -1;
        int[][] newIntervals = new int[intervals.length][intervals[0].length];
        for (int[] interval : treeSet) {
            if (index == -1) {
                newIntervals[0] = interval;
                index = 0;
                continue;
            }
            if (interval[0] <= newIntervals[index][1]) {
                newIntervals[index][1] = Math.max(interval[1], newIntervals[index][1]);
            } else {
                newIntervals[++index] = interval;
            }
        }
        return Arrays.copyOf(newIntervals, index + 1);
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= intervals[index][1]) {
                intervals[index][1] = Math.max(intervals[i][1], intervals[index][1]);
            } else {
                intervals[++index] = intervals[i];
            }
        }
        return Arrays.copyOf(intervals, index + 1);
    }

    @Test
    public void testP57() {
        int[][] intervals = new int[][]{{1,5}};
        int[] newInterval = {2,7};
        print(insert(intervals, newInterval));
    }

    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] result = new int[intervals.length + 1][2];
        int index = 0;
        boolean added = false;
        for (int i = 0; i < intervals.length; i++) {
            if (!added && newInterval[0] <= intervals[i][0]) {
                result[index++] = newInterval;
                added = true;
            }
            result[index++] = intervals[i];
        }
        if(!added) {
            result[index] = newInterval;
        }
        return merge(result);
    }

    @Test
    public void testP59() {
        print(generateMatrix(3));

        print(generateMatrix(2));

        print(generateMatrix(1));
    }
    public int[][] generateMatrix(int n) {
        int l = 0;
        int u = 0;
        int r = n - 1;
        int d = n - 1;
        int[][] matrix = new int[n][n];
        int num = 1;
        while (l <= r && u <= d) {
            int i = l;
            while (i <= r && u <= d) {
                matrix[u][i++] = num++;
            }
            u++;
            i = u;
            while (i <= d && l <= r) {
                matrix[i++][r] = num++;
            }
            r--;
            i = r;
            while (i >= l && u <= d) {
                matrix[d][i--] = num++;
            }
            d--;
            i = d;
            while (i >= u && l <= r) {
                matrix[i--][l] = num++;
            }
            l++;
        }
        return matrix;
    }

    @Test
    public void testP61() {
        // head = [1,2,3,4,5], k = 2     =>[4,5,1,2,3]
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        print(rotateRight(n1, 5));
    }

    public ListNode rotateRight(ListNode head, int k) {
        int length = 0;
        ListNode headTemp = head;
        ListNode tail = headTemp;
        while (headTemp != null) {
            tail = headTemp;
            length++;
            headTemp = headTemp.next;
        }
        int headMoveCount = k % length;
        if (headMoveCount == 0) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (headMoveCount > 0) {
            fast = fast.next;
            headMoveCount--;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        tail.next = head;
        return newHead;
    }
}
