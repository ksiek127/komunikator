<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
