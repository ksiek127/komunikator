<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit your data</title>
</head>
<body>
<h2>Your new data: </h2>
<form action="edit-data-handler" method="post">
    date of birth: <input type="date" name="birthdate"/><br>
    first name: <input type="text" name="firstname"/><br>
    last name: <input type="text" name="lastname"/><br>
    email: <input type="text" name="email"/><br>
    address: <input type="text" name="address"/><br>
    <input type="submit" value="submit"/>
</form>
<a href="mainpage.jsp">Back to main page</a>
</body>
</html>
