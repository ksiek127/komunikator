<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Message sent</title>
</head>
<body>
    <br/>
    <div class="container-sm">
        <div class="card">
            <div class="card-header">
                <b>Success!</b>
            </div>
            <div class="card-body">
                <h5 class="card-title">Your message:</h5>
                <br/>
                <div class="container-sm">
                    <div class="card">
                        <div class="card-header">
                            <b>${requestScope.title}</b>
                        </div>
                        <div class="card-body">
                            ${requestScope.message}
                        </div>
                    </div>
                </div>
                <br/>
                has been sent to <b>${requestScope.groupName}!</b>
                <br/>
                <a href="groups" class="btn btn-outline-primary">Return to group list</a>
            </div>
        </div>
    </div>


</body>
</html>
