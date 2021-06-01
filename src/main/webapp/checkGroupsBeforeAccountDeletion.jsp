<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Group Communicator Leaving Group</title>
</head>
<body>
<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            You are admin of these groups, what do you want to do before you delete your account?
        </div>
        <div class="card-body">
            <c:forEach var="group" items="${requestScope.groups}">
                Group: <b>${group.name}</b>
                <form action="deleteGroup" method="post">
                    <input type="hidden" name="group_id" value="${group.groupID}">
                    <input type="hidden" name="returnPage" value="/checkIfGroupAdmin">
                    <button type="submit" class="btn btn-outline-danger">Delete group</button>
                </form>
                <form action="pickAdminFromMembers" method="post">
                    <input type="hidden" name="group_id" value="${group.groupID}">
                    <input type="hidden" name="returnPage" value="/checkIfGroupAdmin">
                    <button type="submit" class="btn btn-outline-danger">Pick new admin</button>
                </form>
            </c:forEach>
            <button type="button" class="btn btn-outline-primary" name="back" onclick="history.back()">
                Return
            </button>
        </div>
    </div>
</div>
</body>
</html>
