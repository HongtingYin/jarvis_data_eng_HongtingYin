package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidPalindromeTest {
    ValidPalindrome valid = new ValidPalindrome();

    @Test
    public void isPalindromeWithTwoPointer() {
        assertTrue(valid.isPalindromeWithTwoPointer("A man, a plan, a canal: Panama"));
        assertFalse(valid.isPalindromeWithTwoPointer("race a car"));
    }

    @Test
    public void isPalindromeWithTwoRecursion() {
        assertTrue(valid.isPalindromeWithRecursion("A man, a plan, a canal: Panama"));
        assertFalse(valid.isPalindromeWithRecursion("race a car"));
    }
}