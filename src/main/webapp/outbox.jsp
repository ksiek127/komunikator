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
            <ul class="list-group list-group-flush">
                <c:forEach var="email" items="${requestScope.emails}">
                    <li class="list-group-item">
                        <b>Title: </b>${email.title}<br>
                        <b>Created: </b>${email.createdFormatted}<br>
                        <b>To group: </b> ${email.group.name} <br><br>
                        <form action="message-servlet" method="post">
                            <input type="hidden" name="mailId" value="${email.mailID}">
                            <input type="hidden" name="path" value="outbox">
                            <input type="submit" value="Show message" class="btn btn-outline-primary">
                        </form>
                    </li>
                </c:forEach>
            </ul>
            <br>
            <a href="returnToMainPage" class="btn btn-outline-primary">Return</a>

        </div>
    </div>
</div>
</body>
</html>