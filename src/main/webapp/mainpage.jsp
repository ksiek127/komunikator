<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logged in</title>
</head>
<body>
    <%
        Date birthDate = java.sql.Date.valueOf(request.getParameter("birthdate"));
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
    %>
    <div class = "user-data">
        Welcome<br>
        <%= birthDate%>
        <%= firstname%>
        <%= lastname%>
        <%= email%>
        <%= address%>
        <br>
    </div>
    <div class="inbox">
        <a href="inbox.jsp" class="button">Inbox</a>
    </div>
    <div class="outbox">
        <a href="outbox.jsp" class="button">Outbox</a>
    </div>
    <div class="edit-data">
        <a href="editdata.jsp" class="button">Edit you data</a>
    </div>

    <a href="index.jsp">Logout</a>
</body>
</html>
