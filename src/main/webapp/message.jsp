<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<html>
<head>
    <title>Message</title>
</head>
<body>
<b>Title: ${requestScope.title}</b> <br>
<b>From: ${requestScope.groupName}</b> <br>
Message: ${requestScope.msg}
</body>
</html>
