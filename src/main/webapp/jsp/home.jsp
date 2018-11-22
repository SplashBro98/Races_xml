<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 19.11.2018
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home_JSP</title>
</head>
<body>
<span style="color: ${cookie.color.value}">Hello!</span>
<form action="controller" method="post">
    <label>Color:
        <select name="color">
            <option value="red">Красный</option>
            <option value="black">Черный</option>
            <option value="white">Белый</option>
        </select>
    </label>
    <input type="hidden" name="command" value="home">
    <input type="submit" value="Send">
</form>
</body>
</html>
