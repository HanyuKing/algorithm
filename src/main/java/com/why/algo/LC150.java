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
}
