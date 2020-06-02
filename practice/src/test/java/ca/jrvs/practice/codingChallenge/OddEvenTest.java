package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class OddEvenTest {

    @Test
    public void isOddEvenMod() {
        OddEven oddEven = new OddEven();
        assertEquals("odd", oddEven.isOddEvenMod(-1));
        assertEquals("even", oddEven.isOddEvenMod(-20));
        assertEquals("even", oddEven.isOddEvenMod(6));
        assertEquals("even", oddEven.isOddEvenMod(0));
        assertEquals("odd", oddEven.isOddEvenMod(1));
    }

    @Test
    public void isOddEvenBIt() {
        OddEven oddEven = new OddEven();
        assertEquals("odd", oddEven.isOddEvenBit(-1));
        assertEquals("even", oddEven.isOddEvenBit(-20));
        assertEquals("even", oddEven.isOddEvenBit(6));
        assertEquals("even", oddEven.isOddEvenBit(0));
        assertEquals("odd", oddEven.isOddEvenBit(1));
    }
}