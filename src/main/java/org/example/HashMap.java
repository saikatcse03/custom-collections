package org.example;

import java.util.Arrays;
import java.util.LinkedList;

public class HashMap<K, V> {

    private int capacity = 32;
    private LinkedList<MapEntry<K, V>>[] buckets;

    static class MapEntry<K, V> {
        private K key;
        private V value;

        MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public HashMap() {
        buckets = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public void put(K key, V value) {
        int index = getBucketIndex(key);
        LinkedList<MapEntry<K, V>> bucket = buckets[index];

        for(MapEntry<K, V> mapEntry : bucket) {
            if(mapEntry.key.equals(key) && !mapEntry.value.equals(value)) {
                mapEntry.value = value;
            }
        }

        bucket.add(new MapEntry<>(key, value));
    }

    public V get(K key) {
        int index = getBucketIndex(key);
        LinkedList<MapEntry<K, V>> bucket = buckets[index];

        for(MapEntry<K, V> mapEntry : bucket) {
            if(mapEntry.key.equals(key)) {
                return mapEntry.value;
            }
        }
        return null;
    }

    public boolean containsKey(K key) {
        return key != null && get(key) != null;
    }

    public void remove(K key) {
        int index = getBucketIndex(key);
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
        bucket.removeIf(kvEntry -> kvEntry.key.equals(key));
    }

    @Override
    public String toString() {
        return "HashMap{" +
                "capacity=" + capacity +
                ", buckets=" + Arrays.toString(buckets) +
                '}';
    }

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();

        map.put("hello", "world");
        map.put("hi", "world");
        map.put("My", "world");
        System.out.println(map.get("hello"));
        System.out.println(map.get("hi"));

        map.put("hello", "DE");

        System.out.println(map.get("hello"));
        System.out.println(map);
        System.out.println(map.containsKey("hello"));
        System.out.println(map.containsKey(""));
        System.out.println(map.containsKey(null));
        map.put("null", null);
        System.out.println(map.containsKey("null"));

        map.remove("hello");
        System.out.println(map);

    }
}
