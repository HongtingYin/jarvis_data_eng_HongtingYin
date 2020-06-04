package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringOnlyDigitsTest {
    StringOnlyDigits stringOnlyDigits = new StringOnlyDigits();
    String str1 = "1234";
    String str2 = "123,000";

    @Test
    public void stringOnlyDigitsASCII() {
        assertTrue(stringOnlyDigits.stringOnlyDigitsASCII(str1));
        assertFalse(stringOnlyDigits.stringOnlyDigitsASCII(str2));
    }

    @Test
    public void stringOnlyDigitsAPI() {
        assertTrue(stringOnlyDigits.stringOnlyDigitsAPI(str1));
        assertFalse(stringOnlyDigits.stringOnlyDigitsAPI(str2));
    }

    @Test
    public void stringOnlyDigitsRegex() {
        assertTrue(stringOnlyDigits.stringOnlyDigitsRegex(str1));
        assertFalse(stringOnlyDigits.stringOnlyDigitsRegex(str2));
    }
}