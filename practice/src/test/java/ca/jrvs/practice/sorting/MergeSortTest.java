package ca.jrvs.practice.sorting;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class MergeSortTest {

    private int[] numbers = {6,5,3,1,8,7,2,4};
    private int l = numbers.length;
    private int[] sortedNumbers = {1,2,3,4,5,6,7,8};

    MergeSort sorter = new MergeSort();

    @Test
    public void sort() {
        int[] sorted = sorter.mergeSort(numbers, l);
        System.out.println(Arrays.toString(sorted));
        System.out.println(Arrays.toString(sortedNumbers));
        assertEquals(Arrays.toString(sorted), Arrays.toString(sortedNumbers));
    }
}