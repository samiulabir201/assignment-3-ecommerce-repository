package com.example.ecommerce180041221;

import com.example.ecommerce180041221.model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "loginServlet", value = "/loginServlet")
public class loginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailid = request.getParameter("email");
        String password = request.getParameter("password");
        String name = "";
        int flag =0 ;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT *FROM user_table");

            while (rs.next()) {
                String emailid1 = rs.getString("emailid");
                String password1 = rs.getString("password");

                if (emailid.equals(emailid1) && password.equals((password1))) {
                    name = rs.getString("name");
                    flag = 1;
                    break;
                }

            }
            if(flag==1)
            {
                HttpSession session = request.getSession();
                List<Cart> cartList = new ArrayList<Cart>();
                List<Integer> id = new ArrayList<Integer>();
                List<Integer> quantity = new ArrayList<Integer>();

                rs = st.executeQuery("SELECT *FROM cart_list where user_emailid='"+emailid+"'");

                int i = 0;
                while (rs.next())
                {
                    id.add(rs.getInt("id"));
                    quantity.add(rs.getInt("quantity"));
                }
                for(i=0;i<id.size();i++)
                {
                    Cart cart = new Cart();
                    cart.setId(id.get(i));
                    cart.setQuantity(quantity.get(i));
                    cartList.add(cart);
                }

                session.setAttribute("cart_lists",cartList);
                session.setAttribute("user",emailid);
                session.setAttribute("name",name);
                response.sendRedirect("productList.jsp");
            }
            else {
                request.setAttribute("error","Password or Email Does not Match");
                request.getRequestDispatcher("login.jsp").forward(request,response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
