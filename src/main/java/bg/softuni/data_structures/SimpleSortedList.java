package bg.softuni.data_structures;

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
        if (element == null) {
            throw new IllegalArgumentException();
        }

        if (size == innerCollection.length) {
            resize();
        }

        innerCollection[size++] = element;
        Arrays.sort(innerCollection, 0, size, comparator);
    }

    @Override
    public void addAll(Collection<E> elements) {

        if (elements == null) {
            throw new IllegalArgumentException();
        }

        if (size + elements.size() >= innerCollection.length) {
            multiResize(elements);
        }

        for (E element : elements) {
            if (element == null) {
                throw new IllegalArgumentException();
            }
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
        if (joiner == null) {
            throw new IllegalArgumentException();
        }

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

    @Override
    public boolean remove(E element) {
        if (element == null) {
            throw new IllegalArgumentException();
        }

        boolean hasBeenRemoved = false;
        int indexOfRemovedElement = 0;

        for (int i = 0; i < size; i++) {
            if (innerCollection[i].compareTo(element) == 0) {
                hasBeenRemoved = true;
                indexOfRemovedElement = i;
                innerCollection[i] = null;
                break;
            }
        }

        if (hasBeenRemoved) {
            for (int i = indexOfRemovedElement; i < innerCollection.length - 1; i++) {
                innerCollection[i] = innerCollection[i + 1];
            }
            innerCollection[--size] = null;
        }

        return hasBeenRemoved;
    }

    @Override
    public int capacity() {
        return this.innerCollection.length;
    }

}
