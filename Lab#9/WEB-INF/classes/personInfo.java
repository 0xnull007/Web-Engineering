import java.io.*;
import java.sql.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;

public class personInfo extends HttpServlet {
    Connection con;

    @Override
    public void init(ServletConfig sc) {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/mydb"; // write your own database name instead of 'mydb'
            con = DriverManager.getConnection(url, "root", "root"); // same here, in case of user and password

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        printRecords(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "create":
                addRecord(request, response);
                break;
            case "update":
                updateRecord(request, response);
                break;
            case "delete":
                deleteRecord(request, response);
                break;
            default:
                break;
        }

    }

    void printRecords(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM info");

            out.println("<html><body><table><tr><th>Name</th><th>Address</th><th>Phone No.</th></tr>");
            while (rs.next()) {
                String name = rs.getString("name");
                String add = rs.getString("address");
                String phone = rs.getString("phone");

                out.println("<tr><td>" + name + "</td><td>" + add + "</td><td>" + phone + "</td></tr>");
            }
            out.println("</table></body></html>");

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    void addRecord(HttpServletRequest req, HttpServletResponse res) {
        String name = req.getParameter("name");
        String add = req.getParameter("address");
        String phone = req.getParameter("phone");

        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO info (name,address,phone) values (?, ?, ?)");
            pst.setString(1, name);
            pst.setString(2, add);
            pst.setString(3, phone);

            int rows = pst.executeUpdate();

            if (rows > 0) {
                System.out.println(rows + " rows inserted successfully! :) ");
                printRecords(req, res);
            } else {
                System.out.println("Something went wrong while adding record! :( ");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void updateRecord(HttpServletRequest req, HttpServletResponse res) {

        String name = req.getParameter("name");
        String add = req.getParameter("address");
        String phone = req.getParameter("phone");

        try {

            /* 1nd Approach (Better Way) */
            PreparedStatement pst = con.prepareStatement("update info set address = ?, phone = ? where name = ?");
            pst.setString(1, add);
            pst.setString(2, phone);
            pst.setString(3, name);

            int rs = pst.executeUpdate();
            if (rs > 0) {
                System.out.println("Record Updated successfully!");
                printRecords(req, res);
            } else {
                System.out.println("Somthing went wrong while updating record!");
            }

            /* 2nd Approach */

            // boolean flag = false;
            // Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
            // ResultSet.CONCUR_UPDATABLE);
            // ResultSet rs = st.executeQuery("SELECT * FROM info");
            // while (rs.next()) {
            // String n = rs.getString("name");
            // System.out.println(name);
            // if (n.equals(name)) {
            // rs.updateString("address", add);
            // rs.updateString("phone", phone);
            // rs.updateRow();
            // flag = true;
            // break;
            // }
            // }
            // if (flag) {
            // System.out.println("Record Updated successfully!");
            // printRecords(req, res);
            // } else {
            // System.out.println("Somthing went wrong while updating record!");
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void deleteRecord(HttpServletRequest req, HttpServletResponse res) {
        String n = req.getParameter("Dname");
        try {
            PreparedStatement pst = con.prepareStatement("DELETE FROM info WHERE name = ?");
            pst.setString(1, n);

            int rs = pst.executeUpdate();

            if (rs > 0) {
                System.out.println("Record Updated successfully!");
                printRecords(req, res);
            } else {
                System.out.println("Somthing went wrong while deleting record!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}