<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>GroupCommunicator</title>
</head>
<body>
<br/>
<div class="container-sm">
    <div class="card">
        <div class="card-header">
            Welcome to <b>GroupCommunicator</b> application!
        </div>
        <div class="card-body">
            <c:if test="${requestScope.logged_out == true}">
                <div class="alert alert-success" role="alert">
                    Logged out successfully
                </div>
            </c:if>
            <c:if test="${requestScope.created_user == true}">
                <div class="alert alert-success" role="alert">
                    Account created! You can log in with your e-mail address: ${requestScope.user_email}
                </div>
            </c:if>
            <c:if test="${requestScope.email_first == true}">
                <div class="alert alert-warning" role="alert">
                    Enter your email address first
                </div>
            </c:if>
            <c:if test="${requestScope.no_user == true}">
                <div class="alert alert-warning" role="alert">
                    Wrong email: no such user registered
                </div>
            </c:if>
            <c:if test="${requestScope.login_empty == true}">
                <div class="alert alert-warning" role="alert">
                    Please type in your email to log in
                </div>
            </c:if>
            <c:if test="${requestScope.removed_user == true}">
                <div class="alert alert-success" role="alert">
                    Account successfully removed
                </div>
            </c:if>
            <c:if test="${requestScope.error_while_removing_user == true}">
                <div class="alert alert-warning" role="alert">
                    Something went wrong while removing the account...
                </div>
            </c:if>
            <form action="login-handler" method="post">
                <div class="input-group mb-3">
                    <span class="input-group-text" id="basic-addon1">@</span>
                    <input type="text" class="form-control" placeholder="Email" aria-label="Email"
                           aria-describedby="basic-addon1" name="email" id="email" value="${fn:escapeXml(param.email)}">
                </div>
                <button type="submit" class="btn btn-outline-primary">Log in</button>
                <a class="btn btn-outline-primary" href="register.jsp" role="button">Register</a>
            </form>
        </div>
        <br>
    </div>
</div>
</body>
</html>