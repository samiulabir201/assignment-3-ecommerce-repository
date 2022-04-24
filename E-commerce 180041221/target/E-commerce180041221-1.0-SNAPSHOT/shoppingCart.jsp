<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/24/2022
  Time: 12:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Shopping Cart</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
<%@page import="com.example.ecommerce180041221.model.Cart" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce180041221.model.Product" %>
<%! List<Cart> shoppingCart = new ArrayList<Cart>();%>
<%! double total = 0;%>
<%
    shoppingCart = (List<Cart>) session.getAttribute("cart_lists");
    List<Product> productList = new Product().getAllProducts();

%>
<%@include file="navbar.jsp" %>

<div>
    <div>
        <h1>Shopping Cart</h1>

    </div>
    <hr>

    <%
        total = 0;
        for (Cart cart : shoppingCart) {
            int index = 0;
            for (int i = 0; i < productList.size(); i++) {
                if (productList.get(i).getId() == cart.getId()) {
                    index = i;
                    break;
                }
            }
            total += (Integer.valueOf(cart.getQuantity()) * Integer.valueOf(productList.get(index).getProduct_price()));

    %>
    <div>
        <div>
            <h6><%=productList.get(index).getProduct_name()%>
            </h6>
        </div>
        <div>
            <a href="modifyCart?param1=decrease&param2=<%=cart.getId()%>"
               style="text-decoration: none">
                <i class="fas fa-minus p-2"></i>

            </a>

            <input id="form1" min="0" name="quantity" value="<%=cart.getQuantity()%>"
                   type="text"
                   class="form-control form-control-sm"/>

            <a href="modifyCart?param1=increase&param2=<%=cart.getId()%>"
               style="text-decoration: none">

                <i class="fas fa-plus p-2"></i>

            </a>
        </div>
        <div>
            <h6>Tk <%=productList.get(index).getProduct_price()%>
            </h6>
        </div>
        <div>
            <a href="modifyCart?param1=removeItem&param2=<%=cart.getId()%>"
               class="text-muted"><i class="fas fa-times"></i></a>
        </div>
    </div>

    <hr>

    <%

        }%>

    <div>
        <h6><a href="productList.jsp" class="text-body"><i
                class="fas fa-long-arrow-alt-left me-2"></i>Back to shop</a></h6>
    </div>
</div>
</div>
<div>
    <div>
        <h3>Summary</h3>
        <hr>

        <div>
            <h5>items <%= shoppingCart.size()%>
            </h5>
            <h5>Tk <%=total%>
            </h5>
        </div>

        <hr>

        <div>
            <h5>Total price</h5>
            <h5>Tk <%=total%>
            </h5>
        </div>

        <a href="modifyCart?param1=checkOut">
            <button type="button"
                    data-mdb-ripple-color="dark">Check Out
            </button>
        </a>

    </div>
</div>

</body>
</html>

