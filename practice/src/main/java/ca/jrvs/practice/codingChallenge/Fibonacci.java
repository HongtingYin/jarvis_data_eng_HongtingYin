package ca.jrvs.practice.codingChallenge;

/**
 * ticket: https://www.notion.so/027448e6819a46468dadedac2a728d83?v=fe51c52cf1e24c0f84d0f54b3aea453a&p=48f99355918a4dcf962fe3b0ba6c7f9f
 */
public class Fibonacci {

    /**
     * Big-O: O(2^n) exponential
     *
     * @param n the position of the number needs to be found
     * @return the number at n-th position
     */
    public static int fibonacciRecursion(int n) {
        if (n == 0)
            return 0;
        else if (n == 1 || n == 2)
            return 1;
        else
            return fibonacciRecursion(n-2) + fibonacciRecursion(n-1);

    }

    /**
     * Big-O: O(n)
     *
     *
     * @param n the position of the number needs to be found
     * @return the number at n-th position
     */
    public int fibonacciDP(int n) {
        int[] fib = new int[n+1];
        if (n == 0)
            return 0;
        fib[1] = 1;

        for (int i = 2; i < n+1; i++) {
            fib[i] = fib[i-1] + fib[i-2];
        }
        return fib[n];
    }
}
