package com.example.ecommerce180041221.model;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class Product {

    private int id;
    private String product_name;
    private String product_price;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }


    public List<Product> getAllProducts(){

        List<Product> allProducts = new ArrayList<Product>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce", "root", "");
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT *FROM product");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProduct_name(rs.getString("product_name"));
                product.setProduct_price(rs.getString("product_price"));
                allProducts.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return allProducts;
    }
}

