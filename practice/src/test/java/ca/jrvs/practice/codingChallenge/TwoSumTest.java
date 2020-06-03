package ca.jrvs.practice.codingChallenge;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class TwoSumTest {
    TwoSum twoSum = new TwoSum();
    int[] arr;
    int target;
    int[] arrSorted;

    @Before
    public void twoSumBegore() {
        arr = new int[]{4,8,25,0,1,13,2,6};
        arrSorted = new int[]{0,1,2,4,6,8,13,25};
        target = 27;
    }
    @Test
    public void twoSumForLoop() {
        assertArrayEquals(new int[]{2,6}, twoSum.twoSumForLoop(arr, target));
    }

    @Test
    public void twoSumSort() {
        //assertArrayEquals(new int[]{2,7}, twoSum.twoSumSort(arr, target));
        assertArrayEquals(new int[]{2,25}, twoSum.twoSumSort(arr, target));
    }

    @Test
    public void twoSumHashMap() {
        assertArrayEquals(new int[]{2,6}, twoSum.twoSumHashMap(arr, target));
    }
}