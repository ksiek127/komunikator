<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Outbox</title>
</head>
<body>
<h1><%= "Outbox" %>
</h1>
<br/>
<c:forEach var="email" items="${requestScope.emails}">
    <c:out value="${email.title}"/> <br>
    <c:out value="${email.message}"/> <br> <br>
</c:forEach>
</body>
</html>