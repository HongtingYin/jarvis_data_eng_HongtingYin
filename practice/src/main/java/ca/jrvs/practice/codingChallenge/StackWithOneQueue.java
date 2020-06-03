package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=b750fcc061064bbfa8cc4c2286d31aa9
 */
public class StackWithOneQueue {
    private Queue<Integer> q1 = new LinkedList<>();

    public void StackWithOneQueue() {}
    /**
     * Big-O: O(n) where n is the number of elements in stack before inserting
     * the algorithm removes n elements and insert n+1 elements to q1, where n is the stack size.
     * This gives 2n+1 operations. operation add() and remove() in linked list has O(1) complexity
     *
     * @param e
     */
    public void push(int e) {
        q1.add(e);
        int size = q1.size();
        while(size > 1) {
            q1.add(q1.remove());
            size--;
        }
    }

    /**
     * Big-O: O(1)
     * remove the last inserted element
     */
    public void pop() {
        q1.remove();
    }

    /**
     * Big-O: O(1)
     * return the last inserted element
     */
    public int top() {
        return q1.peek();
    }

    /**
     * Big-O: O(1)
     * return true if stack is empty, false otherwise
     */
    public boolean empty() {
        return q1.isEmpty();

    }
}
