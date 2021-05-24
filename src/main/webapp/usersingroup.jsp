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
                <c:if test="${requestScope.no_rank_choosen == true}">
                    <div class="alert alert-warning" role="alert">
                        Choose group rank for the user you want to edit first
                    </div>
                </c:if>
                <c:if test="${requestScope.change_rank_success == true}">
                    <div class="alert alert-success" role="alert">
                        Group rank changed successfully!
                    </div>
                </c:if>
                <c:if test="${requestScope.change_rank_fail == true}">
                    <div class="alert alert-warning" role="alert">
                        Ooops... something went wrong with changing the group rank...
                    </div>
                </c:if>
                <c:if test="${requestScope.delete_success == true}">
                    <div class="alert alert-success" role="alert">
                        User deleted from this group successfully
                    </div>
                </c:if>
                <c:if test="${requestScope.delete_fail == true}">
                    <div class="alert alert-warning" role="alert">
                        Ooops... something went wrong with deleting the user from this group...
                    </div>
                </c:if>
                <c:if test="${requestScope.admin == true}">
                    <c:forEach var="member" items="${requestScope.groupMembers}">
                        <li class="list-group-item">
                            <p>${member.user.nameAndEmail}</p>
                            <p>Group rank: ${member.groupRank}</p>
                            <div class="container-sm">
                                <form action="changeRank" method="post">
                                    <input type="hidden" name="userId" value="${member.user.userID}">
                                    <input type="hidden" name="groupName" value="${requestScope.group_name}">
                                    <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                    <div class="input-group mb-3">
                                        <select class="form-select" name="groupRank" id="inputGroupSelect02">
                                            <option selected>Choose...</option>
                                            <c:if test="${member.groupRank != 'MODERATOR'}">
                                                <option value="MODERATOR">Moderator</option>
                                            </c:if>
                                            <c:if test="${member.groupRank != 'MEMBER'}">
                                                <option value="MEMBER">Member</option>
                                            </c:if>
                                        </select>
                                        <label class="input-group-text" for="inputGroupSelect02">Group rank</label>
                                    </div>
                                    <button type="submit" class="btn btn-outline-primary">Change rank</button>
                                </form>
                                <form action="deleteUserFromGroup" method="post">
                                    <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                    <input type="hidden" name="userId" value="${member.user.userID}">
                                    <input type="hidden" name="groupName" value="${requestScope.group_name}">
                                    <button type="submit" class="btn btn-outline-danger">Delete from group</button>
                                </form>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.moderator == true}">
                    <c:forEach var="member" items="${requestScope.groupMembers}">
                        <li class="list-group-item">
                            <p>${member.user.nameAndEmail}</p>
                            <p>Group rank: ${member.groupRank}</p>
                                <c:if test="${member.groupRank == 'MEMBER'}">
                                <div class="container-sm">
                                    <form action="changeRank" method="post">
                                        <input type="hidden" name="userId" value="${member.user.userID}">
                                        <input type="hidden" name="groupName" value="${requestScope.group_name}">
                                        <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                        <div class="input-group mb-3">
                                            <select class="form-select" name="groupRank" id="inputGroupSelect03">
                                                <option selected>Choose...</option>
                                                <c:if test="${member.groupRank != 'MODERATOR'}">
                                                    <option value="MODERATOR">Moderator</option>
                                                </c:if>
                                                <c:if test="${member.groupRank != 'MEMBER'}">
                                                    <option value="MEMBER">Member</option>
                                                </c:if>
                                            </select>
                                            <label class="input-group-text" for="inputGroupSelect03">Group rank</label>
                                        </div>
                                        <button type="submit" class="btn btn-outline-primary">Change rank</button>
                                    </form>
                                    <form action="deleteUserFromGroup" method="post">
                                        <input type="hidden" name="groupId" value="${requestScope.groupId}">
                                        <input type="hidden" name="userId" value="${member.user.userID}">
                                        <input type="hidden" name="groupName" value="${requestScope.group_name}">
                                        <button type="submit" class="btn btn-outline-danger">Delete from group</button>
                                    </form>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.member == true}">
                    <c:forEach var="member" items="${requestScope.groupMembers}">
                        <li class="list-group-item">
                            <p>${member.user.nameAndEmail}</p>
                            <p>Group rank: ${member.groupRank}</p>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
            <a href="groups" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</div>
</body>
</html>