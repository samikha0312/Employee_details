// SaveEmployeeServlet.java
package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/saveemployee")
public class SaveEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String number = request.getParameter("number");
        String department = request.getParameter("department");
        String gender = request.getParameter("gender");
        String address = request.getParameter("address");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/company","root","12345");

            PreparedStatement ps = con.prepareStatement(
                "insert into employee values(?,?,?,?,?,?,?)");

            ps.setString(1,id);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, number);
            ps.setString(5, department);
            ps.setString(6, gender);
            ps.setString(7, address);

            int i = ps.executeUpdate();

            if(i > 0){
                response.sendRedirect("success.jsp");
            } else {
                response.sendRedirect("error.jsp");
            }

            con.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}