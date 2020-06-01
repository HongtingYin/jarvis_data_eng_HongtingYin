package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Collections;

public class EmployeeComparableSort {

    public static void main(String args[]){
        ArrayList<EmployeeComparable> arraylist = new ArrayList<EmployeeComparable>();
        arraylist.add(new EmployeeComparable(223, "Chaitanya", 26, 4000));
        arraylist.add(new EmployeeComparable(245, "Rahul", 24, 4500));
        arraylist.add(new EmployeeComparable(209, "Ajeet", 32, 5000));
        arraylist.add(new EmployeeComparable(214, "Ross", 27, 5500));

        Collections.sort(arraylist);

        for(EmployeeComparable str: arraylist){
            System.out.println(str.getId());
        }
    }
}

