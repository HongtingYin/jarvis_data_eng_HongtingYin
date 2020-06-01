package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListAPIs {

    public static void main(String[] args) {
        //List VS ArrayList
        List<String> animals = new ArrayList<>();

        animals.add("Lion");
        animals.add("Tiger");
        animals.add(2, "Cat");

        //size
        int s = animals.size();
        System.out.println(s);

        //get
        String firstElement = animals.get(0);
        System.out.println(firstElement);

        //search O(n)
        Boolean hasLion = animals.contains("Lion");
        System.out.println(hasLion);

        //index
        int lionIndex = animals.indexOf("Lion");
        System.out.println(lionIndex);

        //remove by object
        boolean isLionRemoved = animals.remove("Lion");
        System.out.println(isLionRemoved);
        //remove by index
        String removedElement = animals.remove(0);
        System.out.println(removedElement);

        //sort
        //pass comparator using lambda
        animals.sort(String::compareToIgnoreCase);

        //convert list to arrary
        System.out.println(Arrays.toString(animals.toArray()));
        System.out.println("print out the animals in list");
        System.out.println(animals);

    }
}
