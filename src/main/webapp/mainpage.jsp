<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>GroupCommunicator Profile</title>
</head>
<body>
<div class="container">
    <div class="card-sm">
        <div class="card-header">
            Welcome, <b>${requestScope.firstname}</b>!
        </div>
        <div class="card-body">
            <h5 class="card-title">Here is your data</h5>
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><b>Name:</b> ${requestScope.firstname} ${requestScope.lastname}</li>
                <li class="list-group-item"><b>Email:</b> ${requestScope.email}</li>
                <li class="list-group-item"><b>Birth date:</b> ${requestScope.birthdate}</li>
                <li class="list-group-item"><b>Address:</b> ${requestScope.address}</li>
            </ul>
            <a class="btn btn-outline-primary" href="#" role="button">Edit your account details</a>
        </div>
    </div>
    <div class="d-grid gap-2" style="margin-top:2vh;padding:1vw">
        <a class="btn btn-primary" href="inbox.jsp" role="button">Inbox</a>
        <a class="btn btn-primary" href="outbox.jsp" role="button">Outbox</a>
        <a class="btn btn-outline-secondary" href="index.jsp" role="button">Log out</a>
    </div>
</div>
</body>
</html>
