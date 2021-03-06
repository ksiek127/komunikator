<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>Inbox</title>
</head>
<body>

<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Here's your <b>Inbox</b>
        </div>
        <div class="card-body">
            <ul class="list-group list-group-flush">
                <c:forEach var="mail" items="${requestScope.new_mails}">
                    <li class="list-group-item">
                        <b>Title: </b>${mail.key.title} <span class="badge bg-primary">New</span> <br>
                        <b>Date: </b>${mail.key.createdFormatted} <br>
                        <b>Author: </b>${mail.value} <br>
                        <b>Group: </b>${mail.key.group.name} <br><br>
                        <form action="message-servlet" method="post">
                            <input type="hidden" name="mailId" value="${mail.key.mailID}">
                            <input type="hidden" name="path" value="inbox">
                            <input type="submit" value="Show message" class="btn btn-outline-primary">
                        </form>
                    </li>
                </c:forEach>
                <c:forEach var="mail" items="${requestScope.old_mails}">
                    <li class="list-group-item">
                        <b>Title: </b>${mail.key.title} <br>
                        <b>Date: </b>${mail.key.createdFormatted} <br>
                        <b>Author: </b>${mail.value} <br>
                        <b>Group: </b>${mail.key.group.name} <br><br>
                        <form action="message-servlet" method="post">
                            <input type="hidden" name="mailId" value="${mail.key.mailID}">
                            <input type="hidden" name="path" value="inbox">
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