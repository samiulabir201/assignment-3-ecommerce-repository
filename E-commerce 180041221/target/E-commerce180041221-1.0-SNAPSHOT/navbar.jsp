<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/24/2022
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<nav>
    <div>
        <a href="productList.jsp">Assignment 3</a>
        <div>
            <ul>
                <li>
                    <a href="profile.jsp">Hello <%=session.getAttribute("name")%>
                    </a>
                </li>
                <li>
                    <a href="shoppingCart.jsp">Cart</a>
                </li>
                <li>
                    <a href="logOutServlet">Log Out</a>
                </li>

            </ul>
        </div>
    </div>
</nav>
</body>
</html>
