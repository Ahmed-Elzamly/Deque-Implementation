package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T [] items;
    private int size;
    private int first;
    private int last;
    private int length;
    public ArrayDeque() {
        length = 8;
        items = (T []) new Object[length];
        size = 0;
        first = 1;
        last = 0;
    }
    private class ArrayDequeIterator implements Iterator<T> {
        private int curPos;
        ArrayDequeIterator() {
            curPos = 0;
        }
        @Override
        public boolean hasNext() {
            return curPos < size;
        }
        @Override
        public T next() {
            T returnedItem = items[(first + curPos) % length];
            curPos++;
            return returnedItem;
        }
    }
    public void addFirst(T item) {
        if (size == length) {
            resize(length * 2);
        }
        first = (first - 1 + length) % length;
        this.items[first] = item;
        size++;
    }
    public void addLast(T item) {
        if (size == length) {
            resize(length * 2);
        }
        last = (last + 1 + length) % length;
        this.items[last] = item;
        size++;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(first + index) % items.length];
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((double) (size - 1) / length < 0.25 && length > 8) {
            resize(length / 2);
        }
        T item = this.items[first];
        first = (first + 1 + length) % length;
        size--;
        return item;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((double) (size - 1) / length < 0.25 && length > 8) {
            resize(length / 2);
        }
        T item = this.items[last];
        last = (last - 1 + length) % length;
        size--;
        return item;
    }
    private void resize(int newSize) {
        T [] a = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            a[i] = get(i);
        }
        first = 0;
        last = size - 1;
        length = newSize;
        items = a;
    }
    public boolean equals(Object o) {
        if (!(o instanceof Deque)) {
            return false;
        }
        if (size != ((Deque<?>) o).size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (get(i) == null || !get(i).equals(((Deque<?>) o).get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}
