package com.why.algo.design;

import com.why.algo.Base;

import java.util.Stack;

class BSTIterator extends Base {
    private Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    
    public int next() {
        TreeNode curr = stack.pop();
        int value = curr.val;
        TreeNode node = curr.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return value;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }
}