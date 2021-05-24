<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

<html>
<head>
    <title>Group created</title>
</head>
<body>

<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            <b>Success!</b>
        </div>
        <div class="card-body">
            <h5 class="card-title">Your group has been created!</h5>
            <br/>
            <div class="container-sm">
                <div class="card">
                    <div class="card-header">
                        <b>${requestScope.name}</b>
                    </div>
                    <div class="card-body">
                        ${requestScope.description}
                    </div>
                </div>
            </div>
            <br/>
            Now you can add new members and send them messages!
            <br/>
            <a href="groups" class="btn btn-outline-primary">Go to group list</a>
            <a href="returnToMainPage" class="btn btn-outline-secondary">Return to main page</a>
        </div>
    </div>
</div>

</body>
</html>
