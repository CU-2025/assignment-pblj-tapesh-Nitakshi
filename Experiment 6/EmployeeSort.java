//Easy
import java.util.*;

class Employee {
    String name;
    int age;
    double salary;

    Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String toString() {
        return name + " - Age: " + age + ", Salary: " + salary;
    }
}

public class EmployeeSort {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Ravi", 25, 45000));
        employees.add(new Employee("Sneha", 30, 70000));
        employees.add(new Employee("Amit", 22, 30000));
        employees.add(new Employee("Neha", 28, 55000));

        // Sort by name using lambda
        employees.sort((e1, e2) -> e1.name.compareTo(e2.name));

        System.out.println("Sorted Employees by Name:");
        employees.forEach(System.out::println);
    }
}

//Medium
import java.util.*;
import java.util.stream.*;

class Student {
    String name;
    double marks;

    Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return name + " - Marks: " + marks;
    }
}

public class StudentFilter {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
            new Student("Anil", 82),
            new Student("Pooja", 67),
            new Student("Rahul", 91),
            new Student("Meena", 74),
            new Student("Sonia", 88)
        );

        System.out.println("Students scoring above 75% (sorted by marks):");
        students.stream()
                .filter(s -> s.marks > 75)
                .sorted(Comparator.comparingDouble(s -> s.marks))
                .forEach(System.out::println);
    }
}
//Hard
import java.util.*;
import java.util.stream.*;
import java.util.function.*;
import java.text.DecimalFormat;

class Product {
    String name;
    String category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String toString() {
        return name + " - " + category + " - ₹" + price;
    }
}

public class ProductProcessor {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("iPhone", "Electronics", 95000),
            new Product("Samsung TV", "Electronics", 60000),
            new Product("Nike Shoes", "Fashion", 5000),
            new Product("Levi’s Jeans", "Fashion", 3000),
            new Product("Toshiba Laptop", "Electronics", 75000),
            new Product("Gucci Watch", "Fashion", 12000),
            new Product("Washing Machine", "Appliances", 30000),
            new Product("Refrigerator", "Appliances", 40000)
        );

        // Grouping by category
        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(p -> p.category));

        System.out.println("Grouped Products by Category:");
        groupedByCategory.forEach((category, prods) -> {
            System.out.println("\n" + category + ":");
            prods.forEach(System.out::println);
        });

        // Most expensive product in each category
        System.out.println("\nMost Expensive Product in Each Category:");
        groupedByCategory.forEach((category, prods) -> {
            Product max = prods.stream().max(Comparator.comparingDouble(p -> p.price)).get();
            System.out.println(category + " -> " + max);
        });

        // Average price of all products
        double averagePrice = products.stream()
            .mapToDouble(p -> p.price)
            .average()
            .orElse(0.0);

        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("\nAverage Price of All Products: ₹" + df.format(averagePrice));
    }
}
