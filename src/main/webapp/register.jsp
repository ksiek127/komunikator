<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<!DOCTYPE html>
<html>
<head>
    <title>GroupCommunicator Registration</title>
</head>
<body>
<div class="conatiner-md">
    <div class="card">
        <div class="card-header">
            Registration form
        </div>
        <div class="card-body">
            <form action="registration-handler" method="post">
                <c:if test="${requestScope.empty_fields == true}">
                    <div class="alert alert-warning" role="alert">
                        All fields must be filled
                    </div>
                </c:if>
                <c:if test="${requestScope.constraint_exception == true}">
                    <div class="alert alert-warning" role="alert">
                        Wrong data! Check if all your data is valid
                    </div>
                </c:if>
                <div class="mb-3">
                    <label for="birthdate" class="form-label">Birth date</label>
                    <input type="date" class="form-control" id="birthdate"
                           name="birthdate" value="${fn:escapeXml(param.birthdate)}">
                </div>
                <div class="mb-3">
                    <label for="firstname" class="form-label">Firstname</label>
                    <input type="text" class="form-control" id="firstname"
                           name="firstname" value="${fn:escapeXml(param.firstname)}">
                </div>
                <div class="mb-3">
                    <label for="lastname" class="form-label">Lastname</label>
                    <input type="text" class="form-control" id="lastname"
                           name="lastname" value="${fn:escapeXml(param.lastname)}">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email"
                           name="email" value="${fn:escapeXml(param.email)}">
                </div>
                <div class="mb-3">
                    <label for="street" class="form-label">Street</label>
                    <input type="text" class="form-control" id="street"
                           name="street" value="${fn:escapeXml(param.street)}">
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city"
                           name="city" value="${fn:escapeXml(param.city)}">
                </div>
                <div class="mb-3">
                    <label for="zipcode" class="form-label">Zip code</label>
                    <input type="text" class="form-control" id="zipcode"
                           name="zipcode" value="${fn:escapeXml(param.zipcode)}">
                </div>
                <button type="submit" class="btn btn-outline-primary">Register</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
