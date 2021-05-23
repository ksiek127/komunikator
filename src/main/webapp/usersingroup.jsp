<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
    <title>Users in group</title>
</head>
<body>
<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Users in group <b>${requestScope.group_name}</b>
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <c:forEach var="member" items="${requestScope.groupMembers}">
                    <li class="list-group-item">
                        <p>${member.user.nameAndEmail}</p>
                        <p>Group rank: ${member.groupRank}</p>
                        <div class="container-sm">
                            <form action="changeRole" method="post">
                                <input type="hidden" name="userId" value="${member.user.userID}">
                                <input type="hidden" name="groupName" value="${requestScope.group_name}">
                                <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                <div class="input-group mb-3">
                                    <select class="form-select" name="groupRank" id="inputGroupSelect02">
                                        <option selected>Choose...</option>
                                        <option value="MODERATOR">Moderator</option>
                                        <option value="MEMBER">Member</option>
                                    </select>
                                    <label class="input-group-text" for="inputGroupSelect02">Group rank</label>
                                </div>
                                <button type="submit" class="btn btn-outline-primary">Change rank</button>
                            </form>
                            <form action="deleteUserFromGroup" method="post">
                                <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                <input type="hidden" name="userId" value="${member.user.userID}">
                                <button type="submit" class="btn btn-outline-danger">Delete from group</button>
                            </form>
                        </div>
                    </li>
                </c:forEach>
            </ul>
            <a href="groups" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</div>
</body>
</html>