<%--
  Created by IntelliJ IDEA.
  User: samiu
  Date: 4/24/2022
  Time: 12:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@include file="navbar.jsp" %>
    <h5 class="my-3"><%=session.getAttribute("name")%>
    </h5>
    <p class="text-muted mb-1"><%=session.getAttribute("user")%>
    </p>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

