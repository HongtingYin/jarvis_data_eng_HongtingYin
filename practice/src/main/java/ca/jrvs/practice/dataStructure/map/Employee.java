package ca.jrvs.practice.dataStructure.map;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int age;
    private long salary;

    public Employee() {}

    public Employee(int id, String name, int age, long salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                age == employee.age &&
                salary == employee.salary &&
                name.equals(employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, salary);
    }

    public static void main(String[] args) {
        HashMap<Employee, List<String>> empStrMap = new HashMap<>();
        //Amy
        Employee amy = new Employee(1, "amy", 25, 45000);
        List<String> amyPrevCompanies = Arrays.asList("TD", "RBC", "CIBC");
        empStrMap.put(amy, amyPrevCompanies);

        //Bob
        Employee bob = new Employee(2, "bob", 25, 40000);
        List<String> bobPrevCompanies = Arrays.asList("A&W", "superstore");
        empStrMap.put(bob, bobPrevCompanies);

        System.out.println("Bob hashcode: " + bob.hashCode());
        System.out.println("Bob value: " + empStrMap.get(bob).toString());
    }
}
