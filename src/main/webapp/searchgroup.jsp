<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>Group search</title>
</head>
<body>
<br/>
    <div class="container-sm">
        <div class="card">
            <div class="card-header">
                Search for a group
            </div>
            <div class="card-body">
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
                <c:if test="${requestScope.request_delete_failed == true}">
                    <div class="alert alert-warning" role="alert">
                        Oops! Something has gone wrong with deleting the request...
                    </div>
                </c:if>
                <c:if test="${requestScope.request_deleted == true}">
                    <div class="alert alert-success" role="alert">
                        Request deleted successfully
                    </div>
                </c:if>
                <c:if test="${requestScope.no_group_name == true}">
                    <div class="alert alert-warning" role="alert">
                        Enter group name you want to find
                    </div>
                </c:if>
                <c:if test="${requestScope.no_such_group == true}">
                    <div class="alert alert-warning" role="alert">
                        There is no group with name like that
                    </div>
                </c:if>
                <c:if test="${requestScope.request_failed == true}">
                    <div class="alert alert-warning" role="alert">
                        Oops! Something has gone wrong...
                    </div>
                </c:if>
                <c:if test="${requestScope.request_sent == true}">
                    <div class="alert alert-success" role="alert">
                        Request sent!
                    </div>
                </c:if>
                <c:if test="${requestScope.search_error == true}">
                    <div class="alert alert-warning" role="alert">
                        Something went wrong while receiving search result...
                    </div>
                </c:if>
                <form action="groups-search" method="post">
                    <div class="input-group mb-3">
<%--                        <span class="input-group-text" id="basic-addon1"></span>--%>
                        <input type="text" class="form-control" placeholder="Group name" aria-label="Email"
                               aria-describedby="basic-addon1" name="group_name" id="group_name"
                               value="${fn:escapeXml(param.group_name)}">
                    </div>
                    <button type="submit" class="btn btn-outline-primary">Search</button>
                    <a href="returnToMainPage" class="btn btn-outline-primary">Return</a>
                </form>
            </div>
        </div>
    </div>

<br/>
    <c:if test="${requestScope.groups != null}">
        <div class="container-sm">
            <div class="card">
                <div class="card-header">
                    Search results
                </div>
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <c:forEach var="group" items="${requestScope.groups}">
                            <li class="list-group-item"><b>Group Name: </b> ${group.key.name}
                                <b>Description: </b> ${group.key.description}
                                <br/>
                                <c:if test="${group.value == 'none'}">
                                    <form action="sendRequest" method="post">
                                        <input type="hidden" name="group_id" value="${group.key.groupID}">
                                        <button type="submit" class="btn btn-outline-primary">Join group</button>
                                    </form>
                                </c:if>
                                <c:if test="${group.value == 'requested'}">
                                    <form action="deleteRequest" method="post">
                                        <input type="hidden" name="group_id" value="${group.key.groupID}">
                                        <button type="submit" class="btn btn-outline-danger">Delete request</button>
                                    </form>
                                </c:if>
                                <c:if test="${group.value == 'joined'}">
                                    <form action="leaveGroup" method="post">
                                        <input type="hidden" name="group_id" value="${group.key.groupID}">
                                        <button type="submit" class="btn btn-outline-danger">Leave group</button>
                                    </form>
                                </c:if>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </c:if>
</body>
</html>