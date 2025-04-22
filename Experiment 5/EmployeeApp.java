import java.util.*;

public class AutoBoxingExample {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter numbers (type 'done' to finish):");
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equalsIgnoreCase("done")) break;

            try {
                // Autoboxing from int to Integer
                numbers.add(Integer.parseInt(input));
            } catch (NumberFormatException e) {
                System.out.println("Invalid number, try again.");
            }
        }

        int sum = 0;
        for (Integer num : numbers) {
            // Unboxing from Integer to int
            sum += num;
        }

        System.out.println("Sum: " + sum);
    }
}

//Medium
import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String name;
    private double gpa;

    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    public void display() {
        System.out.println("Student ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("GPA: " + gpa);
    }
}
import java.io.*;

public class StudentMain {
    public static void main(String[] args) {
        String filename = "student.ser";

        // Serialize
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            Student s = new Student(101, "Alice", 9.1);
            oos.writeObject(s);
            System.out.println("Student serialized successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("Error during serialization: " + e);
        }

        // Deserialize
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Student s2 = (Student) ois.readObject();
            System.out.println("\nDeserialized Student:");
            s2.display();
        } catch (FileNotFoundException e) {
            System.out.println("File not found during deserialization.");
        } catch (IOException e) {
            System.out.println("IO Exception: " + e);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found.");
        }
    }
}

//Hard
import java.io.Serializable;

public class Employee implements Serializable {
    private int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Designation: " + designation + ", Salary: " + salary);
    }
}
import java.io.*;
import java.util.*;

public class EmployeeApp {
    static final String filename = "employees.ser";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employeeList = new ArrayList<>();

        // Load existing data
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                employeeList = (List<Employee>) ois.readObject();
            } catch (Exception e) {
                System.out.println("Error loading existing data.");
            }
        }

        int choice;
        do {
            System.out.println("\nMenu:\n1. Add an Employee\n2. Display All\n3. Exit");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Employee ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();

                    Employee emp = new Employee(id, name, designation, salary);
                    employeeList.add(emp);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                        oos.writeObject(employeeList);
                        System.out.println("Employee added successfully.");
                    } catch (IOException e) {
                        System.out.println("Error saving employee data.");
                    }
                    break;

                case 2:
                    if (employeeList.isEmpty()) {
                        System.out.println("No employee records found.");
                    } else {
                        System.out.println("Employee List:");
                        for (Employee e : employeeList) {
                            e.display();
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid option.");
            }
        } while (choice != 3);
    }
}
