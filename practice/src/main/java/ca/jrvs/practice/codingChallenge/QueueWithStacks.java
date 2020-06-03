package ca.jrvs.practice.codingChallenge;

import java.util.Stack;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=349aefce8720493d9209b0cfd78b1b32
 */
public class QueueWithStacks {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    /**
     * Big-O: O(n) where n is the size of queue
     * a while loop to move the elements in stack1 to stack2 which is O(n)
     * another while loop to move them back after pushing the new element(O(n))
     * so the time complexity is O(n)
     *
     * @param e the item to be pushed onto the stack
     */
    public void push(int e) {
        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }
        stack1.push(e);
        while(!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    /**
     * Big-O: O(1)
     * pop the item at the top of stack
     *
     * @return remove and return the element in front of queue
     */
    public int pop() {
        return stack1.pop();
    }

    /**
     * Big-O: O(1)
     * peek the item at the top of stack
     *
     * @return return the element in front of queue
     */
    public int peek() {
        return stack1.peek();
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
