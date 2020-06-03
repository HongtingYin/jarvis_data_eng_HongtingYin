package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=349aefce8720493d9209b0cfd78b1b32
 */
public class QueueWithStacks2 {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * Big-O: O(1)
     * push the item onto queue
     */
    public void push(int e) {
        stack1.push(e);
    }

    /**
     * Big-O: O(n) where n is the size of queue
     * a while loop to move the elements in stack1 to stack2 which is O(n)
     * another while loop to move them back after popping out the most front element
     * so the time complexity is O(n)
     * @return remove and return the element in front of the queue
     */
    public int pop() {
        int top = -1;
        while(!stack1.isEmpty()){
            top = stack1.pop();
            if (!stack1.isEmpty()){
                stack2.push(top);
            }
        }
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return top;
    }

    /**
     * Big-O: O(n) where n is the size of queue
     * a while loop to move the elements in stack1 to stack2 which is O(n)
     * another while loop to move them back after popping out the most front element
     * so the time complexity is O(n)
     * @return  return the element in front of the queue
     */
    public int peek() {
        int top = -1;
        while(!stack1.isEmpty()){
            top = stack1.pop();
            stack2.push(top);
        }
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return top;
    }

    /**
     * Big-O: O(1)
     *
     * @return return true if queue is empty, false otherwise
     */
    public boolean empty() {
        return stack1.isEmpty();
    }

    /**
     * Big-O: O(1)
     *
     * @return return the size of queue
     */
    public int queueSize() {
        return stack1.size();

    }
}
