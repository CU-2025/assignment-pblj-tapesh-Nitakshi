//Easy
import java.sql.*;

public class EmployeeFetcher {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String username = "root";
        String password = "your_password";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            System.out.println("EmpID | Name | Salary");
            System.out.println("---------------------");
            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + " | " + rs.getString("Name") + " | " + rs.getDouble("Salary"));
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e);
        }
    }
}
/* CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100),
    Salary DOUBLE
);
*/

//Medium
import java.sql.*;
import java.util.Scanner;

public class ProductManager {
    static String url = "jdbc:mysql://localhost:3306/your_database_name";
    static String username = "root";
    static String password = "your_password";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n1. Add Product\n2. View All\n3. Update Product\n4. Delete Product\n5. Exit");
                System.out.print("Choose an option: ");
                int choice = sc.nextInt();
                sc.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Price: ");
                        double price = sc.nextDouble();
                        System.out.print("Quantity: ");
                        int qty = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)")) {
                            ps.setString(1, name);
                            ps.setDouble(2, price);
                            ps.setInt(3, qty);
                            ps.executeUpdate();
                            System.out.println("Product added!");
                        }
                        break;

                    case 2:
                        try (Statement st = conn.createStatement()) {
                            ResultSet rs = st.executeQuery("SELECT * FROM Product");
                            System.out.println("\nID | Name | Price | Qty");
                            while (rs.next()) {
                                System.out.println(rs.getInt("ProductID") + " | " + rs.getString("ProductName") +
                                        " | " + rs.getDouble("Price") + " | " + rs.getInt("Quantity"));
                            }
                        }
                        break;

                    case 3:
                        System.out.print("Enter Product ID to Update: ");
                        int idToUpdate = sc.nextInt();
                        sc.nextLine();
                        System.out.print("New Name: ");
                        String newName = sc.nextLine();
                        System.out.print("New Price: ");
                        double newPrice = sc.nextDouble();
                        System.out.print("New Quantity: ");
                        int newQty = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement("UPDATE Product SET ProductName=?, Price=?, Quantity=? WHERE ProductID=?")) {
                            ps.setString(1, newName);
                            ps.setDouble(2, newPrice);
                            ps.setInt(3, newQty);
                            ps.setInt(4, idToUpdate);
                            ps.executeUpdate();
                            System.out.println("Product updated!");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Product ID to Delete: ");
                        int idToDelete = sc.nextInt();

                        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM Product WHERE ProductID=?")) {
                            ps.setInt(1, idToDelete);
                            ps.executeUpdate();
                            System.out.println("Product deleted!");
                        }
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e);
        }
    }
}
/*
CREATE TABLE Product (
    ProductID INT PRIMARY KEY AUTO_INCREMENT,
    ProductName VARCHAR(100),
    Price DOUBLE,
    Quantity INT
);
*/

//Hard
public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    public Student(int studentID, String name, String department, double marks) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.marks = marks;
    }

    public int getStudentID() { return studentID; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getMarks() { return marks; }
}
import java.sql.*;
import java.util.*;

public class StudentController {
    private final String url = "jdbc:mysql://localhost:3306/your_database_name";
    private final String username = "root";
    private final String password = "your_password";

    public void addStudent(Student s) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement("INSERT INTO Student VALUES (?, ?, ?, ?)")) {

            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setDouble(4, s.getMarks());
            ps.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e);
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery("SELECT * FROM Student");
            while (rs.next()) {
                Student s = new Student(rs.getInt("StudentID"), rs.getString("Name"),
                        rs.getString("Department"), rs.getDouble("Marks"));
                list.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e);
        }
        return list;
    }

    public void deleteStudent(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM Student WHERE StudentID=?")) {

            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student deleted!");
        } catch (SQLException e) {
            System.out.println("Error deleting student: " + e);
        }
    }
}
import java.util.*;

public class StudentView {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            System.out.println("\n1. Add Student\n2. View All\n3. Delete Student\n4. Exit");
            System.out.print("Select option: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Marks: ");
                    double marks = sc.nextDouble();

                    Student s = new Student(id, name, dept, marks);
                    controller.addStudent(s);
                    break;

                case 2:
                    List<Student> list = controller.getAllStudents();
                    System.out.println("\nID | Name | Dept | Marks");
                    for (Student st : list) {
                        System.out.println(st.getStudentID() + " | " + st.getName() +
                                " | " + st.getDepartment() + " | " + st.getMarks());
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to delete: ");
                    int deleteId = sc.nextInt();
                    controller.deleteStudent(deleteId);
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}

/*
CREATE TABLE Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100),
    Department VARCHAR(100),
    Marks DOUBLE
);
*/
