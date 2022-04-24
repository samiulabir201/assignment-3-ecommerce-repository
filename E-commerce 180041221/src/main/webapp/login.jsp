<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/12/2022
  Time: 3:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<h1>Welcome To Online Shopping!!!</h1>
<div>
    <form action="loginServlet" method="post">
        <label for="email">Email:</label>
        <input type="email" id="email" name="email">
        <label for="password">Password:</label>
        <input type="password" id="password" name="password">
        <button type="submit">Log In</button>
    </form>
</div>
<div>
    <p>Don't have an account? <a href="registration.jsp">Register</a></p>
</div>


</body>
</html>
