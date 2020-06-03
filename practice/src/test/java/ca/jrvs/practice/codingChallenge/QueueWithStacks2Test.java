package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueueWithStacks2Test {
    QueueWithStacks2 q = new QueueWithStacks2();

    @Test
    public void push() {
        q.push(0);
        q.push(6);
        q.push(0);
        q.push(3);
        assertEquals(0, q.peek());
        assertEquals(4, q.queueSize());
    }

    @Test
    public void pop() {
        q.push(0);
        q.push(6);
        assertEquals(0, q.pop());
        q.push(0);
        q.push(3);
        assertEquals(6,q.pop());
        assertEquals(2, q.queueSize());
    }

    @Test
    public void peek() {
        q.push(0);
        q.push(6);
        assertEquals(0, q.peek());
        q.push(0);
        q.push(3);
        assertEquals(0,q.peek());
        assertEquals(4, q.queueSize());
    }

    @Test
    public void empty() {
        assertTrue(q.empty());
        q.push(0);
        q.push(6);
        q.push(0);
        q.push(3);
        assertFalse(q.empty());
    }
}