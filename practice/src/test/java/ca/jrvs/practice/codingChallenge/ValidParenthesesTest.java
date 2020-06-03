package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidParenthesesTest {

    ValidParentheses valid = new ValidParentheses();

    @Test
    public void isValid() {
        String str1 = "{{}}()[()]";
        String str2 = "(][)";
        String str3 = ")";
        assertTrue(valid.isValid(str1));
        assertFalse(valid.isValid(str2));
        assertFalse(valid.isValid(str3));
    }
}