<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 16.11.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Main_JSP</title>
</head>
<body>
<h4>List of races from data.xml</h4>
<h4>Create with help of ${parser}</h4>
<div class="form-style-2">
    <table>
        <tr>
            <td><h4>Title</h4></td>
            <td><h4>Organizer</h4></td>
            <td><h4>Date</h4></td>
            <td><h4>Time</h4></td>
            <td><h4>Place</h4></td>
            <td><h4>Price</h4></td>
        </tr>
        <c:forEach items="${races}" var="race">
            <tr>
                <td>${race.getTitle()}</td>
                <td>${race.organizer}</td>
                <td>${race.date}</td>
                <td>${race.time}</td>
                <td>${race.place}</td>
                <td>${race.ticketPrice}</td>
            </tr>
            <%--<tr>--%>
            <%--<td><h5>Nickname</h5></td>--%>
            <%--<td><h5>Age</h5></td>--%>
            <%--<td><h5>Breed</h5></td>--%>
            <%--</tr>--%>
        </c:forEach>
    </table>


    <a href="/controller?command=return">To start page</a>

</div>

</body>
</html>
