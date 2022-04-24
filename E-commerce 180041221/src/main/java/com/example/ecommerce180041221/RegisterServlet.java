package com.example.ecommerce180041221;

import com.example.ecommerce180041221.model.Cart;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name  = request.getParameter("username");
        String email = request.getParameter("email");
        String password  = request.getParameter("password");
        String repassword  = request.getParameter("repassword");

        if(password.equals(repassword))
        {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
                Statement st = connection.createStatement();
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_table(emailid,name,password) VALUES (?,?,?)");

                preparedStatement.setString(1,email);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, password);
                preparedStatement.executeUpdate();
                HttpSession session= request.getSession();
                List<Cart>cartList = new ArrayList<Cart>();
                session.setAttribute("cart_lists",cartList);
                session.setAttribute("user",email);
                session.setAttribute("name",name);
                response.sendRedirect("productList.jsp");


            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else
        {
            request.setAttribute("error","Password and Repassword Must Be Same");
            request.getRequestDispatcher("registration.jsp").forward(request,response);
        }
    }
}
