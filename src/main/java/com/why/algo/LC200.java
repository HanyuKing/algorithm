package com.why.algo;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.UnaryOperator;
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

    @Test
    public void testP189() {
        rotate(new int[] {-1}, 2);
    }

    public void rotate(int[] nums, int k) {
        // 1,2,3,4,5,6,7
        // 5,6,7,1,2,3,4

        // 4,3,2,1,7,6,5,
        k = k % nums.length;
        reverse(nums, 0, nums.length - k - 1);
        reverse(nums, nums.length - k, nums.length - 1);
        reverse(nums, 0, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int t = nums[start];
            nums[start] = nums[end];
            nums[end] = t;
            start++;
            end--;
        }
    }

    @Test
    public void testP190() {
        print(reverseBits(43261596));
        // print(reverseBits(4294967293));
    }

    public int reverseBits(int n) {
        return Integer.reverse(n);
    }

    @Test
    public void testP191() {
        print(hammingWeight(2147483645));
    }

    public int hammingWeight(int n) {
        int ans = 0;
        while (n > 0) {
            ans += n & 1;
            n = n >> 1;
        }
        return ans;
    }

    @Test
    public void testP193() {

    }

    @Test
    public void testP197() {
        // PASS
        // SQL
    }

    @Test
    public void testP198() {
        print(rob(new int[] {2,1,1,2}));
        print(rob(new int[] {1,2,3,1}));
        print(rob(new int[] {2,7,9,3,1}));
    }

    public int rob(int[] nums) {
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

    public int rob2(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = dp[i - 2] + nums[i - 1];
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }
        return dp[nums.length];
    }

    @Test
    public void testP199() {

    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelCount = 1;
        while (!queue.isEmpty()) {
            int currCount = 0;
            while (levelCount-- > 0) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                    currCount++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    currCount++;
                }
                if (levelCount == 0) {
                    result.add(node.val);
                }
             }
            levelCount = currCount;
        }
        return result;
    }

    @Test
    public void testP200() {

    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    numIslandsDFS(grid, i, j, m, n);
                    count++;
                }
            }
        }
        return count;
    }

    public void numIslandsDFS(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '2';
        numIslandsDFS(grid, i - 1, j, m, n);
        numIslandsDFS(grid, i + 1, j, m, n);
        numIslandsDFS(grid, i, j - 1, m, n);
        numIslandsDFS(grid, i, j + 1, m, n);
    }
}
