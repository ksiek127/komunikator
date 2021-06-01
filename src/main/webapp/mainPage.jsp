<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>GroupCommunicator Profile</title>
</head>
<body>

<br/>
<div class="container">
    <div class="card-sm">
        <div class="card-header">
            Welcome, <b>${requestScope.user.firstname}</b>!
        </div>
        <div class="card-body">
            <h5 class="card-title">Here is your data</h5>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><b>Name:</b> ${requestScope.user.firstname} ${requestScope.user.lastname}</li>
                <li class="list-group-item"><b>Email:</b> ${requestScope.user.email}</li>
                <li class="list-group-item"><b>Birth date:</b> ${requestScope.user.birthDate}</li>
                <li class="list-group-item"><b>Address:</b> ${requestScope.user.address}</li>
            </ul>

            <a href="editData.jsp" class="btn btn-outline-primary">Edit your account details</a>
        </div>
    </div>

    <c:if test="${requestScope.edit_data_successful == true}">
        <div class="alert alert-success" role="alert">
            Success!
        </div>
    </c:if>

    <div class="container-sm">
        <div class="card">
            <div class="card-header">
                What do you want to do?
            </div>
            <div class="card-body">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                        <a href="inbox" class="btn btn-outline-primary">Inbox</a>
                    </li>
                    <li class="list-group-item">
                        <a href="outbox" class="btn btn-outline-primary">Outbox</a>
                    </li>
                    <li class="list-group-item">
                        <a href="groups" class="btn btn-outline-primary">Groups</a>
                    </li>
                    <li class="list-group-item">
                        <a href="groups-search" class="btn btn-outline-primary">Groups search</a>
                    </li>
                    <li class="list-group-item">
                        <a href="createGroup.jsp" class="btn btn-outline-primary">Create group</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>

    <div class="d-grid gap-2" style="margin-top:2vh;padding:1vw">
        <a href="checkIfGroupAdmin" class="btn btn-outline-danger">Delete this account</a>
        <a class="btn btn-outline-secondary" href="logout" role="button">Log out</a>
    </div>
</div>
</body>
</html>
