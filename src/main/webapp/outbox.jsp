<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>Outbox</title>
</head>
<body>

<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Here's your <b>Outbox</b>
        </div>
        <div class="card-body">
            <form action="outbox-handler" method="post">

                <ul class="list-group list-group-flush">
                    <c:forEach var="email" items="${requestScope.emails}">
                        <li class="list-group-item">
                            <b>${email.title}:</b> <br>
                                ${email.message}</li>
                    </c:forEach>
                </ul>
                <br>

                <button type="button" class="btn btn-outline-primary" name="back" onclick="history.back()">Back</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>