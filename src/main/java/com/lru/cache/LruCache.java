package com.lru.cache;


import java.util.HashMap;
import java.util.Map;

public class LruCache {

    int maxSize;
    Map<Integer, Node> cacheObject;
    Node head;
    Node tail;

    public LruCache(int maxSize) {
        this.maxSize = maxSize;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        cacheObject = new HashMap<>();
    }

    int get(int key) {
        if (cacheObject.containsKey(key)) {
            Node node = cacheObject.get(key);
            return node.val;
        }
        return -1;
    }

    void put(int key, int value) {
        Node node = new Node(key, value);
        if (cacheObject.containsKey(key)) {
            Node oldNode = cacheObject.get(key);
            remove(oldNode);
            addToFront(node);
            cacheObject.put(key, node);
        } else {
            if (cacheObject.size() >= maxSize) {
                Node nodeToEvict = evictLastRecently();
                cacheObject.remove(nodeToEvict.key);
            }
            addToFront(node);
            cacheObject.put(key, node);
        }
    }

    private void addToFront(Node node) {
        node.prev = tail.prev;
        node.next = tail;
        tail.prev.next = node;
        tail.prev = node;
    }

    private void remove(Node node) {
        node.detachNode();
    }

    private Node evictLastRecently() {
        Node nodeToEvict = head.next;
        if (nodeToEvict != null && nodeToEvict != tail) {
            remove(nodeToEvict);
        }
        return nodeToEvict;
    }

}
