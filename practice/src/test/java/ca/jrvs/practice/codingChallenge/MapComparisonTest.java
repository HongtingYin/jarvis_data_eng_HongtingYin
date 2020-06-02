package ca.jrvs.practice.codingChallenge;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MapComparisonTest {

    @Test
    public void compareMapsAPITest() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        m1.put("sun", 7);
        m1.put("rain", 6);
        m1.put("wind", 2);
        m1.put("snow", 3);
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        m2.put("rain", 6);
        m2.put("wind", 2);
        m2.put("snow", 3);
        Map<String, Integer> m3 = new HashMap<String, Integer>();
        m3.put("rain", 6);
        m3.put("wind", 2);
        m3.put("snow", 3);

        assertFalse(MapComparison.compareMapsAPI(m1, m2));
        assertTrue(MapComparison.compareMapsAPI(m2, m3));
    }

    @Test
    public void compareMapsHashJMapTest() {
        Map<String, Integer> m1 = new HashMap<String, Integer>();
        m1.put("sun", 7);
        m1.put("rain", 6);
        m1.put("wind", 2);
        m1.put("snow", 3);
        Map<String, Integer> m2 = new HashMap<String, Integer>();
        m2.put("rain", 6);
        m2.put("wind", 2);
        m2.put("snow", 3);
        Map<String, Integer> m3 = new HashMap<String, Integer>();
        m3.put("rain", 6);
        m3.put("wind", 2);
        m3.put("snow", 3);

        assertFalse(MapComparison.compareMapsHashJMap(m1, m2));
        assertTrue(MapComparison.compareMapsHashJMap(m2, m3));
    }
}