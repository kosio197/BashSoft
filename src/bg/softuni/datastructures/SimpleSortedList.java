package bg.softuni.datastructures;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

import bg.softuni.contract.SimpleOrderedBag;

public class SimpleSortedList<E extends Comparable<E>> implements SimpleOrderedBag<E> {
    private static final int DEFAULT_SIZE = 16;

    private E[] innerCollection;
    private int size;
    private Comparator<E> comparator;

    public SimpleSortedList(Class<E> type) {
        this(type, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<E> type, Comparator<E> comparator) {
        this(type, comparator, DEFAULT_SIZE);
    }

    public SimpleSortedList(Class<E> type, int capacity) {
        this(type, Comparable::compareTo, capacity);
    }

    public SimpleSortedList(Class<E> type, Comparator<E> comparator, int capacity) {
        initializeInnerCollection(type, capacity);
        this.comparator = comparator;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = new Iterator<E>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public E next() {
                return innerCollection[index++];
            }
        };

        return iterator;
    }

    @Override
    public void add(E element) {
        if (size == innerCollection.length) {
            resize();
        }

        innerCollection[size++] = element;
        Arrays.sort(innerCollection, 0, size, comparator);
    }

    @Override
    public void addAll(Collection<E> elements) {
        if (size + elements.size() >= innerCollection.length) {
            multiResize(elements);
        }

        for (E element : elements) {
            innerCollection[size++] = element;
        }

        Arrays.sort(innerCollection, 0, size, comparator);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String joinWith(String joiner) {
        StringBuilder output = new StringBuilder();

        for (E e : this) {
            output.append(e);
            output.append(joiner);
        }

        output.setLength(output.length() - joiner.length());

        return output.toString();
    }

    @SuppressWarnings("unchecked")
    private void initializeInnerCollection(Class<E> type, int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity cannot be negative!");
        }

        innerCollection = (E[]) Array.newInstance(type, capacity);
    }

    private void resize() {
        E[] newCollection = Arrays.copyOf(innerCollection, innerCollection.length * 2);

        innerCollection = newCollection;
    }

    private void multiResize(Collection<E> elements) {
        int newSize = innerCollection.length * 2;

        while (size + elements.size() >= newSize) {
            newSize *= 2;
        }

        E[] newCollection = Arrays.copyOf(innerCollection, newSize);

        innerCollection = newCollection;
    }

}
