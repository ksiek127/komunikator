<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>Send message</title>
</head>
<body>
<form action="send-message" method="post">
  <c:if test="${requestScope.empty_fields == true}">
    <div class="alert alert-warning" role="alert">
      All fields must be filled
    </div>
  </c:if>
  <div class="mb-3">
    <label for="title" class="form-label">Title: </label>
    <input type="text" class="form-control" id="title"
           name="title" value="${fn:escapeXml(param.title)}">
  </div>
  <div class="mb-3">
    <label for="message" class="form-label">Message: </label>
    <input type="text" class="form-control" id="message"
           name="message" value="${fn:escapeXml(param.message)}">
  </div>
  <input type="hidden" name="groupId" value="${requestScope.groupId}">
  <button type="submit">Send</button>
</form>
</body>
</html>
