package com.why.algo;

import org.junit.Test;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/19 10:25
 * @Description
 * @Version 1.0
 **/
public class LC extends Base {

    @Test
    public void testP12() {
        print(intToRoman(58));
        print(intToRoman(1994));
        print(intToRoman(3749));
    }

    public String intToRoman(int num) {
        TreeMap<Integer, String> sortedMap = new TreeMap<>((o1, o2) -> o2 - o1);
        sortedMap.put(1000, "M");
        sortedMap.put(500, "D");
        sortedMap.put(100, "C");
        sortedMap.put(50, "L");
        sortedMap.put(10, "X");
        sortedMap.put(5, "V");
        sortedMap.put(1, "I");
        sortedMap.put(4, "IV");
        sortedMap.put(9, "IX");
        sortedMap.put(40, "XL");
        sortedMap.put(90, "XC");
        sortedMap.put(400, "CD");
        sortedMap.put(900, "CM");

        sortedMap.put(3, "III");
        sortedMap.put(30, "XXX");
        sortedMap.put(300, "CCC");
        sortedMap.put(3000, "MMM");

        StringBuilder ans = new StringBuilder();

        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            int curr = entry.getKey();
            while (num - curr >= 0) {
                num = num - curr;
                ans.append(entry.getValue());
            }
            if (num == 0) {
                break;
            }
        }

        return ans.toString();
    }

    @Test
    public void testP3188() {
        System.out.println(minAnagramLength("aabbabab"));
        System.out.println(minAnagramLength("abba"));
        System.out.println(minAnagramLength("aabb"));
        System.out.println(minAnagramLength("aabbbb"));
    }

    public int minAnagramLength(String s) {
        char[] chars = s.toCharArray();
        int n = s.length();
        int halfN = n / 2;
        for (int span = 1; span <= halfN; span++) {
            if (n % span != 0) {
                continue;
            }
            int[] cnt0 = new int[26];
            for (int i = 0; i < span; i++) {
                cnt0[chars[i] - 'a']++;
            }

            int[] cnt2 = new int[26];

            int i = span;
            for (; i < n; i++) {
                cnt2[chars[i] - 'a']++;
                if ((i + 1) % span == 0) {
                    if (!Arrays.equals(cnt0, cnt2)) {
                        break;
                    }
                    if (i == n - 1) {
                        return span;
                    }
                    Arrays.fill(cnt2, 0);
                }
            }
        }
        return n;
    }
    public int minAnagramLength2(String s) {
        char[] chars = s.toCharArray();
        for (int span = 1; span <= s.length() / 2; span++) {
            if (s.length() % span != 0) {
                continue;
            }
            Map<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < span; i++) {
                map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
            }
            Map<Character, Integer> map2 = new HashMap<>();

            int i = span;
            for (; i < s.length(); i++) {
                map2.put(chars[i], map2.getOrDefault(chars[i], 0) + 1);
                if ((i + 1) % span == 0) {
                    if (map2.size() != map.size()) {
                        break;
                    }
                    if (!mapEquals(map, map2)) {
                        break;
                    }
                    if (i == s.length() - 1) {
                        return span;
                    }
                    map2.clear();
                }
            }
        }
        return s.length();
    }

    private static boolean mapEquals(Map<Character, Integer> map, Map<Character, Integer> map2) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            Integer count2;
            if ((count2 = map2.get(entry.getKey())) == null
                    || !count2.equals(entry.getValue())) {
                return false;
            }
        }
        return true;
    }

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

    @Test
    public void testP8() {
//        print(myAtoi("42"));
//        print(myAtoi("-42"));
//        print(myAtoi("    -42"));
//        print(myAtoi("    -042"));
//
//        print(myAtoi("2147483647"));
//        print(myAtoi("-2147483648"));
//        print(myAtoi("-21474836481"));
//        print(myAtoi("-91283472332")); // -2147483648
        print(myAtoi("-6147483648")); // -2147483648

//        print(myAtoi("0-1"));
//        print(myAtoi("-1337c0d3"));
//        print(myAtoi("1337c0d3"));
//
//        print(myAtoi("words and 987"));

//        print(myAtoi("2147483646"));


    }

    public int myAtoi(String s) {
        if (s.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            if ((c == '-' || c == '+') && sb.length() == 0
                    || (c >= '0' && c <= '9')) {
                sb.append(c);
            } else {
                break;
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        int result = 0;
        char[] numArray = sb.toString().toCharArray();
        int i = 0;
        if (numArray[0] == '-' || numArray[0] == '+') {
            i++;
        }

        int boundary = Integer.MAX_VALUE / 10;
        for ( ; i < numArray.length; i++) {
            int num = (numArray[i] - '0');
            if (result > boundary || result == boundary && num > 7) {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            result = result * 10 + num;
        }

        return numArray[0] == '-' ? -result : result;
    }

    public int myAtoi2(String s) {
        if (s.length() == 0) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == ' ') {
                if (sb.length() == 0) {
                    continue;
                } else {
                    break;
                }
            }
            if ((c == '-' || c == '+') && sb.length() == 0
                    || (c >= '0' && c <= '9')) {
                sb.append(c);
            } else {
                break;
            }
        }

        if (sb.length() == 0) {
            return 0;
        }

        int result = 0;
        char[] numArray = sb.toString().toCharArray();
        int i = 0;
        if (numArray[0] == '-' || numArray[0] == '+') {
            i++;
        }

        for ( ; i < numArray.length; i++) {
            int num = (numArray[i] - '0');
            if (result * 10 / 10 != result) {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            if (result * 10 <= Integer.MAX_VALUE - num) {
                result = result * 10 + num;
            } else {
                return numArray[0] == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }

        return numArray[0] == '-' ? -result : result;
    }

    @Test
    public void testP3() {
        print(lengthOfLongestSubstring("abcabcbb")); // 3
        print(lengthOfLongestSubstring("bbbbb")); // 1
        print(lengthOfLongestSubstring("pwwkew")); // 3
    }

    public int lengthOfLongestSubstring(String s) {
        int left = 0;
        int right = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int max = 0;
        while (right < n) {
            char curr = s.charAt(right);
            if (!set.contains(curr)) {
                set.add(curr);
                right++;
                max = Math.max(set.size(), max);
            } else {
                char leftChar = s.charAt(left++);
                set.remove(leftChar);
            }
        }
        return max;
    }

    @Test
    public void testP5() {
        print(longestPalindrome("babad")); // bab (i + 1, j - 1)
        print(longestPalindrome("cbbd")); // bb
        print(longestPalindrome("aacabdkacaa")); // cbbc
    }

    public String longestPalindrome(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        int l = 0;
        int r = 0;
        int max = 0;
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j == i + 1 || dp[i + 1][j - 1] > 0)) {
                    int currMax = (j - i + 1);
                    if (currMax > max) {
                        l = i;
                        r = j;
                        max = currMax;
                    }
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                }
            }
        }
        return s.substring(l, r + 1);
    }

    @Test
    public void testP6() {
        print(convert("PAYPALISHIRING", 3));
        print(convert("PAYPALISHIRING", 4));
        print(convert("ABCDE", 4));
    }

    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int n = s.length();
        int numCols =  ((int) Math.ceil((double) n / (numRows + numRows - 2))) * (numRows - 1) + 1;
        char[][] chars = new char[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            Arrays.fill(chars[i], ' ');
        }
        int row = 0;
        int col = 0;
        boolean down = true;
        for (char c : s.toCharArray()) {
            if (down) {
                chars[row][col] = c;
                row++;
                if (row == numRows) {
                    down = false;
                    col = col + 1;
                    row = numRows - 2;
                }
            } else {
                chars[row][col] = c;
                col++;
                row--;
                if (row == -1) {
                    down = true;
                    row = 1;
                    col--;
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (chars[i][j] != ' ') {
                    sb.append(chars[i][j]);
                }
            }
        }
        return sb.toString();
    }

    @Test
    public void testP11() {
        print(maxArea2(new int[] {1,8,6,2,5,4,8,3,7})); // 49
    }

    public int maxArea2(int[] height) {
        // s = (j - i) * (min(h[i], h[j]))
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            max = Math.max(max, (r - l) * min);
            while (l < r && height[l] <= min) {
                l++;
            }
            while (l < r && height[r] <= min) {
                r--;
            }
        }
        return max;
    }

    public int maxArea(int[] height) {
        // s = (j - i) * (min(h[i], h[j]))
        int l = 0;
        int r = height.length - 1;
        int max = 0;
        while (l < r) {
            int min = Math.min(height[l], height[r]);
            max = Math.max(max, (r - l) * min);
            if (min == height[l]) {
                l++;
            } else {
                r--;
            }
        }
        return max;
    }

    @Test
    public void testP13() {
        print(romanToInt("MCMXCIV")); // 1994
    }

    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int curr = map.get(s.charAt(i));
            if (i < s.length() - 1 && curr < map.get(s.charAt(i + 1))) {
                ans = ans + map.get(s.charAt(i + 1)) - curr;
                i++;
            } else {
                ans = ans + curr;
            }
        }
        return ans;
    }

    @Test
    public void testP17() {
        String digits = ""; // ["ad","ae","af","bd","be","bf","cd","ce","cf"]
        print(letterCombinations(digits));
    }

    public List<String> letterCombinations2(String digits) {
        Map<Integer, char[]> map = new HashMap<>();
        map.put(2, new char[]{'a', 'b', 'c'});
        map.put(3, new char[]{'d', 'e', 'f'});
        map.put(4, new char[]{'g', 'h', 'i'});
        map.put(5, new char[]{'j', 'k', 'l'});
        map.put(6, new char[]{'m', 'n', 'o'});
        map.put(7, new char[]{'p', 'q', 'r', 's'});
        map.put(8, new char[]{'t', 'u', 'v'});
        map.put(9, new char[]{'w', 'x', 'y', 'z'});
        List<String> result = new ArrayList<>();

        for (char digitC : digits.toCharArray()) {
            char[] chars = map.get(digitC - '0');
            List<String> newResult = new ArrayList<>();
            for (String s : result) {
                for (char c : chars) {
                    newResult.add(s + c);
                }
            }
            if (result.isEmpty()) {
                for (char c : chars) {
                    result.add(c + "");
                }
            } else {
                result = newResult;
            }
        }

        return result;
    }
    public List<String> letterCombinations(String digits) {
        Map<Character, char[]> map = new HashMap<>();
        map.put('2', new char[]{'a', 'b', 'c'});
        map.put('3', new char[]{'d', 'e', 'f'});
        map.put('4', new char[]{'g', 'h', 'i'});
        map.put('5', new char[]{'j', 'k', 'l'});
        map.put('6', new char[]{'m', 'n', 'o'});
        map.put('7', new char[]{'p', 'q', 'r', 's'});
        map.put('8', new char[]{'t', 'u', 'v'});
        map.put('9', new char[]{'w', 'x', 'y', 'z'});
        List<String> result = new ArrayList<>();

        dfsLetterCombinations(map, result, new StringBuilder(), digits, 0, digits.length());

        return result;
    }

    private void dfsLetterCombinations(Map<Character, char[]> map,
                                       List<String> result,
                                       StringBuilder sb,
                                       String digits,
                                       int index,
                                       int length) {
        if (index == length) {
            if (sb.length() > 0) {
                result.add(sb.toString());
            }
            return;
        }
        for (char c : map.get(digits.charAt(index))) {
            sb.append(c);
            dfsLetterCombinations(map, result, sb, digits, index + 1, length);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @Test
    public void testP19() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        print(removeNthFromEnd(n1, 4));
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode p1 = head;
        ListNode p2 = head;
        while (n > 0 && p2 != null) {
            p2 = p2.next;
            n--;
        }
        if (p2 == null) {
            head = head.next;
            return head;
        }
        // 1->2->3->4
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return head;
    }

    @Test
    public void testP22() {
        print(generateParenthesis(4));
    }
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        dfsGenerateParenthesis(result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void dfsGenerateParenthesis(List<String> result, StringBuilder sb, int lc, int rc, int n) {
        if (sb.length() == n * 2) {
            result.add(sb.toString());
            return;
        }
        if (lc < n) {
            sb.append('(');
            dfsGenerateParenthesis(result, sb, lc + 1, rc, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (rc < n && rc < lc) {
            sb.append(')');
            dfsGenerateParenthesis(result, sb, lc, rc + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    @Test
    public void testP31() {
        int[] nums = new int[] {4,2,0,2,3,2,0}; // 4,2,0,3,0,2,2
        nextPermutation(nums);
        print(nums);

        nums = new int[] {3, 2, 1}; // 123
        nextPermutation(nums);
        print(nums);

        nums = new int[] {1, 1, 5}; // 151
        nextPermutation(nums);
        print(nums);

        nums = new int[] {1, 3, 2}; // 213
        nextPermutation(nums);
        print(nums);
    }

    public void nextPermutation(int[] nums) {
        int index = -1;
        for (int i = nums.length - 1; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                index = i - 1;
                break;
            }
        }
        if (index == -1) {
            // Arrays.sort(nums, 0, nums.length);
            reverse(nums, 0);
            return;
        }
        for (int i = nums.length - 1; i > index; i--) {
            if (nums[i] > nums[index]) {
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;
                break;
            }
        }

        // Arrays.sort(nums, index + 1, nums.length);
        reverse(nums, index + 1);
    }

    private void reverse(int[] nums, int index) {
        int end = nums.length - 1;
        while (index < end) {
            int temp = nums[index];
            nums[index] = nums[end];
            nums[end] = temp;
            index++;
            end--;
        }
    }

    @Test
    public void testP33() {
        int[] nums = new int[] {4,5,6,7,0,1,2};
        int target = 0;
        print(search(nums, target)); // 4
    }
    public int search(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = nums[mid];
            if (midValue == target) {
                return mid;
            } else if (nums[low] <= midValue) {
                if (nums[low] <= target && target < midValue) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (midValue < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    @Test
    public void testP34() {
        int[] nums = new int[]  {5,7,7,8,8,10};
        int target = 8;
        print(searchRange(nums, target));

        nums = new int[] {5,7,7,8,8,10};
        target = 6;
        print(searchRange(nums, target));
    }

    public int[] searchRange(int[] nums, int target) {
        return new int[] {
                searchRange(nums, target, true),
                searchRange(nums, target, false)
        };
    }

    public int searchRange(int[] nums, int target, boolean isLeft) {
        int low = 0;
        int high = nums.length - 1;
        int index = -1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = nums[mid];

            if (target == midValue) {
                index = mid;
                if (isLeft) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else if (target < midValue) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return index;
    }
}
