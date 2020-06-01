package ca.jrvs.practice.dataStructure.stackQueue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class LinkedJListDeque<E> implements JDeque<E> {

    /**
     * The size of the ArrayList (the number of elements it contains).
     */
    transient int size = 0;

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    transient Node<E> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    transient Node<E> last;

    /**
     * This is equivalent enqueue operation in Queue ADT
     *
     * Inserts the specified element into the queue represented by this deque
     * (in other words, at the tail of this deque) if it is possible to do so
     * immediately without violating capacity restrictions, returning
     * {@code true} upon success and throwing an
     * {@code IllegalStateException} if no space is currently available.
     *
     * @param e the element to add
     * @return {@code true} (as specified by {@link Collection#add})
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     */
    @Override
    public boolean add(E e) {
        if (e == null) throw new NullPointerException("null can not be inserted!");
        linkLast(e);
        return true;
    }

    /**
     * This is equivalent dequeue operation in Queue ADT
     *
     * Retrieves and removes the head of the queue represented by this deque
     * (in other words, the first element of this deque).
     *
     * @return the head of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    public E remove() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException("It is empty!");
        return unlinkFirst(f);
    }

    /**
     * Pops an element from the stack represented by this deque. In other
     * words, removes and returns the first element of this deque.
     *
     * @return the element at the front of this deque (which is the top
     *         of the stack represented by this deque)
     * @throws NoSuchElementException if this deque is empty
     */
    @Override
    public E pop() {
        final Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException("No such element!");
        return unlinkFirst(f);
    }

    /**
     * Pushes an element onto the stack represented by this deque (in other
     * words, at the head of this deque) if it is possible to do so
     * immediately without violating capacity restrictions
     *
     * @param e the element to push
     * @throws NullPointerException if the specified element is null and this
     *         deque does not permit null elements
     */
    @Override
    public void push(E e) {
        if (e == null)
            throw new NoSuchElementException("null can not be pushed!");
        linkFirst(e);
    }

    /**
     * Retrieves, but does not remove, the head of the queue represented by
     * this deque (in other words, the first element of this deque), or
     * returns {@code null} if this deque is empty.
     *
     * @return the head of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    @Override
    public E peek() {
        final Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * Links e as first element.
     */
    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            last = newNode;
        else
            f.prev = newNode;
        size++;
    }

    /**
     * Link e as the last element
     * @param e
     */
    void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(l, e, null);

        last = newNode;

        if (l == null)
            first = newNode;
        else
            l.next = newNode;

        size++;
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        return element;
    }

    /**
     * Unlinks non-null first node f.
     */
    private E unlinkFirst(Node<E> f) {
        // assert f == first && f != null;
        final E element = f.item;
        final Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            last = null;
        else
            next.prev = null;
        size--;
        return element;
    }

    /**
     *
     * @param <E>
     */
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;
        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Returns the (non-null) Node at the specified element index.
     */
    Node<E> node(int index) {
        // assert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }
}
