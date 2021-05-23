<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>Group requests</title>
</head>
<body>
<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Group requests for <b>${requestScope.group_name}</b>
        </div>
        <div class="card-body">
            <c:if test="${requestScope.accept_fail == true}">
                <div class="alert alert-warning" role="alert">
                    Ooops... something went wrong when accepting the request
                </div>
            </c:if>
            <c:if test="${requestScope.no_rank_choosen == true}">
                <div class="alert alert-warning" role="alert">
                    Choose group rank for the user you want to accept first
                </div>
            </c:if>
            <c:if test="${requestScope.accept_success == true}">
                <div class="alert alert-success" role="alert">
                    Request accepted successfully
                </div>
            </c:if>
            <c:if test="${requestScope.reject_fail == true}">
                <div class="alert alert-warning" role="alert">
                    Ooops... something went wrong when rejecting the request
                </div>
            </c:if>
            <c:if test="${requestScope.reject_success == true}">
                <div class="alert alert-success" role="alert">
                    Request rejected successfully
                </div>
            </c:if>
            <ul class="list-group list-group-flush">
                <c:forEach var="user" items="${requestScope.users}">
                    <li class="list-group-item">
                        <p>${user.nameAndEmail}</p>
                        <form action="acceptRequest" method="post">
                            <input type="hidden" name="userId" value="${user.userID}">
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
                            <button type="submit" class="btn btn-outline-primary">Accept request</button>
                        </form>
                        <form action="rejectRequest" method="post">
                            <input type="hidden" name="userId" value="${user.userID}">
                            <input type="hidden" name="groupId" value="${requestScope.groupId}">
                            <input type="hidden" name="groupName" value="${requestScope.group_name}">
                            <button type="submit" class="btn btn-outline-danger">Reject request</button>
                        </form>
                    </li>
                </c:forEach>
            </ul>
            <a href="groups" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</div>

</body>
</html>