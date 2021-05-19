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
            Are you sure you want to leave group <b>${requestScope.group.name}</b>?
        </div>
        <div class="card-body">
            <c:if test="${requestScope.other_members == true}">
                <c:if test="${requestScope.group_admin == true}">
                    You are the admin of this group. You can choose to delete the group or to pick a new admin of the group
                    and leave.
                    <form action="deleteGroup" method="post">
                        <input type="hidden" name="group_id" value="${requestScope.group.groupID}">
                        <button type="submit" class="btn btn-outline-danger">Delete group</button>
                    </form>
                    <form action="pickAdminFromMembers" method="post">
                        <input type="hidden" name="group_id" value="${requestScope.group.groupID}">
                        <button type="submit" class="btn btn-outline-danger">Pick new admin and leave</button>
                    </form>
                </c:if>
                <c:if test="${requestScope.group_admin == false}">
                    <form action="leaveGroupHandler" method="post">
                        <input type="hidden" name="group_id" value="${requestScope.group.groupID}">
                        <button type="submit" class="btn btn-outline-danger">Leave group</button>
                    </form>
                </c:if>
            </c:if>
            <c:if test="${requestScope.other_members == null}">
                No more members in this group, if you leave, this group will be deleted.
                <form action="deleteGroup" method="post">
                    <input type="hidden" name="group_id" value="${requestScope.group.groupID}">
                    <button type="submit" class="btn btn-outline-danger">Delete group</button>
                </form>
            </c:if>
            <button type="button" class="btn btn-outline-primary" name="back" onclick="history.back()">
                Don't delete this group, return
            </button>
        </div>
    </div>
</div>
</body>
</html>
