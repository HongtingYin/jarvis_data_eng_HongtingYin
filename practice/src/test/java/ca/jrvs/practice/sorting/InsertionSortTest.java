package ca.jrvs.practice.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class InsertionSortTest {
    private int[] numbers = {6,5,3,1,8,7,2,4};
    private int[] sortedNumbers = {1,2,3,4,5,6,7,8};

    InsertionSort sorter = new InsertionSort();

    @Test
    public void insertionSortImperative() {
        int[] sorted = sorter.insertionSortImperative(numbers);
        assertEquals(Arrays.toString(sorted), Arrays.toString(sortedNumbers));
    }
}