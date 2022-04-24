package com.example.ecommerce180041221;

import com.example.ecommerce180041221.model.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModifyCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("param1");
        String id;
        HttpSession session = request.getSession(false);
        String emailid = (String) session.getAttribute("user");
        List<Cart> cartList = new ArrayList<Cart>();
        cartList = (List<Cart>) session.getAttribute("cart_lists");
        System.out.println("Hi");
        System.out.println(action);

        switch (action) {
            case "increase":
                id = request.getParameter("param2");
                updateCartDb(id, "+", "-", emailid);
                if (cartList != null) {
                    session.setAttribute("cart_lists", updateCartSession(id, cartList, +1));
                }
                break;
            case "decrease":
                id = request.getParameter("param2");
                updateCartDb(id, "-", "+", emailid);
                if (cartList != null) {
                    session.setAttribute("cart_lists", updateCartSession(id, cartList, -1));
                }
                break;
            case "removeItem":
                id = request.getParameter("param2");
                removeCartDb(id,"removeOneItem",emailid);
                if (cartList!=null)
                {
                    session.setAttribute("cart_lists",updateCartSession(id,cartList,0));
                }
                break;
            case "checkOut":
                id = "dontCare";
                removeCartDb(id,"checkOut",emailid);
                if (cartList!=null)
                {
                    session.setAttribute("cart_lists",updateCartSession(id,cartList,2));
                }
                break;
        }
        response.sendRedirect(action.equals("checkOut")? "productList.jsp":"shoppingCart.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    void updateCartDb(String id, String operation, String operation1, String emailid) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement st = connection.createStatement();
            String query = "UPDATE cart_list SET quantity = quantity" + operation + "  1 where user_emailid='" + emailid + "' and id=" + id;
            System.out.println(query);
            st.executeUpdate("UPDATE cart_list SET quantity = quantity" + operation + "1 where user_emailid='" + emailid + "' and id=" + id);

            st.executeUpdate("UPDATE product SET quantity = quantity " + operation1 + "1 where id=" + id);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    void removeCartDb(String id,String operation,String emailid)
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement st = connection.createStatement();

            if(operation.equals("removeOneItem"))
            {
                ResultSet rs = st.executeQuery("SELECT quantity FROM cart_list where user_emailid='" + emailid + "' and id=" + id);
                int currentQuantity = 0;
                while (rs.next())
                {
                    currentQuantity = rs.getInt("quantity");
                }

                st.executeUpdate("UPDATE product SET quantity = quantity + " + currentQuantity + " where id=" + id);
                st.executeUpdate("DELETE FROM cart_list where user_emailid='" + emailid + "' and id=" + id);
            }
            else
            {
                st.executeUpdate("DELETE FROM cart_list where user_emailid='" + emailid + "'");
            }



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    List<Cart> updateCartSession(String id, List<Cart> cartList, int value) {

        if(value != 2) {
            for (Cart cart : cartList) {
                if (cart.getId() == Integer.valueOf(id)) {
                    if (value == 1 || value == -1) {
                        cart.setQuantity(cart.getQuantity() + value);
                        break;
                    } else if (value == 0) {
                        cartList.remove(cart);
                        break;
                    }
                }
            }
            return cartList;
        }
        else{
            cartList.clear();
            return cartList;
        }
    }
}
