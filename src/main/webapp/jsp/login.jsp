<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 20.11.2018
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login_JSP</title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h5>${incorrect}</h5>
<div class="form-style-2">
    <form method="post" action="controller">
        <input type="hidden" name="command" value="login">
        <label>Login:
            <input type="text" name="login" required>
        </label>
        <label>Password:
            <input type="password" name="password" required>
        </label>
        <input type="submit" value="Big Virgil">
    </form>
</div>


</body>
</html>
