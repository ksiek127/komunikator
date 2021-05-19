<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Group Communicator Pick New Group Admin</title>
</head>
<body>
<br/>
    <div class="container-sm">
        <div class="card">
            <div class="card-header">
                Pick new admin from group <b>${requestScope.group_name}</b> members,
                you will be removed from the group afterwards:
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <c:forEach var="member" items="${requestScope.group_members}">
                        <li class="list-group-item">
                            Name: ${member.firstname} ${member.lastname}
                            Email: ${member.email}
                            Address: ${member.address}
                            <form action="giveAdminRoleAndLeaveGroup" method="post">
                                <input type="hidden" name="group_id" value="${requestScope.groupId}">
                                <input type="hidden" name="user_id" value="${member.userID}">
                                <button type="submit" class="btn btn-outline-danger">Give Admin Role</button>
                            </form>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
