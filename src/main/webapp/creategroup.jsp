
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create group</title>
</head>
<body>
<form action="create-group" method="post">
    <c:if test="${requestScope.empty_fields == true}">
    <div class="alert alert-warning" role="alert">
        All fields must be filled
    </div>
    </c:if>
    <input type="text" name="group-name"/>
    <input type="submit" value="create-group"/>
</form>
</body>
</html>
