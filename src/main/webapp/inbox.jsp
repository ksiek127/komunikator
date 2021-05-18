<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Inbox</title>
</head>
<body>
<h1><%= "Inbox" %>
</h1>
<br/>
    <c:forEach var="email" items="${requestScope.emails}">
        <c:out value="${email.title}"/> <br>
        <c:out value="${email.message}"/> <br> <br>
    </c:forEach>
</body>
</html>