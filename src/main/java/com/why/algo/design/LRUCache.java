package com.why.algo.design;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private Map<Integer, Node> map = new HashMap<>();
    private Node head;
    private Node tail;
    private int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        Node existsNode = map.get(key);
        if (existsNode != null) {
            moveToFirst(existsNode);
            return existsNode.value;
        } else {
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if (map.size() >= capacity && !map.containsKey(key)) {
            removeOldest();
        }

        if (map.isEmpty()) {
            Node newNode = new Node(null, null, key, value);
            head = tail = newNode;
            map.put(key, newNode);
        } else {
            Node existsNode = null;
            if ((existsNode = map.get(key)) != null) {
                existsNode.setValue(value);
                moveToFirst(existsNode);
            } else {
                Node newNode = new Node(null, head, key, value);
                head.prev = newNode;
                head = newNode;
                map.put(key, newNode);
            }
        }
    }

    private void removeOldest() {
        int key = tail.key;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        map.remove(key);
    }

    // 1-2-3-4-5
    // 1-2
    // 1
    
    private void moveToFirst(Node existsNode) {
        if (existsNode == head) {
            return;
        }
        if (existsNode.next == null) {
            tail = existsNode.prev; // 更新tail

            existsNode.prev.next = null;
            existsNode.next = head;
            head.prev = existsNode;
            existsNode.prev = null;
        } else {
            existsNode.prev.next = existsNode.next;
            existsNode.next.prev = existsNode.prev;
            existsNode.next = head;
            head.prev = existsNode;
            existsNode.prev = null;
        }
        head = existsNode;
    }

    private static class Node {
        private Node prev;
        private Node next;
        private int value;
        private int key;
        public Node(Node prev, Node next, int key, int value) {
            this.prev = prev;
            this.next = next;
            this.key = key;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }
}