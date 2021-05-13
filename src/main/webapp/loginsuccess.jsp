
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged in</title>
</head>
<body>
    <%
        String email = request.getParameter("email");
    %>
    Welcome, <%= email%>
    <a href="login.jsp">Logout</a>
</body>
</html>
