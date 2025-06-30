package org.example;

public class ArrayList<T> {

    private Object[] array;
    private int size;

    public ArrayList(int capacity) {
        size = 0;
        array = new Object[capacity];
    }

    public void add(T data) {
        if(array.length == 50) {
            throw new IllegalArgumentException("array is full");
        }

        array[++size] = data;
    }

    public T get(int index) {
        if(index < 1 || index > array.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        return (T) array[index];
    }

    public int length() {
        return size;
    }

}
