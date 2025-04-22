import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String empId = request.getParameter("empId");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/your_db", "username", "password");

            Statement stmt = con.createStatement();
            ResultSet rs;
            if (empId != null && !empId.isEmpty()) {
                rs = stmt.executeQuery("SELECT * FROM employees WHERE id=" + empId);
            } else {
                rs = stmt.executeQuery("SELECT * FROM employees");
            }

            out.println("<h2>Employee Details:</h2><table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Dept</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" +
                            rs.getString("name") + "</td><td>" + rs.getString("dept") + "</td></tr>");
            }
            out.println("</table>");

            con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
