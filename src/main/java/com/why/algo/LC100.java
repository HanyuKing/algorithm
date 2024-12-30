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

    @Test
    public void testP62() {
        print(uniquePaths(3, 7)); // 28
        print(uniquePaths(3, 2)); // 3
    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j] + dp[j - 1];
            }
        }
        return dp[n - 1];
    }

    @Test
    public void testP63() {
        int[][] obstacleGrid = {
                {0,0,0},
                {0,1,0},
                {0,0,0}
        };
        print(uniquePathsWithObstacles(obstacleGrid)); // 2

        obstacleGrid = new int[][]{
                {0, 1},
                {0, 0}
        };
        print(uniquePathsWithObstacles(obstacleGrid)); // 1

        obstacleGrid = new int[][]{
                {1, 0}
        };
        print(uniquePathsWithObstacles(obstacleGrid)); // 0

    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 1) {
                while (i < n) {
                    dp[i++] = 0;
                }
            } else {
                dp[i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } else {
                    if (j != 0) {
                        dp[j] = dp[j] + dp[j - 1];
                    }
                }
            }
        }
        return dp[n - 1];
    }

    @Test
    public void testP64() {
        int[][] grid = new int[][] {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        print(minPathSum(grid)); // 7
    }

    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                dp[i] = grid[0][i];
            } else {
                dp[i] = dp[i - 1] + grid[0][i];
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (j == 0) {
                    dp[j] = dp[j] + grid[i][j];
                } else {
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[n - 1];
    }

    @Test
    public void testP66() {
        print(plusOne(new int[]{1,2,3}));
        print(plusOne(new int[]{9}));
    }

    public int[] plusOne(int[] digits) {
        int end = digits.length - 1;
        int carry = 1;
        while (end >= 0) {
            int num = digits[end] + carry;
            digits[end] = num % 10;
            carry = num / 10;
            end--;
        }
        if (carry > 0) {
            int[] newDigits = new int[digits.length + 1];
            System.arraycopy(digits, 0, newDigits, 1, digits.length);
            newDigits[0] = carry;
            return newDigits;
        } else {
            return digits;
        }
    }

    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            if (++digits[i] != 10) {
                return digits;
            } else {
                digits[i] = 0;
            }
        }
        int[] ans = new int[digits.length + 1];
        ans[0] = 1;
        return ans;
    }


    @Test
    public void testP67() {
        String a = "1010";
        String b = "1011";
        print(addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        int carry = 0;
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >= 0) {
            if (i >= 0 && j >= 0) {
                int num = a.charAt(i) + b.charAt(j) - 2 * '0' + carry;
                carry = num / 2;
                sb.append(num % 2);
                i--;
                j--;
            } else if (i >= 0) {
                int num = a.charAt(i) - '0' + carry;
                carry = num / 2;
                sb.append(num % 2);
                i--;
            } else {
                int num = b.charAt(j) - '0' + carry;
                carry = num / 2;
                sb.append(num % 2);
                j--;
            }
        }
        if (carry > 0) {
            sb.append(carry);
        }
        return sb.reverse().toString();
    }

    @Test
    public void testP69() {
//        print(mySqrt(8));

        print(mySqrt(2147395599));
    }

    public int mySqrt(int x) {
        int low = 1;
        int high = x;
        while (low < high) {
            int mid = low + (high - low) / 2;
            long val = (long) mid * mid;
            if (val == x) {
                return mid;
            } else if (val < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low * low <= x) {
            return low;
        } else {
            return low - 1;
        }
    }

    @Test
    public void testP70() {
        print(climbStairs(3));
    }

    public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int prePre = 1;
        int pre = 2;
        n = n - 2;

        int ans = 0;
        while (n-- > 0) {
            ans = prePre + pre;
            prePre = pre;
            pre = ans;
        }

        return ans;
    }

    @Test
    public void testP71() {
        print(simplifyPath("/home/"));
        print(simplifyPath("/home//foo/"));
        print(simplifyPath("/home/user/Documents/../Pictures"));
        print(simplifyPath("/../"));
        print(simplifyPath("//.../a/../b/c/../d/./"));
    }

    public String simplifyPath(String path) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < path.length(); i++) {
            char curr = path.charAt(i);
            if (i > 0 && curr == path.charAt(i - 1) && curr == '/') {
                continue;
            }
            s.append(curr);
        }
        String[] paths = s.toString().split("/");
        Stack<String> newPathStack = new Stack<>();
        for (String p : paths) {
            if (p.equals(".")) {
                continue;
            } else if (p.equals("..")) {
                if(!newPathStack.isEmpty()) {
                    newPathStack.pop();
                }
            } else {
                if ("".contentEquals(p)) {
                    continue;
                }
                newPathStack.add("/" + p);
            }
        }
        StringBuilder newPath = new StringBuilder();
        for (String np : newPathStack) {
            newPath.append(np);
        }
        if ("".contentEquals(newPath)) {
            return "/";
        }
        return newPath.toString();
    }

    @Test
    public void testP72() {
        String word1 = "pneumonoultramicroscopicsilicovolcanoconiosis";
        String word2 = "ultramicroscopically";
        print(minDistance(word1, word2)); // 27
    }

    public int minDistance(String word1, String word2) {
        /*
             "" h t t p
          "" 0  1 2 3 4
          t  1  1 1 2 3
          t  2  2 1 1 2
         */
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public int minDistance2(String word1, String word2) {
        /*
             h o r s e
           r 1 2 2 3 4
           o 2 1 2 3 4
           s 3 2 3 2 3

            h t t p
          t 1 1 2 3
          t 2 1 1 2
             "" h t t p
          "" 0  1 2 3 4
          t  1  1 1 2 3
          t  2  2 1 1 2
         */
        int[][] dp = new int[word1.length()][word2.length()];
        boolean used = false;
        for (int i = 0; i < word2.length(); i++) {
            if (!used && word2.charAt(i) == word1.charAt(0)) {
                if (i > 0) {
                    dp[0][i] = dp[0][i - 1];
                } else {
                    dp[0][0] = 0;
                }
                used = true;
            } else {
                if (i > 0) {
                    dp[0][i] = dp[0][i - 1] + 1;
                } else {
                    dp[0][0] = 1;
                }
            }
        }
        used = false;
        for (int i = 1; i < word1.length(); i++) {
            if (!used && word1.charAt(i) == word2.charAt(0)) {
                dp[i][0] = dp[i - 1][0];
                used = true;
            } else {
                dp[i][0] = dp[i - 1][0] + 1;
            }
        }
        for (int i = 1; i < word1.length(); i++) {
            for (int j = 1; j < word2.length(); j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[word1.length() - 1][word2.length() - 1];
    }

    @Test
    public void testP73() {
        int[][] matrix = new int[][]{
                {0,1,2,0},
                {3,4,5,2},
                {1,3,1,5}
        };
        setZeroes(matrix);
        print(matrix);
    }

    public void setZeroes(int[][] matrix) {
        boolean firstRowHasZero = false;
        boolean firstColHasZero = false;
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                firstRowHasZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) {
                firstColHasZero = true;
                break;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < n; i++) {
            if (matrix[0][i] == 0) {
                for (int j = 1; j < m; j++) {
                    matrix[j][i] = 0;
                }
            }
        }
        for (int i = 1; i < m; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 1; j < n; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (firstRowHasZero) {
            for (int i = 0; i < n; i++) {
                matrix[0][i] = 0;
            }
        }
        if (firstColHasZero) {
            for (int i = 0; i < m; i++) {
                matrix[i][0] = 0;
            }
        }
    }

    @Test
    public void testP77() {
        print(combine(4, 2));
        print(combine(1, 1));
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        doCombine(result, new ArrayList<>(), n, k);
        return result;
    }

    public void doCombine(List<List<Integer>> result,
                          List<Integer> selected,
                          int n,
                          int k) {
        if (k == 0) {
            result.add(new ArrayList<>(selected));
            return ;
        }
        for (int i = n; i > 0; i--) {
            selected.add(i);
            doCombine(result, selected, i - 1, k - 1);
            selected.remove(selected.size() - 1);
        }
    }

    @Test
    public void testP78() {
        print(subsets(new int[] {1,2,3})); // [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
        print(subsets(new int[] {0})); // [[],[0]]
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int len = 0; len <= nums.length; len++) {
            List<List<Integer>> resultT = new ArrayList<>();
            doSubsets(nums, resultT, new ArrayList<>(), 0, len);
            result.addAll(resultT);
        }
        return result;
    }

    public void doSubsets(int[] nums,
                          List<List<Integer>> result,
                          List<Integer> selected,
                          int index,
                          int length) {
        if (length == 0) {
            result.add(new ArrayList<>(selected));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            selected.add(nums[i]);
            doSubsets(nums, result, selected, i + 1,length - 1);
            selected.remove(selected.size() - 1);
        }
    }

    @Test
    public void testP79() {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        String word = "ABCCEESEC";
        print(exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean exist2(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (exist2(board, i, j, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }
    private boolean[][] visited;
    public boolean exist2(char[][] board,
                         int i,
                         int j,
                         int index,
                         String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j >= board[0].length || i >= board.length || j < 0) {
            return false;
        }
        char targetChar = word.charAt(index);
        boolean exist = false;
        if (!visited[i][j] && board[i][j] == targetChar) {
            visited[i][j] = true;
            exist = exist(board, i, j - 1, index + 1, word);
            if (!exist) {
                exist = exist(board, i, j + 1, index + 1, word);
            }
            if (!exist) {
                exist = exist(board, i - 1, j, index + 1, word);
            }
            if (!exist) {
                exist = exist(board, i + 1, j, index + 1, word);
            }
            visited[i][j] = false;
        }
        return exist;
    }
    public boolean exist(char[][] board,
                         int i,
                         int j,
                         int index,
                         String word) {
        if (index == word.length()) {
            return true;
        }
        if (i < 0 || j >= board[0].length || i >= board.length || j < 0) {
            return false;
        }
        char targetChar = word.charAt(index);
        boolean exist = false;
        if (board[i][j] != '0' && board[i][j] == targetChar) {
            char temp = board[i][j];
            board[i][j] = '0';
            exist = exist(board, i, j - 1, index + 1, word);
            if (!exist) {
                exist = exist(board, i, j + 1, index + 1, word);
            }
            if (!exist) {
                exist = exist(board, i - 1, j, index + 1, word);
            }
            if (!exist) {
                exist = exist(board, i + 1, j, index + 1, word);
            }
            board[i][j] = temp;
        }
        return exist;
    }

    @Test
    public void testP80() {
        print(removeDuplicates(new int[] {1,1,1,2,2,3})); // 5. [1,1,2,2,3]
        print(removeDuplicates(new int[] {0,0,1,1,1,1,2,3,3})); // 7. [0,0,1,1,2,3,3]

    }

    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }
        int newIndex = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                if (count == 1) {
                    count++;
                    nums[newIndex++] = nums[i];
                }
            } else {
                nums[newIndex++] = nums[i];
                count = 1;
            }
        }

        return newIndex;
    }

    @Test
    public void testP81() {
        print(search(new int[] {0,0,1,1,2,0}, 2)); // true
        print(search(new int[] {1,0,1,1,1}, 0)); // true
        print(search(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1}, 2)); // true
    }

    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    public boolean search(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (target == midVal) {
                return true;
            }

            if (nums[low] == nums[high] && nums[high] == midVal) {
                return search(nums, low, mid - 1, target) || search(nums, mid + 1, high, target);
            }

            if (target < midVal) {
                if (nums[low] <= midVal) {
                    if (target >= nums[low]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    high = mid - 1;
                }
            } else {
                if (midVal <= nums[high]) {
                    if (target <= nums[high]) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                } else {
                    low = mid + 1;
                }
            }
        }

        return false;
    }

    @Test
    public void testP82() {
        // head = [1,1,1,2,3]  2,3
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        print(deleteDuplicates(n1));
    }

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = new ListNode();
        ListNode tail = newHead;
        ListNode pre = head;
        boolean preDuplicate = false;
        head = head.next;
        while (head != null) {
            if (head.val != pre.val) {
                if (!preDuplicate) {
                    pre.next = null;
                    tail.next = pre;
                    tail = pre;
                } else {
                    preDuplicate = false;
                }
                pre = head;
            } else {
                preDuplicate = true;
            }
            head = head.next;
        }
        if (!preDuplicate) {
            tail.next = pre;
        }
        return newHead.next;
    }

    @Test
    public void testP83() {
        // head = [1,1,1,2,3]  1,2,3
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        print(deleteDuplicates2(n1));
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode tail = head;
        ListNode pHead = head.next;
        tail.next = null;
        while (pHead != null) {
            if (pHead.val != pre.val) {
                pre = pHead;
                tail.next = pHead;
                tail = pHead;
                pHead = pHead.next;
                tail.next = null;
            } else {
                pHead = pHead.next;
            }
        }
        return head;
    }

    @Test
    public void testP86() {
// head = [1,1,1,2,3]  1,2,3
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(2);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        print(partition(n1, 3));
    }
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode l1 = new ListNode();
        ListNode l2 = new ListNode();
        ListNode l1Tail = l1;
        ListNode l2Tail = l2;
        while (head != null) {
            if (head.val < x) {
                l1Tail.next = head;
                l1Tail = head;
                head = head.next;
                l1Tail.next = null;
            } else {
                l2Tail.next = head;
                l2Tail = head;
                head = head.next;
                l2Tail.next = null;
            }
        }
        l1Tail.next = l2.next;
        return l1.next;
    }

    @Test
    public void testP88() {
        int[] nums1 = new int[] {1};
        int m = 1;
        int[] nums2 = new int[] {};
        int n = 0;
        merge(nums1, m, nums2, n);
        print(nums1);
    }
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = nums1.length - 1;
        while (i >= 0 || j >= 0) {
            if (i >= 0 && j >= 0) {
                if (nums1[i] > nums2[j]) {
                    nums1[index--] = nums1[i--];
                } else {
                    nums1[index--] = nums2[j--];
                }
            } else if (i >= 0) {
                return;
            } else {
                nums1[index--] = nums2[j--];
            }
        }
    }
}
