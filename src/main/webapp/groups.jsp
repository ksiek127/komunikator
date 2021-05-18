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
    <c:out value="${group.name}"/>
</c:forEach>
</body>
</html>
