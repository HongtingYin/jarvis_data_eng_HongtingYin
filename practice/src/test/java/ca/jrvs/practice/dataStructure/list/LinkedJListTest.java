package ca.jrvs.practice.dataStructure.list;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Iterator;

import static org.junit.Assert.*;

public class LinkedJListTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void add() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null is not illegal!");

        JList<String> list = new LinkedJList<>();
        list.add("add");
        assertEquals(list.get(0), "add");
        list.add(null);
    }

    @Test
    public void toArray() {
        JList<String> list = new LinkedJList<>();
        list.add("toArray");
        String[] strArray = new String[]{"toArray"};
        assertArrayEquals(list.toArray(), strArray);
    }

    @Test
    public void size() {
        JList<String> list = new LinkedJList<>();
        list.add("size");
        assertEquals(list.size(), 1);
    }

    @Test
    public void isEmpty() {
        JList<String> list = new LinkedJList<>();
        assertTrue(list.isEmpty());
    }

    @Test
    public void indexOf() {
        JList<String> list = new LinkedJList<>();
        list.add("sun");
        list.add("rain");
        list.add("wind");
        list.add("snow");
        assertEquals(list.indexOf("sun"), 0);
    }

    @Test
    public void contains() {
        exception.expect(NullPointerException.class);
        exception.expectMessage("null is not illegal!");

        JList<String> list = new LinkedJList<>();
        list.add("sun");
        list.add("rain");
        list.add("wind");
        list.add("snow");
        assertTrue(list.contains("sun"));
        list.contains(null);
    }

    @Test
    public void get() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("The index is out of range!");

        JList<String> list = new LinkedJList<>();
        list.add("sun");
        list.add("rain");
        list.add("wind");
        list.add("snow");
        assertEquals(list.get(0), "sun");
        list.get(9);

    }

    @Test
    public void remove() {
        exception.expect(IndexOutOfBoundsException.class);
        exception.expectMessage("The index is out of range!");

        JList<String> list = new LinkedJList<>();
        list.add("sun");
        list.add("rain");
        list.add("wind");
        list.add("snow");
        assertEquals(list.remove(2), "wind");
        assertEquals(list.size(), 3);
        list.remove(9);
    }

    @Test
    public void clear() {
        JList<String> list = new LinkedJList<>();
        list.add("sun");
        list.add("rain");
        list.add("wind");
        list.add("snow");
        list.clear();
        assertEquals(list.size(), 0);
    }
}