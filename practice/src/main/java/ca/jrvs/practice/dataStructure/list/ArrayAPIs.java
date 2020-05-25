package ca.jrvs.practice.dataStructure.list;

import java.util.Arrays;
import java.util.List;

public class ArrayAPIs {
    public static void main(String[] args) {

        //create an array of integers
        int[] intArray = new int[10];
        intArray[0] = 0;
        intArray[1] = 10;
        intArray[2] = 20;

        //shortcut syntax to create an array and initialize it
        int[] inlineArray = {
                0, 10, 20
        };

        //2-d array
        String[][] names = {
                {"Mr.", "Mrs.", "Miss."},
                {"Smith", "Jones"}
        };

        //copy array
        char[] copyFrom = {'d', 'e', 'c', 'a', 'f', 'f', 'e', 'i', 'n', 'a', 't', 'e', 'd'};
        char[] copyTo = new char[7];
        System.arraycopy(copyFrom, 0, copyTo, 0, 7);
        System.out.println(new String(copyFrom));

        //convert an array to a list
        List<String> fruits = Arrays.asList("apple", "orange");
        //List<String> fruits = Arrays.asList(new String[]{"apple", "orange"});
        System.out.println(fruits);

        //copy array
        String[] fruitArray = new String[]{"mango", "banana"};
        String[] anotherFruitArray = Arrays.copyOfRange(fruitArray, 0, 1);
        //System.out.printf(anotherFruitArray.toString());
        System.out.println(Arrays.toString(anotherFruitArray));

        //sort
        Arrays.sort(fruitArray);
        System.out.println(Arrays.toString(fruitArray));

        //binary search
        int exact = Arrays.binarySearch(fruitArray, "banana");
        System.out.println(exact);
    }
}
