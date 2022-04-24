package com.example.ecommerce180041221;

import com.example.ecommerce180041221.model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToCardServlet", value = "/AddToCardServlet")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String product_id = request.getParameter("product_id");
        int exits = 0;
        Cart cart = new Cart();
        cart.setId(Integer.parseInt(product_id));
        cart.setQuantity(1);
        List<Cart> cartList = new ArrayList<Cart>();
        HttpSession session = request.getSession(false);

        String emailid = (String) session.getAttribute("user");

        List<Cart> cart_lists = (List<Cart>) session.getAttribute("cart_lists");

        if (cart_lists == null) {
            cartList.add(cart);
            session.setAttribute("cart_lists", cartList);

        } else {
            cartList = cart_lists;

            for (Cart ct : cartList) {
                if (ct.getId() == Integer.valueOf(product_id)) {
                    int prevValue = ct.getQuantity();
                    ct.setQuantity(prevValue + 1);
                    exits = 1;
                    break;

                } else {
                    exits = 0;
                }
            }

            if (exits == 0) {
                cartList.add(cart);
            }

            session.setAttribute("cart_lists", cartList);
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT *FROM cart_list where id=" + product_id + " and user_emailid='abc@gmail.com'");

            if (rs.next() == false) {

                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cart_list(id,user_emailid,quantity) VALUES (?,?,?)");

                preparedStatement.setInt(1, Integer.valueOf(product_id));
                preparedStatement.setString(2, emailid);
                preparedStatement.setInt(3, 1);

                preparedStatement.executeUpdate();

            } else {
                String query1 = "UPDATE cart_list SET quantity = quantity + 1 where user_emailid='"+emailid+"' and id=" + product_id;
                st.executeUpdate(query1);
            }

            String query1 = "UPDATE product SET quantity = quantity - 1 where id=" + product_id;
            st.executeUpdate(query1);

            response.sendRedirect("productList.jsp");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
