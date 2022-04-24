<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/12/2022
  Time: 4:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@page import="com.example.ecommerce180041221.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%! List<Product> allProducts = new ArrayList<Product>();%>
<%
    Product product = new Product();
    allProducts = product.getAllProducts();

%>
<%@include file="navbar.jsp" %>

<div>All Products</div>
<div class="row">
    <%
        for (Product p : allProducts) {

    %>
    <div>
        <div>
            <div>
                <h5><%=p.getProduct_name()%>
                </h5>
                <h6>Tk <%=p.getProduct_price()%>BDT
                </h6>
                <form action="AddToCardServlet" method="get">
                    <input hidden name="product_id" value="<%=String.valueOf(p.getId())%>"/>
                    <input class="btn btn-primary" type="submit" value="Add to Cart"/>
                </form>

            </div>
        </div>
    </div>
    <%
        }
    %>
</div>

</body>
</html>

