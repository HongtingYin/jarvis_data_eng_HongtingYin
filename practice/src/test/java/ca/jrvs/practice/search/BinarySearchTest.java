package ca.jrvs.practice.search;

import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class BinarySearchTest {

    private BinarySearch sut;

    @Before
    public void setUp() throws Exception {
        sut = new BinarySearch();
    }

    @Test
    public void shouldReturnFalseIfArrayIsEmptyRecursion() {
        assertEquals(Optional.empty(), sut.binarySearchRecursion(new Integer[]{}, 1));
    }

    @Test
    public void shouldReturnFalseIfNotFoundInSortedOddArrayRecursion() {
        Integer[] arr = {0, 2, 4, 6, 8, 10, 12, 14, 16};
        Integer tar = 1;
        assertEquals(Optional.empty(), sut.binarySearchRecursion(arr, tar));
    }
    @Test
    public void shouldReturnTrueIfFoundInMiddleInSortedArrayRecursion() {
        assertEquals(Optional.of(4),
                sut.binarySearchRecursion(new Integer[]{0, 2, 4, 6, 8, 10, 12, 14, 16}, 8));
    }

    @Test
    public void shouldReturnFalseIfArrayIsEmptyIteration() {
        assertEquals(Optional.empty(), sut.binarySearchIteration(new Integer[]{}, 1));
    }

    @Test
    public void shouldReturnFalseIfNotFoundInSortedOddArrayIteration() {
        Integer[] arr = {0, 2, 4, 6, 8, 10, 12, 14, 16};
        Integer tar = 1;
        assertEquals(Optional.empty(), sut.binarySearchRecursion(arr, tar));
    }
    @Test
    public void shouldReturnTrueIfFoundInMiddleInSortedArrayIteration() {
        assertEquals(Optional.of(4),
                sut.binarySearchRecursion(new Integer[]{0, 2, 4, 6, 8, 10, 12, 14, 16}, 8));
    }

}