package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidAnagramTest {
    ValidAnagram validAnagram = new ValidAnagram();
    String A = "anagram";
    String B = "nagaram";
    String C = "nagaramgsf";

    @Test
    public void isValidAnagramWithSort() {
        assertTrue(validAnagram.isValidAnagramWithSort(A, B));
        assertFalse(validAnagram.isValidAnagramWithSort(A, C));
    }

    @Test
    public void isValidAnagramWithoutSort() {
        assertTrue(validAnagram.isValidAnagramWithoutSort(A, B));
        assertFalse(validAnagram.isValidAnagramWithoutSort(A, C));
    }
}