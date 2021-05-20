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
<form action="groups">
    <c:forEach var="groupM" items="${groupsMember}">
        <c:out value="${groupM.name}"/>
        <form action="go-to-message" method="post">
            <input type="hidden" name="groupId" value="${groupM.groupID}">
            <a href="sendmessage.jsp" class="btn btn-outline-primary">Send message</a>
        </form>
    </c:forEach>
    <c:forEach var="groupA" items="${groupsAdmin}">
        <c:out value="${groupA.name}"/>
        <form action="go-to-message" method="post">
            <input type="hidden" name="groupId" value="${groupA.groupID}">
            <a href="sendmessage.jsp" class="btn btn-outline-primary">Send message</a>
        </form>
    </c:forEach>
</form>
</body>
</html>
