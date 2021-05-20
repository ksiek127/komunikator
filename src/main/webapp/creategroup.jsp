<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
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
    <div class="mb-3">
        <label for="name" class="form-label">Group name: </label>
        <input type="text" class="form-control" id="name"
               name="name" value="${fn:escapeXml(param.name)}">
    </div>
    <div class="mb-3">
        <label for="description" class="form-label">Description: </label>
        <input type="text" class="form-control" id="description"
               name="description" value="${fn:escapeXml(param.description)}">
    </div>
    <input type="submit" value="create-group"/>
</form>
</body>
</html>
