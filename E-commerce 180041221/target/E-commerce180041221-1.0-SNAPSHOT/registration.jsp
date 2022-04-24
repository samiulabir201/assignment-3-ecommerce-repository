<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/12/2022
  Time: 3:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form method="post" action="RegisterServlet">

    <div>
        <label class="form-label">Your Name</label>
        <input type="text" name="username"/>
    </div>

    <div>
        <label class="form-label">Your Email</label>
        <input type="email" name="email"/>
    </div>

    <div>
        <label class="form-label">Password</label>
        <input type="password" name="password"/>
    </div>

    <div>
        <label class="form-label">Repeat your password</label>
        <input type="password" name="repassword"/>
    </div>

    <div>
        <button type="submit" class="btn btn-primary">
            Register
        </button>
    </div>
    <p>Have already an account? <a href="login.jsp"><u>Login here</u></a></p>

    <p><%=request.getAttribute("alerting") == null ? "" : request.getAttribute("alerting")%>
    </p>
</form>
</body>
</html>
