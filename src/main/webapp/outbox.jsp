<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Outbox</title>
</head>
<body>
<h1><%= "Inbox" %>
</h1>
<br/>
<c:forEach var="email" items="${emails}">
    <tr>
        <td><c:out value="${email.title}"/></td>
        <td><c:out value="${email.message}"/></td>
    </tr>
</c:forEach>
</body>
</html>