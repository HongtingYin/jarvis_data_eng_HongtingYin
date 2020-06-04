package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class RotateStringTest {
    RotateString rotateString = new RotateString();
    String A = "abcde";
    String B = "cdeab";
    String C = "abced";
    String D = "deab";

    @Test
    public void isRotateString() {
        assertTrue(rotateString.isRotateString(A, B));
        assertFalse(rotateString.isRotateString(A, D));
        assertFalse(rotateString.isRotateString(C, B));
    }
}