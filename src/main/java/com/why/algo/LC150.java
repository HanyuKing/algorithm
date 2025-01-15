package com.why.algo;

import com.sun.jmx.snmp.SnmpNull;
import org.junit.Test;

import java.util.*;

public class LC150 extends Base {
    @Test
    public void testP101() {

    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null || root.left == null && root.right == null) {
            return true;
        }
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode leftRoot, TreeNode rightRoot) {
        if (leftRoot == null && rightRoot == null) {
            return true;
        } else if (leftRoot == null || rightRoot == null) {
            return false;
        } else {
            return leftRoot.val == rightRoot.val
                    && isSymmetric(leftRoot.left, rightRoot.right)
                    && isSymmetric(rightRoot.left, leftRoot.right);
        }
    }

    @Test
    public void testP102() {

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        int levelCount = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int newLevelCount = 0;
            List<Integer> level = new ArrayList<>();
            while (levelCount-- > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                    newLevelCount++;
                }
                if (node.right != null) {
                    queue.offer(node.right);
                    newLevelCount++;
                }
            }
            levelCount = newLevelCount;
            result.add(level);
        }

        return result;
    }

    @Test
    public void testP104() {

    }

    int max = 0;
    public int maxDepth(TreeNode root) {
        maxDepth(root, 0);
        return max;
    }

    public void maxDepth(TreeNode root, int depth) {
        if (root == null) {
            return ;
        }
        max = Math.max(depth, max);
        maxDepth(root.left, depth + 1);
        maxDepth(root.right, depth + 1);
    }

    @Test
    public void testP108() {

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length / 2, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int l, int mid, int r) {
        TreeNode root = new TreeNode(nums[mid]);
        if (l <= mid - 1) {
            root.left = sortedArrayToBST(nums, l, l + (mid - 1 - l) / 2, mid - 1);
        }
        if (mid + 1 <= r) {
            root.right = sortedArrayToBST(nums, mid + 1, (mid + 1) + (r - mid - 1) / 2, r);
        }
        return root;
    }

    @Test
    public void testP109() {

    }

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(slow.next);
        return root;
    }

    @Test
    public void testP110() {

    }

    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return treeDepth(root) > 1;
    }

    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = treeDepth(root.left);
        int rightDepth = treeDepth(root.right);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    @Test
    public void testP111() {

    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftMin = 0;
        int rightMin = 0;
        if (root.left != null) {
            leftMin = minDepth(root.left);
        }
        if (root.right != null) {
            rightMin = minDepth(root.right);
        }

        return root.left == null || root.right == null ? leftMin + rightMin + 1: Math.min(leftMin, rightMin) + 1;
    }

    @Test
    public void testP112() {

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null && root.val == targetSum) {
            return true;
        }
        return hasPathSum(root.left, targetSum - root.val)
                || hasPathSum(root.right, targetSum - root.val);
    }

    @Test
    public void testP113() {

    }

    List<List<Integer>> pathSumResult = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        doPathSum(root, new ArrayList<>(), targetSum);
        return pathSumResult;
    }

    public void doPathSum(TreeNode root, List<Integer> currPath, int targetSum) {
        if (root == null) {
            return;
        }
        currPath.add(root.val);
        if(root.left == null && root.right == null && root.val == targetSum) {
            pathSumResult.add(new ArrayList<>(currPath));
            currPath.remove(currPath.size() - 1);
            return ;
        }
        doPathSum(root.left, currPath, targetSum - root.val);
        doPathSum(root.right, currPath, targetSum - root.val);
        currPath.remove(currPath.size() - 1);
    }

    @Test
    public void testP116() {

    }

    public Node connect(Node root) {
        if (root == null) {
            return root;
        }
        Node leftmost = root;
        while (leftmost.left != null) {
            Node head = leftmost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftmost = leftmost.left;
        }
        return root;
    }

    @Test
    public void testP117() {

    }

    public Node connect117(Node root) {
        if (root == null) {
            return root;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        int layerSize = 1;
        while (!queue.isEmpty()) {
            int nextLayerSize = 0;
            Node tail = null;
            for (int i = 1; i <= layerSize; i++) {
                if (i == 1) {
                    tail = queue.poll();
                } else {
                    Node node = queue.poll();
                    tail.next = node;
                    tail = node;
                }
                if (tail.left != null) {
                    queue.offer(tail.left);
                    nextLayerSize++;
                }
                if (tail.right != null) {
                    queue.offer(tail.right);
                    nextLayerSize++;
                }
            }
            layerSize = nextLayerSize;
        }
        return root;
    }

    @Test
    public void testP120() {

    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int maxCol = triangle.get(triangle.size() - 1).size();
        int[] dp = new int[maxCol];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = triangle.get(0).get(0);
        int m = triangle.size();

        if (m == 1) {
            return dp[0];
        }

        int minniNum = Integer.MAX_VALUE;
        for (int i = 1; i < m; i++) {
            List<Integer> rowNumberList = triangle.get(i);
            int n = rowNumberList.size();
            for (int j = n - 1; j >= 0; j--) {
                if (j == 0) {
                    dp[j] = dp[j] + rowNumberList.get(j);
                } else {
                    dp[j] = Math.min(dp[j], dp[j - 1]) + rowNumberList.get(j);
                }
                if (i == m - 1) {
                    minniNum = Math.min(minniNum, dp[j]);
                }
            }
        }
        return minniNum;
    }

    @Test
    public void testP121() {
        print(maxProfit(new int[] {7,1,5,3,6,4}));
    }

    public int maxProfit(int[] prices) {
        int min = prices[0];
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > min) {
                maxProfit = Math.max(maxProfit, prices[i] - min);
            }
            min = Math.min(min, prices[i]);
        }

        return maxProfit;
    }

    @Test
    public void testP122() {
        print(maxProfit122(new int[] {7,1,5,3,6,4})); // 7
        print(maxProfit122DP(new int[] {1,2,3,4,5})); // 4

        print(maxProfit122DP2(new int[] {1,3,6,4})); // 5
    }

    public int maxProfit122DP2(int[] prices) {
        int out = 0;
        int buy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            int currOut = Math.max(out, buy + prices[i]);
            int currBuy = Math.max(out - prices[i], buy);
            out = currOut;
            buy = currBuy;
        }
        return out;
    }

    public int maxProfit122DP(int[] prices) {
        int[][] dp = new int[prices.length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[prices.length - 1][0];
    }


    public int maxProfit122(int[] prices) {
        int max = -1;
        int maxProfit = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            if (prices[i] < max) {
                maxProfit += max - prices[i];
                max = -1;
            }
            max = Math.max(max, prices[i]);
        }

        return maxProfit;
    }

    @Test
    public void testP125() {
        print(isPalindrome("A man, a plan, a canal: Panama"));
    }

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        char[] chars = s.toLowerCase().toCharArray();
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (!((chars[start] >= 'a' && chars[start] <= 'z')
                    || (chars[start] >= '0' && chars[start] <= '9'))) {
                start++;
                continue;
            }
            if (!((chars[end] >= 'a' && chars[end] <= 'z')
                    || (chars[end] >= '0' && chars[end] <= '9'))) {
                end--;
                continue;
            }
            if (chars[start++] != chars[end--]) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testP128() {
        print(longestConsecutive(new int[] {0,0,1,-1}));
        print(longestConsecutive(new int[] {100,4,200,1,3,2}));
        print(longestConsecutive(new int[] {0,3,7,2,5,8,4,6,0,1}));
    }

    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : nums) {
            numCountMap.put(num, 1);
        }
        int max = 0;
        for (int num : nums) {
            Integer count = numCountMap.get(num);
            if (count == null) {
                continue;
            }
            int numTemp = num;
            Integer preNumCount = numCountMap.get(--numTemp);
            while (preNumCount != null) {
                count += preNumCount;
                numCountMap.remove(numTemp);
                preNumCount = numCountMap.get(--numTemp);
            }
            numCountMap.put(num, count);
            max = Math.max(max, count);
        }
        return max;
    }

    @Test
    public void testP129() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        print(sumNumbers(root));
    }

    int sum129 = 0;
    public int sumNumbers(TreeNode root) {
        sumNumbers(root, 0);
        return sum129;
    }

    public void sumNumbers(TreeNode root, int preNum) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            sum129 += preNum * 10 + root.val;
            return;
        }
        sumNumbers(root.left, preNum * 10 + root.val);
        sumNumbers(root.right, preNum * 10 + root.val);
    }

    @Test
    public void testP130() {
        char[][] board = new char[][] {
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}
        };
        // solve(board);
        bfsSolve(board);
        print(board);
    }

    public void bfsSolve(char[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                queue.offer(new int[] {i, 0});
                board[i][0] = 'A';
            }
            if (board[i][board[0].length - 1] == 'O') {
                board[i][board[0].length - 1] = 'A';
                queue.offer(new int[] {i, board[0].length - 1});
            }
        }
        for (int j = 0; j < board[0].length; j++) {
            if (board[board.length - 1][j] == 'O') {
                queue.offer(new int[] {board.length - 1, j});
                board[board.length - 1][j] = 'A';
            }
            if (board[0][j] == 'O') {
                queue.offer(new int[] {0, j});
                board[0][j] = 'A';
            }
        }
        int col = board[0].length;
        int row = board.length;
        while (!queue.isEmpty()) {
            int[] ij = queue.poll();
            int i = ij[0];
            int j = ij[1];
            if (i + 1 >= 0 && i + 1 < row && j < col && j >= 0 && board[i + 1][j] == 'O') {
                board[i + 1][j] = 'A';
                queue.offer(new int[] {i + 1, j});
            }
            if (i - 1 >= 0 && i - 1 < row && j < col && j >= 0 && board[i - 1][j] == 'O') {
                board[i - 1][j] = 'A';
                queue.offer(new int[] {i - 1, j});
            }
            if (i >= 0 && i < row && j - 1 < col && j - 1 >= 0 && board[i][j - 1] == 'O') {
                board[i][j - 1] = 'A';
                queue.offer(new int[] {i, j - 1});
            }
            if (i >= 0 && i < row && j + 1 < col && j + 1 >= 0 && board[i][j + 1] == 'O') {
                board[i][j + 1] = 'A';
                queue.offer(new int[] {i, j + 1});
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                } else if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            dfsSolve(board, i, 0);
            dfsSolve(board, i, board[0].length - 1);
        }
        for (int j = 0; j < board[0].length; j++) {
            dfsSolve(board, board.length - 1, j);
            dfsSolve(board, 0, j);
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '2') {
                    board[i][j] = 'O';
                } else {
                    board[i][j] = 'X';
                }
            }
        }
    }

    public void dfsSolve(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length
                || j < 0 || j >= board[0].length
                || board[i][j] == '1'
                || board[i][j] == '2') {
            return;
        }
        if (board[i][j] == 'O') {
            board[i][j] = '2';
            dfsSolve(board, i - 1, j);
            dfsSolve(board, i + 1, j);
            dfsSolve(board, i, j - 1);
            dfsSolve(board, i, j + 1);
        } else {
            board[i][j] = '1';
        }
    }

    @Test
    public void testP131() {
        print(partition("aab"));
    }

    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] f = new boolean[n][n];
        for (int i = 0 ; i < n; i++) {
            Arrays.fill(f[i], true);
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                f[i][j] = s.charAt(i) == s.charAt(j) && f[i + 1][j - 1];
            }
        }
        List<List<String>> result = new ArrayList<>();
        dfsPartition(result, new ArrayList<>(), s, f, 0);
        return result;
    }

    private void dfsPartition(List<List<String>> result,
                              List<String> curr,
                              String s,
                              boolean[][] f,
                              int i) {
        if (i == s.length()) {
            result.add(new ArrayList<>(curr));
            return;
        }
        for (int j = i; j < s.length(); j++) {
            if (f[i][j]) {
                curr.add(s.substring(i, j + 1));
                dfsPartition(result, curr, s, f, j + 1);
                curr.remove(curr.size() - 1);
            }
        }
    }

    @Test
    public void testP134() {
        print(canCompleteCircuit(new int[] {1,2,3,4,5}, new int[] {3,4,5,1,2}));
        print(canCompleteCircuit(new int[] {2,3,4}, new int[] {3,4,3}));
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int gasTotal = 0;
        for (int i = 0; i < gas.length; i++) {
            gasTotal = gas[i];
            if (gasTotal == 0) {
                continue;
            }
            int count = 0;
            for (int j = i + 1; j <= cost.length; j++) {
                if (count == cost.length) {
                    return i;
                }
                if (j == cost.length) {
                    j = 0;
                    if (gasTotal < cost[cost.length - 1]) {
                        break;
                    }
                    gasTotal = gasTotal - cost[cost.length - 1] + gas[j];
                } else {
                    if (gasTotal < cost[j - 1]) {
                        break;
                    }
                    gasTotal = gasTotal - cost[j - 1] + gas[j];
                }

                count++;
            }
            i += count;
        }
        return -1;
    }

    @Test
    public void testP136() {
        print(singleNumber(new int[] {2,2,1}));
        print(singleNumber(new int[] {4,1,2,1,2}));
    }

    public int singleNumber(int[] nums) {
        int ans = nums[0];
        for (int i = 1; i < nums.length; i++) {
            ans ^= nums[i];
        }
        return ans;
    }

    @Test
    public void testP138() {
        Node head = new Node(1);
        Node head2 = new Node(2);
        head.next = head2;
        head.random = head2;
        head2.random = head2;
        print(copyRandomList(head));
    }

    public Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Map<Integer, Node> copiedMap = new HashMap<>();
        Map<Node, Integer> originMap = new HashMap<>();
        Node newHead = new Node(-1);
        Node newTail = newHead;
        Node headTemp = head;
        int i = 0;
        while (head != null) {
            Node newNode = new Node(-1);
            newNode.val = head.val;
            newTail.next = newNode;
            newTail = newNode;
            copiedMap.put(i++, newNode);
            originMap.put(head, i - 1);

            head = head.next;
        }
        Node newHeadTemp = newHead.next;
        while (headTemp != null) {
            if (headTemp.random != null) {
                Node node = copiedMap.get(originMap.get(headTemp.random));
                newHeadTemp.random = node;
            }

            newHeadTemp = newHeadTemp.next;
            headTemp = headTemp.next;
        }
        return newHead.next;
    }

    @Test
    public void testP139() {
        print(wordBreak("leetcode", new ArrayList<>(Arrays.asList("leet", "code"))));
        print(wordBreak("applepenapple", new ArrayList<>(Arrays.asList("apple", "pen"))));
        print(wordBreak("catsandog", new ArrayList<>(Arrays.asList("cats", "dog", "sand", "and", "cat"))));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = i; j > 0; j--) {
                if (set.contains(s.substring(j - 1, i)) && dp[j - 1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[n];
    }

    @Test
    public void testP141() {

    }

    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        while (p2.next != null && p2.next.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testP142() {
        // todo
    }


    @Test
    public void testP143() {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        //node4.next = node5;
        reorderList(head);
        print(head);
    }

    public void reorderList(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode pHead = head;
        int count = 0;
        while (pHead != null) {
            count++;
            pHead = pHead.next;
        }
        pHead = head;
        boolean isOdd = (count & 1) == 0;
        int total = isOdd ? count / 2 : count / 2 + 1;
        count = 1;
        while (pHead != null) {
            if (count > total) {
                stack.add(pHead);
            }
            count++;
            pHead = pHead.next;
        }
        pHead = head;
        ListNode tail = pHead;

        while (!stack.isEmpty()) {
            pHead = pHead.next;
            tail.next = stack.pop();
            tail = tail.next;
            tail.next = pHead;
            tail = tail.next;
        }
        tail.next = null;
    }

    @Test
    public void testP146() {
        LRUCache lRUCache = new LRUCache(2);
        print(lRUCache.get(2));
        lRUCache.put(2, 6);
        print(lRUCache.get(1));
        lRUCache.put(1, 5);
        lRUCache.put(1, 2);
        print(lRUCache.get(1));
        print(lRUCache.get(2));
    }

    @Test
    public void testP147() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        print(insertionSortList(node1));
    }

    public ListNode insertionSortList(ListNode head) {
        ListNode newHead = head;
        head = head.next;
        newHead.next = null;
        while (head != null) {
            ListNode pre = null;
            ListNode newHeadTemp = newHead;
            while (newHeadTemp != null) {
                if (head.val <= newHeadTemp.val) {
                    ListNode node = head;
                    head = head.next;
                    if (pre == null) {
                        node.next = newHead;
                        newHead = node;
                    } else {
                        pre.next = node;
                        node.next = newHeadTemp;
                    }
                    break;
                }
                pre = newHeadTemp;
                newHeadTemp = newHeadTemp.next;
            }
            if (newHeadTemp == null) {
                ListNode node = head;
                head = head.next;
                pre.next = node;

                node.next = null;
            }
        }
        return newHead;
    }

    @Test
    public void testP148() {
        ListNode node1 = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        print(sortList(node1));
    }

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(newHead);
        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = null;
        ListNode tail = null;
        if (l1.val <= l2.val) {
            head = l1;
            l1 = l1.next;
            head.next = null;
            tail = head;
        } else {
            head = l2;
            l2 = l2.next;
            head.next = null;
            tail = head;
        }

        while (l1 != null && l2 != null) {
            int v1 = l1.val;
            int v2 = l2.val;
            if (v1 <= v2) {
                tail.next = l1;
                tail = l1;
                l1 = l1.next;
                //tail.next = null;
            } else {
                tail.next = l2;
                tail = l2;
                l2 = l2.next;
                //tail.next = null;
            }
        }
        tail.next = l1 == null ? l2 : l1;

        return head;
    }

}
