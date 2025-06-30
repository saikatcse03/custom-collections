package org.example;

import java.util.HashMap;

import java.util.Map;

public class LRUCache {

    private Map<String, Node> cache;
    private Node head;
    private Node tail;
    private final int capacity;

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(100);
        for(int index = 1; index <= 100 ; index++) {
            lruCache.put("key"+index, "value"+index);
            System.out.println(lruCache.get("key"+index));
            System.out.println(lruCache.get("key"+(index-1)));
        }
        lruCache.remove("key1");
        System.out.println(lruCache.get("key1"));
        lruCache.invalidate();
        System.out.println("=====done======");
    }

    public LRUCache(int capacity) {
        if(capacity < 1) {
            throw new IllegalArgumentException("Cache size must be greater than 0");
        }

        cache = new HashMap<>();
        this.capacity = capacity;
    }

    public String get(String key) {
        if(!cache.containsKey(key)) return null;

        remove(cache.get(key));
        insertToHead(cache.get(key));

        return cache.get(key).getData();
    }

    public void put(String key, String value) {
        if(cache.containsKey(key)) return;

        if(cache.size() == capacity) {
            if(tail != null) {
                Node lru = tail;
                tail = lru.getPrevious();
                if (tail != null) tail.setNext(head);

                remove(lru);
                cache.remove(lru.getKey());
            }
        }

        Node node = new Node(key, value);
        cache.put(key, node);
        insertToHead(node);
    }

    public void remove(String key) {
        Node node = cache.get(key);
        remove(node);
        cache.remove(key);
    }

    public void invalidate() {
        cache = new HashMap<>();;
        head = null;
        tail = null;
    }

    private void remove(Node node) {
        if(node == null) return;

        if(node.getPrevious() != null) {
            node.getPrevious().setNext(node.getNext());
        }

        if(node.getNext() != null) {
            node.getNext().setPrevious(node.getPrevious());
        }
    }

    private void insertToHead(Node node) {
        if(head == null) {
            head = node;
        } else {
            node.setNext(head);
            head.setPrevious(node);
        }

        if(tail == null) {
            tail = node;
        } else {
            node.setPrevious(tail);
            tail.setNext(node);
        }

        head = node;
    }
}

class Node {
    private String key;
    private final String data;
    private Node previous;
    private Node next;

    Node(String key, String data) {
        this.key = key;
        this.data = data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }
}
