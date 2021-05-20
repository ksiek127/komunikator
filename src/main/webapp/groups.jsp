<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<h1><%= "Groups" %>
</h1>
<br/>
    <c:forEach var="groupM" items="${requestScope.groupsMember}">
        <c:out value="${groupM.name}"/>
        <form action="go-to-message" method="post">
            <input type="hidden" name="groupId" value="${groupM.groupID}">
            <button type="submit" class="btn btn-outline-primary">Send message</button>
        </form>
    </c:forEach>
    <c:forEach var="groupA" items="${requestScope.groupsAdmin}">
        <c:out value="${groupA.name}"/>
        <form action="go-to-message" method="post">
            <input type="hidden" name="groupId" value="${groupA.groupID}">
            <button type="submit" class="btn btn-outline-primary">Send message</button>
        </form>
    </c:forEach>
</body>
</html>
