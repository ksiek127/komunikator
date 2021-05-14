<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register: </h2>
<form action="registration-handler" method="post">
    date of birth: <input type="date" name="birthdate"/><br>
    first name: <input type="text" name="firstname"/><br>
    last name: <input type="text" name="lastname"/><br>
    email: <input type="text" name="email"/><br>
    street: <input type="text" name="street"/><br>
    city: <input type="text" name="city"/><br>
    zip code: <input type="text" name="zipcode"/><br>
    <input type="submit" value="register"/>
</form>
</body>
</html>
