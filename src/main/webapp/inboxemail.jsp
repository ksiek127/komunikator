<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <c:forEach var="email" items="${emails}">
        <tr>
            <td><c:out value="${email.title}"/> </td>
            <td><c:out value="${email.message}"/> </td>
        </tr>
    </c:forEach>
</body>
</html>
