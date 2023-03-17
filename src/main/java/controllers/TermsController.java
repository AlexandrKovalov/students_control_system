package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students_4?user=root&password=65edefid");
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from term where status = '1';");

            while (resultSet.next()) {
                String name = resultSet.getString("term");
                String duration = resultSet.getString("duration");
                int id = resultSet.getInt("id");
                System.out.printf("%d %s\n", id, name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("WEB-INF/jsp/terms.jsp").forward(req, resp);
    }
}
