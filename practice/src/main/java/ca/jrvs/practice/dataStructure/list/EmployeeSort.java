package ca.jrvs.practice.dataStructure.list;

import java.util.ArrayList;
import java.util.Collections;

public class EmployeeSort{
    public static void main(String args[]){
        ArrayList<Employee> arraylist = new ArrayList<>();
        arraylist.add(new Employee(473, "Joe", 28, 4000));
        arraylist.add(new Employee(101, "Zues", 26, 4500));
        arraylist.add(new Employee(505, "Abey", 24, 5000));
        arraylist.add(new Employee(809, "Vignesh", 32, 5500));

        /*Sorting based on Student Name*/
        System.out.println("Student Name Sorting:");
        Collections.sort(arraylist, Employee.enpComparator);

        for(Employee e: arraylist){
            System.out.println(e.getName());
        }
    }

}
