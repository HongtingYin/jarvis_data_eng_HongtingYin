package ca.jrvs.practice.codingChallenge;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=b750fcc061064bbfa8cc4c2286d31aa9
 */
public class StackWithTwoQueues {
    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private int top;

    public void StackWithTwoQueues() {}

    /**
     * Big-O: O(1)
     * queue is implemented as linked list and add operation has O(1) time complexity
     *
     * @param e push an element e into q1
     */
    public void push(int e) {
        q1.add(e);
        top = e;
    }

    /**
     * Big-O: O(n)
     * The algorithm dequeues n elements from q1 and enqueue n-1 elements to q2,
     * where n is the stack size. This gives 2n-1 operations
     */
    public void pop() {
        while (q1.size() > 1) {
            top = q1.remove();
            q2.add(top);
        }
        q1.remove();
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 =temp;
    }

    /**
     * Big-O: O(1)
     * @return true if stack is empty, false otherwise
     */
    public boolean empty() {
        return q1.isEmpty();

    }

    /**
     * Big-O: O(1)
     * @return
     */
    public int top() {
        return top;
    }
}
