package com.why.algo;

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
