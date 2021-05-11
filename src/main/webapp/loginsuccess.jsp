
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String email = request.getParameter("email");
    %>
    Welcome, <%
        System.out.println(email);
    %>
</body>
</html>
