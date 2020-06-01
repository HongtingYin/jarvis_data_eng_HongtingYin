package ca.jrvs.practice.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class QuickSortTest {
    private int[] numbers = {7, 9, 5, 3, 6, 12, 4};
    private int length = numbers.length;
    private int[] sortedNumbers = {3, 4, 5, 6, 7, 9, 12};

    QuickSort sorter = new QuickSort();

    @Test
    public void sort() {
        int[] sorted = sorter.quickSort(numbers, 0,length-1);
        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(sortedNumbers));
        assertEquals(Arrays.toString(sorted), Arrays.toString(sortedNumbers));
    }

}