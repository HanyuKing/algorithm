package com.why.algo;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

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
        // todo
    }

    public String fractionToDecimal(int numerator, int denominator) {

        return "";
    }

    @Test
    public void testP167() {
        print(twoSum(new int[] {2,7,11,15}, 9));
    }

    public int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while (l < r) {
            int sum = numbers[l] + numbers[r];
            if (sum == target) {
                return new int[] {l + 1, r + 1};
            } else if (sum > target) {
                r--;
            } else {
                l++;
            }
        }
        return null;
    }

    @Test
    public void testP168() {
        print(convertToTitle(2147483647));
    }

    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber > 0) {
            int r = columnNumber % 26;
            if (r == 0) {
                r = 26;
                columnNumber--;
            }
            sb.insert(0,  (char) ('A' + r - 1));
            columnNumber /= 26;
        }
        return sb.toString();
    }

    @Test
    public void testP171() {
        print(titleToNumber("AB"));
        print(titleToNumber("ZY"));
    }

    public int titleToNumber(String columnTitle) {
        int ans = 0;
        int n = 1;
        for (int i = columnTitle.length() - 1; i >= 0; i--) {
            ans += (columnTitle.charAt(i) - 'A' + 1) * n;
            n *= 26;
        }
        return ans;
    }

    @Test
    public void testP172() {
        print(trailingZeroes(30));
    }

    public int trailingZeroes(int n) {
        int count = 0;
        while (n > 0) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    @Test
    public void testP173() {
        // PASS
    }

    @Test
    public void testP175() {
        // SQL

    }

    @Test
    public void testP179() {
        print(largestNumber(new int[] {111311,1113})); // 1113 111311
        print(largestNumber(new int[] {30,3,34,5,9})); // 1113 111113
        /*

         */
    }

    public String largestNumber(int[] nums) {
        String s = Arrays.stream(nums)
                .boxed()
                .map(String::valueOf)
                .sorted((o1, o2) -> (o2 + o1).compareTo(o1 + o2))
                .collect(Collectors.joining(""));
        return s.charAt(0) == '0' ? "0" : s;
    }

    @Test
    public void testP187() {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        print(findRepeatedDnaSequences(s));
    }

    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> set = new HashSet<>();
        int l = 10;
        Set<String> ans = new HashSet<>();
        for (int i = 0; i <= s.length() - l; i++) {
            String subS = s.substring(i, i + l);
            if (set.contains(subS)) {
                ans.add(subS);
            } else {
                set.add(subS);
            }
        }
        return new ArrayList<>(ans);
    }
}
