package ca.jrvs.practice.dataStructure.stackQueue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class LinkedJListDequeTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void add() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null can not be inserted!");

        JDeque<String> deque = new LinkedJListDeque<>();
        deque.add("black");
        deque.add("blue");
        deque.add("red");
        assertEquals(deque.pop(), "black");
        deque.add(null);
    }

    @Test
    public void remove() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("It is empty!");

        JDeque<String> deque = new LinkedJListDeque<>();
        deque.add("black");
        deque.add("blue");
        assertEquals(deque.remove(), "black");
        deque.remove();
        deque.remove();
    }

    @Test
    public void pop() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("No such element!");

        JDeque<String> deque = new LinkedJListDeque<>();
        deque.pop();
        deque.add("black");
        deque.add("blue");
        deque.add("red");
        assertEquals(deque.pop(), "red");
    }

    @Test
    public void push() {
        exception.expect(NoSuchElementException.class);
        exception.expectMessage("null can not be pushed!");

        JDeque<String> deque = new LinkedJListDeque<>();
        deque.push("black");
        deque.push("blue");
        deque.push("red");
        assertEquals(deque.pop(), "red");
        deque.push(null);
    }

    @Test
    public void peek() {
        JDeque<String> deque = new LinkedJListDeque<>();
        assertEquals(deque.peek(), null);
        deque.add("black");
        deque.add("blue");
        deque.add("red");
        assertEquals(deque.peek(), "black");
    }
}