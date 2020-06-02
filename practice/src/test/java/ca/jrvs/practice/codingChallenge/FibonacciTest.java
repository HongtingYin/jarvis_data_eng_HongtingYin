package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class FibonacciTest {

    @Test
    public void fibonacciRecrusionTest() {
        Fibonacci fibonacci = new Fibonacci();

        assertEquals(0, Fibonacci.fibonacciRecursion(0));
        assertEquals(1, fibonacci.fibonacciRecursion(1));
        assertEquals(1, fibonacci.fibonacciRecursion(2));
        assertEquals(55, fibonacci.fibonacciRecursion(10));
    }

    @Test
    public void fibonacciDPTest() {
        Fibonacci fibonacci = new Fibonacci();

        assertEquals(0, fibonacci.fibonacciDP(0));
        assertEquals(1, fibonacci.fibonacciDP(1));
        assertEquals(1, fibonacci.fibonacciDP(2));
        assertEquals(55, fibonacci.fibonacciDP(10));
    }
}