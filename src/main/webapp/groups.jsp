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
                <c:if test="${requestScope.group_remove_success == true}">
                    <div class="alert alert-success" role="alert">
                        Group removed successfully
                    </div>
                </c:if>
                <c:if test="${requestScope.group_remove_failed == true}">
                    <div class="alert alert-warning" role="alert">
                        Oops! Something has gone wrong with removing the group...
                    </div>
                </c:if>
                <c:if test="${requestScope.edit_data_successful == true}">
                    <div class="alert alert-success" role="alert">
                        Success!
                    </div>
                </c:if>
                <c:if test="${requestScope.group_leave_and_admin_change_success == true}">
                    <div class="alert alert-success" role="alert">
                        Left the group with admin changed
                    </div>
                </c:if>
                <c:if test="${requestScope.group_leave_and_admin_change_failed == true}">
                    <div class="alert alert-warning" role="alert">
                        Oops! Something has gone wrong with leaving the group and changing the admin...
                    </div>
                </c:if>
                <c:if test="${requestScope.group_leave_success == true}">
                    <div class="alert alert-success" role="alert">
                        Left the group
                    </div>
                </c:if>
                <c:if test="${requestScope.group_leave_failed == true}">
                    <div class="alert alert-warning" role="alert">
                        Oops! Something has gone wrong with leaving the group...
                    </div>
                </c:if>
                <c:forEach var="group" items="${requestScope.groupsMap}">
                    <li class="list-group-item">
                        <p><b>Group Name: </b> ${group.key.name}
                            <c:if test="${group.value == 'ADMIN'}">
                            <span class="badge bg-primary">Admin</span>
                            </c:if>
                            <c:if test="${group.value == 'MODERATOR'}">
                                <span class="badge bg-secondary">Moderator</span>
                            </c:if>
                            <br/>
                            <b>Description: </b> ${group.key.description}
                        </p>
                    <div class="container-sm">
                        <form action="groupMembersList" method="post">
                            <input type="hidden" name="groupId" value="${group.key.groupID}">
                            <input type="hidden" name="groupName" value="${group.key.name}">
                            <button type="submit" class="btn btn-outline-primary">Edit members</button>
                        </form>
                        <form action="groupRequests" method="post">
                            <input type="hidden" name="groupId" value="${group.key.groupID}">
                            <input type="hidden" name="groupName" value="${group.key.name}">
                            <button type="submit" class="btn btn-outline-primary">Check requests</button>
                        </form>
                        <form action="groupbox" method="post">
                            <input type="hidden" name="groupId" value="${group.key.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Group Mails</button>
                        </form>
                        <form action="go-to-message" method="post">
                            <input type="hidden" name="groupId" value="${group.key.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Send message</button>
                        </form>
                        <form action="edit-group-data" method="post">
                            <input type="hidden" name="groupId" value="${group.key.groupID}">
                            <button type="submit" class="btn btn-outline-primary">Edit group data</button>
                        </form>
                        <form action="leaveGroup" method="post">
                            <input type="hidden" name="group_id" value="${group.key.groupID}">
                            <input type="hidden" name="returnPage" value="/groups">
                            <button type="submit" class="btn btn-outline-danger">Leave group</button>
                        </form>
                    </div>
                    </li>
                </c:forEach>
                <c:forEach var="group" items="${requestScope.groupsMember}">
                    <li class="list-group-item">
                        <p><b>Group Name: </b> ${group.name} <span class="badge bg-dark">Member</span>
                            <br/><br/>
                            <b>Description: </b> ${group.description}</p>
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
                            <input type="hidden" name="returnPage" value="/groups">
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
