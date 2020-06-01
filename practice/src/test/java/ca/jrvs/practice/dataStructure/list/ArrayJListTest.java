package ca.jrvs.practice.dataStructure.list;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayJListTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void add() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null is not illegal!");

        JList<String> list = new ArrayJList<>();
        list.add("add");
        assertEquals(list.get(0), "add");
        list.add(null);
    }

    @Test
    public void toArray() {
        JList<String> list = new ArrayJList<>();
        list.add("toArray");
        String[] strArray = new String[]{"toArray"};
        assertArrayEquals(list.toArray(), strArray);
        Assert.assertEquals(
                Arrays.toString(list.toArray()),
                Arrays.toString(strArray)); //this method is not effective for large arrays
    }

    @Test
    public void size() {
        JList<String> list = new ArrayJList<>();
        list.add("size");
        assertEquals(list.size(), 1);
    }

    @Test
    public void isEmpty() {
        JList<String> list = new ArrayJList<>();
        System.out.println(list.size());
        assertTrue(list.isEmpty());

    }

    @Test
    public void indexOf() {
        JList<String> list = new ArrayJList<>();
        list.add("indexOne");
        list.add("indexTwo");
        list.add("indexThree");
        list.add("indexFour");
        list.add("indexFive");
        assertEquals(list.indexOf("indexFour"), 3);
    }

    @Test
    public void contains() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null is not illegal!");

        JList<String> list = new ArrayJList<>();
        list.add("indexOne");
        list.add("indexTwo");
        list.add("indexThree");
        list.add("indexFour");
        list.add("indexFive");
        assertTrue(list.contains("indexFour"));
        list.contains(null);
    }

    @Test
    public void get() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("The index is out of range!");

        JList<String> list = new ArrayJList<>();
        list.add("indexOne");
        list.add("indexTwo");
        list.add("indexThree");
        list.add("indexFour");
        list.add("indexFive");
        assertEquals(list.get(3), "indexFour");
        list.get(9);
    }

    @Test
    public void remove() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("The index is out of range!");

        JList<String> list = new ArrayJList<>();
        list.add("indexOne");
        list.add("indexTwo");
        list.add("indexThree");
        list.add("indexFour");
        list.add("indexFive");
        assertEquals(list.remove(3), "indexFour");
        list.remove(9);
    }

    @Test
    public void clear() {
        JList<String> list = new ArrayJList<>();
        list.add("indexOne");
        list.add("indexTwo");
        list.add("indexThree");
        list.add("indexFour");
        list.add("indexFive");
        list.clear();
        assertEquals(list.size(), 0);
    }
}