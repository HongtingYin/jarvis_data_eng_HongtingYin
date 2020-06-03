package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StackWithOneQueueTest {
    StackWithOneQueue stack = new StackWithOneQueue();

    @Test
    public void push() {
        stack.push(6);
        assertEquals(6, stack.top());
        stack.push(2);
        assertEquals(2,stack.top());
    }

    @Test
    public void pop() {
        stack.push(0);
        stack.push(6);
        stack.push(0);
        stack.push(2);
        assertEquals(2, stack.top());
        stack.pop();
        assertEquals(0, stack.top());
    }

    @Test
    public void top() {
        stack.push(0);
        stack.push(6);
        stack.push(0);
        stack.push(2);
        assertEquals(2, stack.top());
    }

    @Test
    public void empty() {
        assertTrue(stack.empty());
        stack.push(0);
        stack.push(6);
        stack.push(0);
        stack.push(2);
        assertFalse(stack.empty());
    }
}