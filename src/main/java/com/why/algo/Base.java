package com.why.algo;

import com.google.gson.Gson;

/**
 * @Author Hanyu.Wang
 * @Date 2024/1/9 10:43
 * @Description
 * @Version 1.0
 **/
public class Base {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    protected void print(Object o) {
        if (o instanceof ListNode) {
            ListNode head = (ListNode) o;
            StringBuilder sb = new StringBuilder();
            while (head != null) {
                sb.append(head.val + "->");
                head = head.next;
            }
            System.out.println(sb.toString());
        } else {
            Gson gson = new Gson();
            System.out.println(gson.toJson(o));
        }
    }

    public class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
