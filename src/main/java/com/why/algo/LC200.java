package com.why.algo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Hanyu.Wang
 * @Date 2025/1/16 10:28
 * @Description
 * @Version 1.0
 **/
public class LC200 extends Base {
    @Test
    public void testP151() {
        String s = " hello world ";
        print(reverseWords(s));
    }

    public String reverseWords(String s) {
        List<String> wordList = new ArrayList<>();
        int start = -1;
        for (int end = 0; end < s.length(); end++) {
            if (s.charAt(end) != ' ' && start == -1) {
                start = end;
            } else if (s.charAt(end) == ' ' && start >= 0) {
                wordList.add(s.substring(start, end));
                start = -1;
            }
        }
        if (start != -1) {
            wordList.add(s.substring(start));
        }
        start = 0;
        int end = wordList.size() - 1;
        while (start < end) {
            String temp = wordList.get(start);
            wordList.set(start, wordList.get(end));
            wordList.set(end, temp);
            start++;
            end--;
        }
        StringBuilder sb = new StringBuilder();
        wordList.forEach(c -> sb.append(c).append(" "));
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    @Test
    public void testP152() {
        print(maxProduct(new int[] {-2,3,-4}));
    }

    public int maxProduct(int[] nums) {
        int ans = nums[0];
        int pre = nums[0];
        int preMin = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int curr = Math.max(nums[i], nums[i] * preMin);
                int currMin = Math.min(nums[i], nums[i] * pre);
                ans = Math.max(ans, curr);
                pre = curr;
                preMin = currMin;
            } else {
                int curr = Math.max(nums[i], nums[i] * pre);
                int currMin = Math.min(nums[i], nums[i] * preMin);
                ans = Math.max(ans, curr);
                pre = curr;
                preMin = currMin;
            }

        }
        return ans;
    }

    @Test
    public void testP155() {
        // PASS
    }

    @Test
    public void testP160() {

    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pHeadA = headA;
        ListNode pHeadB = headB;
        while (headA != null || headB != null) {
            if (headA == headB) {
                return headA;
            }
            if (headA == null) {
                headA = pHeadB;
            }
            if (headB == null) {
                headB = pHeadA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    @Test
    public void testP162() {
        print(findPeakElement(new int[] {1,2}));
    }

    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }

        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (mid == 0) {
                if (midVal > nums[mid + 1]) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else if (mid == nums.length - 1) {
                if (nums[mid - 1] < midVal) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else {
                if (midVal > nums[mid - 1] && midVal > nums[mid + 1]) {
                    return mid;
                }
                if (midVal < nums[mid + 1]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return 0;
    }

    @Test
    public void testP164() {
        print(maximumGap(new int[] {3,6,9,1}));
        print(maximumGap(new int[] {10}));
    }

    public int maximumGap(int[] nums) {
        if (nums.length < 2) {
            return 0;
        }

        return -1; // todo
    }

    @Test
    public void testP165() {
        print(compareVersion("1.2", "1.10"));
    }

    public int compareVersion(String version1, String version2) {
        List<String> v1Array = new ArrayList<>(Arrays.asList(version1.split("\\.")));
        List<String> v2Array = new ArrayList<>(Arrays.asList(version2.split("\\.")));
        int len = v1Array.size() - v2Array.size();
        if (len < 0) {
            for (; len < 0; len++) {
                v1Array.add("0");
            }
        } else {
            for (; len > 0; len--) {
                v2Array.add("0");
            }
        }
        for (int i = 0; i < v1Array.size(); i++) {
            int ans = Integer.parseInt(v1Array.get(i)) - Integer.parseInt(v2Array.get(i));
            if (ans != 0) {
                return ans < 0 ? -1 : 1;
            }
        }
        return 0;
    }

    @Test
    public void testP166() {
        print(fractionToDecimal(4, 333));
    }

    public String fractionToDecimal(int numerator, int denominator) {

        return "";
    }
}
