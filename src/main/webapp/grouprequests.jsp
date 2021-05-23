<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Group requests</title>
</head>
<body>
<div>
    <c:forEach begin="0" end="${fn:length(requestScope.requests)}" varStatus="i">
        ${requestScope.usernames[i.index]} <br>
        ${requestScope.groupNames[i.index]}
        <form>
            <input type="submit" value="accept">
        </form>
        <form>
            <input type="submit" value="reject">
        </form>
    </c:forEach>
</div>
</body>
</html>