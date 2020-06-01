package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;

public class ArrayJList<E> implements JList<E> {

    /**
     * Default initial capacity
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * The array buffer into which the elements of ArrayList are stored.
     * The capacity of ArrayList is the length of this array buffer.
     */
    transient Object[] elementData;

    E elementData(int index) {
        return (E) elementData[index];
    }

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    private int size;

    /**
     * Constructs an empty List with the specified initial capacity
     * @param initialCapacity
     * @throws IllegalArgumentException
     */
    public ArrayJList(int initialCapacity) {
        if (initialCapacity > 0){
            this.elementData = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("Illegal Capacity" + initialCapacity);

        }
    }

    /**
     * Cosntructs an empty list with an initial capacity of 10
     */
    public ArrayJList() {
        this(DEFAULT_CAPACITY);

    }

    /**
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * Double elementData size if elementData is full.
     */
    @Override
    public boolean add(E e) {
        if (e == null) throw new NullPointerException("null is not illegal!");
        elementData[size++] = e;
        return true;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elementData, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null){
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;

                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) throw new NullPointerException("null is not illegal!");
        return indexOf(o) > 0;
    }

    @Override
    public E get(int index) {
        if (index >= 0 & index < size){
            return elementData(index);
        } else {
            throw new IndexOutOfBoundsException("The index is out of range!");
        }
    }

    @Override
    public E remove(int index) {

        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException("The index is out of range!");
        }

        E oldValue = elementData(index);

        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index+1, elementData, index, numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }
}
