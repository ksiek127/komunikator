<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Edit group data</title>
</head>
<body>


<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Edit group data
        </div>
        <div class="card-body">
            <form action="edit-group-data-handler" method="post">
                <c:if test="${requestScope.empty_fields == true}">
                    <div class="alert alert-warning" role="alert">
                        At least one field must be filled! <br> If you don't want to change group data click "Return"
                    </div>
                </c:if>
                <c:if test="${requestScope.constraint_exception == true}">
                    <div class="alert alert-warning" role="alert">
                        Wrong data! You want to change group name to the one already assigned to another group
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
                <input type="hidden" name="groupId" value="${requestScope.groupId}">
                <button type="submit" class="btn btn-outline-primary">Submit</button>
            </form>

            <a href="groups" class="btn btn-outline-secondary">Return</a>
        </div>
    </div>
</div>


</body>
</html>
