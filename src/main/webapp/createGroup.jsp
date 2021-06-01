<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Create group</title>
</head>
<body>

<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            <b>Group</b> creator
        </div>
        <div class="card-body">
            <form action="create-group" method="post">
                <c:if test="${requestScope.empty_fields == true}">
                    <div class="alert alert-warning" role="alert">
                        All fields must be filled
                    </div>
                </c:if>
                <c:if test="${requestScope.name_taken == true}">
                    <div class="alert alert-warning" role="alert">
                        This name is already taken
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
                <button type="submit" class="btn btn-outline-primary">Create group</button>
            </form>

            <a href="returnToMainPage" class="btn btn-outline-secondary">Return</a>
        </div>
    </div>
</div>

</body>
</html>
