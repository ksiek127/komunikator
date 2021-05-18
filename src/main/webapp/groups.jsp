<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<h1><%= "Groups" %>
</h1>
<br/>
<c:forEach var="group" items="${groups}">
    <tr>
        <td><c:out value="${group.title}"/></td>
        <td><c:out value="${group.message}"/></td>
    </tr>
</c:forEach>
</body>
</html>
