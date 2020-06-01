package ca.jrvs.practice.dataStructure.list;

import java.util.Comparator;

public class Employee {
    private int id;
    private String name;
    private int age;
    private long salary;

    public Employee() {
    }

    public Employee(int id, String name, int age, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    /*Comparator for sorting the list by Employee Name*/
    public static Comparator<Employee> enpComparator = new Comparator<Employee>() {
        @Override
        public int compare(Employee o1, Employee o2) {
            String empName1 = o1.getName().toUpperCase();
            String empName2 = o2.getName().toUpperCase();
            //ascending order
            return empName1.compareTo(empName2);
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }
}
