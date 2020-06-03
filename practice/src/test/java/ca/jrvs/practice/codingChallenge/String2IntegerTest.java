package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import static org.junit.Assert.*;

public class String2IntegerTest {

    @Test(expected = NumberFormatException.class)
    public void string2IntegerWithBuildInParse() throws Exception {
        String2Integer string2Integer = new String2Integer();
        int intVal = Integer.parseInt("10000000001");
        System.out.println(intVal);

        assertEquals(-1190, string2Integer.string2IntegerWithBuildInParse("-1190"));
        assertEquals(1190, string2Integer.string2IntegerWithBuildInParse("+1190"));
        assertEquals(1200, string2Integer.string2IntegerWithBuildInParse("1200"));
        assertEquals(-1190, string2Integer.string2IntegerWithBuildInParse("-1190 "));
        assertEquals(0, string2Integer.string2IntegerWithBuildInParse("-"));
        assertEquals(0, string2Integer.string2IntegerWithBuildInParse(""));
        assertEquals(0, string2Integer.string2IntegerWithBuildInParse(" "));
        assertEquals(0, string2Integer.string2IntegerWithBuildInParse("+"));
        assertEquals(0, string2Integer.string2IntegerWithBuildInParse("today"));
        assertEquals(2147483647, string2Integer.string2IntegerWithBuildInParse("3492375249"));
        assertEquals(2147483647, string2Integer.string2IntegerWithBuildInParse("21474836487"));
        assertEquals(-2147483648, string2Integer.string2IntegerWithBuildInParse("-2147483648"));
        assertEquals(-2147483648, string2Integer.string2IntegerWithBuildInParse("-4719249874"));
    }

    @Test
    public void string2IntegerWithoutBuildInParse() throws Exception {
        String2Integer string2Integer = new String2Integer();

        assertEquals(-1190, string2Integer.string2IntegerWithoutBuildInParse("-1190"));
        assertEquals(1190, string2Integer.string2IntegerWithoutBuildInParse("+1190"));
        assertEquals(1200, string2Integer.string2IntegerWithoutBuildInParse("1200"));
        assertEquals(-1190, string2Integer.string2IntegerWithoutBuildInParse("-1190 "));
        assertEquals(0, string2Integer.string2IntegerWithoutBuildInParse("-"));
        assertEquals(0, string2Integer.string2IntegerWithoutBuildInParse(""));
        assertEquals(0, string2Integer.string2IntegerWithoutBuildInParse(" "));
        assertEquals(0, string2Integer.string2IntegerWithoutBuildInParse("+"));
        assertEquals(0, string2Integer.string2IntegerWithoutBuildInParse("today"));
        assertEquals(2147483647, string2Integer.string2IntegerWithoutBuildInParse("2147483651947823"));
        assertEquals(2147483647, string2Integer.string2IntegerWithoutBuildInParse("2147483648"));
        assertEquals(-2147483648, string2Integer.string2IntegerWithoutBuildInParse("-2147483648"));
        assertEquals(-2147483648, string2Integer.string2IntegerWithoutBuildInParse("-47192498741093252304782349350278457626593874"));

    }
}