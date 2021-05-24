<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<!DOCTYPE html>
<html>
<head>
    <title>Groups</title>
</head>
<body>
<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Your groups
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <c:forEach var="group" items="${requestScope.groupsAdmin}">
                    <li class="list-group-item">
                        <p><b>Group Name: </b> ${group.name} <span class="badge bg-primary">Admin</span></p>
                    <div class="container-sm">
                        <form action="groupMembersList" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <input type="hidden" name="groupName" value="${group.name}">
                            <button type="submit" class="btn btn-outline-primary">Edit members</button>
                        </form>
                        <form action="groupRequests" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <input type="hidden" name="groupName" value="${group.name}">
                            <button type="submit" class="btn btn-outline-primary">Check requests</button>
                        </form>
                        <form action="groupbox" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Group Mails</button>
                        </form>
                        <form action="go-to-message" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Send message</button>
                        </form>
                        <form action="leaveGroup" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-danger">Leave group</button>
                        </form>
                    </div>
                    </li>
                </c:forEach>
                <c:forEach var="group" items="${requestScope.groupsModerator}">
                    <li class="list-group-item">
                        <p><b>Group Name: </b> ${group.name} <span class="badge bg-info text-dark">Moderator</span></p>
                    <div class="container-sm">
                        <form action="groupMembersList" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <input type="hidden" name="groupName" value="${group.name}">
                            <button type="submit" class="btn btn-outline-primary">Edit members</button>
                        </form>
                        <form action="groupbox" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Group Mails</button>
                        </form>
                        <form action="go-to-message" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Send message</button>
                        </form>
                        <form action="leaveGroup" method="post">
                            <input type="hidden" name="group_id" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-danger">Leave group</button>
                        </form>
                    </div>
                </li>
                </c:forEach>
                <c:forEach var="group" items="${requestScope.groupsMember}">
                    <li class="list-group-item">
                        <p><b>Group Name: </b> ${group.name} <span class="badge bg-dark">Member</span></p>
                    <div class="container-sm">
                        <form action="groupMembersList" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <input type="hidden" name="groupName" value="${group.name}">
                            <button type="submit" class="btn btn-outline-primary">Members list</button>
                        </form>
                        <form action="groupbox" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Group Mails</button>
                        </form>
                        <form action="go-to-message" method="post">
                            <input type="hidden" name="groupId" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Send message</button>
                        </form>
                        <form action="leaveGroup" method="post">
                            <input type="hidden" name="group_id" value="${group.groupID}">
                            <button type="submit" class="btn btn-outline-danger">Leave group</button>
                        </form>
                    </div>
                </li>
                </c:forEach>
            </ul>
            <a href="returnToMainPage" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</div>
</body>
</html>
