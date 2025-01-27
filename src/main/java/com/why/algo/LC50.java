package com.why.algo;

import org.junit.Test;

import java.util.*;

/**
 * @Author Hanyu.Wang
 * @Date 2024/12/19 10:25
 * @Description
 * @Version 1.0
 **/
public class LC50 extends Base {

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

    @Test
    public void testP35() {
        int[] nums = new int[] {1,3,5,6};
        int target = 5;
        print(searchInsert(nums, target));

        nums = new int[] {1,3,5,6};
        target = 2;
        print(searchInsert(nums, target));
    }

    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int insertIndex = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (target == midVal) {
                return mid;
            } else if (target < midVal) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    @Test
    public void testP36() {
        char[][] board = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        print(isValidSudoku(board));

        board[0][0] = '8';
        print(isValidSudoku(board));
    }

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') {
                    continue;
                }
                if (!set.contains(board[i][j])) {
                    set.add(board[i][j]);
                } else {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = 0; j < 9; j++) {
                if (board[j][i] == '.') {
                    continue;
                }
                if (!set.contains(board[j][i])) {
                    set.add(board[j][i]);
                } else {
                    return false;
                }
            }
        }
        for (int i = 0; i < 9; i += 3) {
            for (int j = 0; j < 9; j += 3) {
                Set<Character> set = new HashSet<>();
                for (int ii = i; ii < i + 3; ii++) {
                    for (int jj = j; jj < j + 3; jj++) {
                        if (board[ii][jj] == '.') {
                            continue;
                        }
                        if (!set.contains(board[ii][jj])) {
                            set.add(board[ii][jj]);
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    @Test
    public void testP38() {
        print(countAndSay(1));
        print(countAndSay(2));
        print(countAndSay(3));
        print(countAndSay(4));
    }

    public String countAndSay2(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            s = rle(s.toCharArray());
        }
        return s;
    }

    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return rle(countAndSay(n - 1).toCharArray());
    }
    private String rle(char[] num) {
        char pre = '-';
        int count = 0;
        StringBuilder newS = new StringBuilder();
        for (char c : num) {
            if (pre == '-') {
                pre = c;
                count = 1;
            } else {
                if (c == pre) {
                    count++;
                } else {
                    newS.append(count).append(pre);
                    count = 1;
                    pre = c;
                }
            }
        }
        newS.append(count).append(pre);
        return newS.toString();
    }

    @Test
    public void testP39() {
        int[] candidates = new int[] {2,3,6,7};
        int target = 7;
        print(combinationSum(candidates, target));
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        doCombinationSum(candidates, result, 0, new ArrayList<>(), target);
        return result;
    }

    public void doCombinationSum(int[] candidates,
                                 List<List<Integer>> result,
                                 int index,
                                 List<Integer> selected,
                                 int target) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = index; i < candidates.length && target >= candidates[i]; i++) {
            selected.add(candidates[i]);
            doCombinationSum(candidates, result, i, selected, target - candidates[i]);
            selected.remove(selected.size() - 1);
        }
    }

    @Test
    public void testP40() {
        int[] candidates = new int[] {10,1,2,7,6,1,5}; // 1,1,2,5,6,7,10
        int target = 8;
        print(combinationSum2(candidates, target));

    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        doCombinationSum2(candidates, result, 0, new ArrayList<>(), target);
        return result;
    }

    public void doCombinationSum2(int[] candidates,
                                 List<List<Integer>> result,
                                 int index,
                                 List<Integer> selected,
                                 int target) {
        if (target < 0) {
            return;
        }

        if (target == 0) {
            result.add(new ArrayList<>(selected));
            return;
        }

        for (int i = index; i < candidates.length; i++) {
            if (i > index && candidates[i - 1] == candidates[i]) {
                continue;
            }
            selected.add(candidates[i]);
            doCombinationSum2(candidates, result, i + 1, selected, target - candidates[i]);
            selected.remove(selected.size() - 1);
        }
    }

    @Test
    public void testP42() {
        int[] height = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        print(trap2(height));
    }

    public int trap2(int[] height) {
        int left = 0;
        int leftMax = 0;
        int right = height.length - 1;
        int rightMax = 0;
        int result = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] < leftMax) {
                    result += leftMax - height[left];
                }

                leftMax = Math.max(leftMax, height[left]);
                left++;
            } else {
                if (height[right] < rightMax) {
                    result += rightMax - height[right];
                }

                rightMax = Math.max(rightMax, height[right]);
                right--;
            }
        }
        return result;
    }

    public int trap(int[] height) {
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = 0;
        rightMax[n - 1] = 0;
        int max = 0;
        for (int i = 1; i < n; i++) {
            max = Math.max(max, height[i - 1]);
            leftMax[i] = max;
        }
        max = 0;
        for (int i = n - 2; i > 0; i--) {
            max = Math.max(max, height[i + 1]);
            rightMax[i] = max;
        }
        int result = 0;
        for (int i = 1; i < n - 1; i++) {
            int min = Math.min(leftMax[i], rightMax[i]);
            result += Math.max(0, min - height[i]);
        }
        return result;
    }

    @Test
    public void testP43() {
        String num1 = "999";
        String num2 = "999";
        print(multiply(num1, num2));
    }

    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) {
            String temp = num1;
            num1 = num2;
            num2 = temp;
        }
        if (num2.equals("0")) {
            return "0";
        }
        int carry = 0;
        String result = "";
        int n2 = num2.length() - 1;
        StringBuilder zero = new StringBuilder();

        while (n2 >= 0) {
            int n2Val = num2.charAt(n2) - '0';

            int n1 = num1.length() - 1;
            StringBuilder sb = new StringBuilder();
            while (n1 >= 0) {
                int n1Val = num1.charAt(n1) - '0';
                int multiVal = n1Val * n2Val + carry;
                carry = multiVal / 10;
                sb.append(multiVal % 10);

                n1--;
            }
            if (carry > 0) {
                sb.append(carry);
                carry = 0;
            }
            sb.reverse().append(zero);
            result = addTowStringNum(result, sb.toString());

            zero.append(0);
            n2--;
        }

        return result;
    }

    public String addTowStringNum(String num1, String num2) {
        int carry = 0;
        int n1 = num1.length() - 1;
        int n2 = num2.length() - 1;
        StringBuilder result = new StringBuilder();
        while (n2 >= 0 || n1 >= 0) {
            if (n1 >= 0 && n2 >= 0) {
                int val = num1.charAt(n1) - '0' + num2.charAt(n2) - '0' + carry;
                carry = val / 10;
                result.append(val % 10);
                n2--;
                n1--;
            } else if (n1 >= 0) {
                int val = num1.charAt(n1) - '0' + carry;
                carry = val / 10;
                result.append(val % 10);
                n1--;
            } else {
                int val = num2.charAt(n2) - '0' + carry;
                carry = val / 10;
                result.append(val % 10);
                n2--;
            }
        }
        if (carry > 0) {
            result.append(carry);
        }
        return result.reverse().toString();
    }

    @Test
    public void testP45() {
        int[] nums = new int[] {2,3,1,1,4};
        print(jump(nums));

        nums = new int[] {2,3,0,1,4};
        print(jump(nums));
    }
    public int jump(int[] nums) {
        int step = 0;
        int maxIndex = 0;
        int end = 0;
        for (int i = 0; i < nums.length; i++) {
            maxIndex = Math.max(maxIndex, i + nums[i]);
            if (maxIndex >= nums.length - 1) {
                step++;
                break;
            }
            if (i == end) {
                end = maxIndex;
                step++;
            }
        }
        return step;
    }
    public int jump2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] + j - i >= 0) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[nums.length - 1];
    }

    @Test
    public void testP46() {
        int[] nums = new int[] {1,2,3};
        print(permute(nums));
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        doPermute(nums, result, new ArrayList<>(), 0);
        return result;
    }

    public void doPermute(int[] nums,
                          List<List<Integer>> result,
                          List<Integer> values,
                          int index) {
        if (values.size() == nums.length) {
            result.add(new ArrayList<>(values));
            return ;
        }
        for (int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            values.add(nums[index]);
            doPermute(nums, result, values, index + 1);
            values.remove(values.size() - 1);
            swap(nums, i, index);
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void testP47() {
        print(permuteUnique(new int[] {2,2,1,1}));
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        doPermuteUnique(nums, result, new ArrayList<>(), new boolean[nums.length], 0);
        return result;
    }

    public void doPermuteUnique(int[] nums,
                          List<List<Integer>> result,
                          List<Integer> values,
                          boolean[] visited,
                          int index) {
        if (values.size() == nums.length) {
            result.add(new ArrayList<>(values));
            return ;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && nums[i] == nums[i - 1])) {
                continue;
            }
            visited[i] = true;
            values.add(nums[i]);
            doPermuteUnique(nums, result, values, visited,index + 1);
            values.remove(values.size() - 1);
            visited[i] = false;
        }
    }

    @Test
    public void testP48() {
        int[][] matrix = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        rotate(matrix);
        print(matrix);
    }

    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
             }
        }
        for (int i = 0; i < n; i++) {
            int start = 0;
            int end = n - 1;
            while (start < end) {
                int temp = matrix[i][start];
                matrix[i][start] = matrix[i][end];
                matrix[i][end] = temp;
                start++;
                end--;
            }
        }
    }

    @Test
    public void testP49() {
        String[] strs = new String[] {"eat", "tea", "tan", "ate", "nat", "bat"};
                                  // [["bat"],["nat","tan"],["ate","eat","tea"]]
        print(groupAnagrams(strs));
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> anagrams = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (anagrams.containsKey(key)) {
                anagrams.get(key).add(str);
            } else {
                List<String> anagram = new ArrayList<>();
                anagram.add(str);
                anagrams.put(key, anagram);
            }
        }
        return new ArrayList<>(anagrams.values());
    }

    @Test
    public void testP50() {
        print(myPow3(2, 10));
        print(myPow3(2.1, 3));
        print(myPow3(2, -2));
        print(myPow3(0.44528, 0));
    }
    public double myPow3(double x, int n) {
        /*
            f(10) = f(5) * f(5)
            f(6) = f(3) * f(3)
            f(5) = f(2) * f(2) * x
            f(4) = f(2) * f(2)
            f(3) = f(1) * f(1) * x
            f(2) = f(1) * f(1)
            f(1) = 2
         */
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double result = 1;
        double contribute = x;
        int absN = Math.abs(n);
        while (absN > 0) {
            // 10100
            // 11
            if ((absN & 1) == 1) {
                result *= contribute;
            }
            contribute = contribute * contribute;
            absN /= 2;
        }
        return n > 0 ? result : 1 / result;
    }
    public double myPow(double x, int n) {
        /*
            2 * 2 = 2^2
            2^2 * 2^2 = 2^4
            2^4 * 2^4 = 2^8

            f(10) = f(5) * f(5)
            f(6) = f(3) * f(3)
            f(5) = f(2) * f(2) * x
            f(4) = f(2) * f(2)
            f(3) = f(1) * f(1) * x
            f(2) = f(1) * f(1)
            f(1) = 2
         */

        double result = doMyPow(x, Math.abs(n));

        return n > 0 ? result : 1 / result;
    }

    public double doMyPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double result = doMyPow(x, n / 2);
        result *= result;
        if ((n & 1) == 1) {
            result *= x;
        }
        return result;
    }


    public double myPow2(double x, int n) {
        double result = 1;
        int absN = Math.abs(n);
        while (absN-- > 0) {
            result *= x;
        }
        return n > 0 ? result : 1 / result;
    }
}
