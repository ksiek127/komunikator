<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Message</title>
</head>
<body>

<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Here's your <b>e-mail</b>
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <li class="list-group-item"><b>Title:</b> ${requestScope.title}</li>
                <li class="list-group-item"><b>From:</b> ${requestScope.groupName}</li>
                <li class="list-group-item"><b>Date:</b> ${requestScope.date}</li>
                <li class="list-group-item"><b>Message:</b><br>${requestScope.msg}</li>
            </ul>
            <br>
            <a href="inbox" class="btn btn-outline-primary">Return</a>
        </div>
    </div>
</div>

</body>
</html>
