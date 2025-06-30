package org.example;

import java.util.LinkedList;

public class HashSet<V> {

    private LinkedList<V>[] buckets;
    private static final int MAX_CAPACITY = 1000;

    public HashSet() {
        buckets = new LinkedList[MAX_CAPACITY];
    }

    public int getHashedIndex(V value) {
        return value.hashCode()%MAX_CAPACITY;
    }

    public void add(V value) {
        int index = getHashedIndex(value);

        if(buckets[index] == null) {
            buckets[index] = new LinkedList<>();
        }

        if(!buckets[index].contains(value)) {
            buckets[index].add(value);
        }
    }

    public void remove(V value) {
        int index = getHashedIndex(value);
        if(buckets[index] != null) {
            buckets[index].remove(value);
        }
    }

    public boolean contains(V value) {
        int index = getHashedIndex(value);
        return buckets[index] != null &&
                buckets[index].contains(value);
    }

    // For debugging purposes
    public void printSet() {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            if (buckets[i] != null && !buckets[i].isEmpty()) {
                System.out.println("Bucket " + i + ": " + buckets[i]);
            }
        }
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        System.out.println(set.contains(1)); // true
        System.out.println(set.contains(3)); // false
        set.add(2);
        set.remove(2);
        System.out.println(set.contains(2)); // false
    }
}
