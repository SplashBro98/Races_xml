<%@ page import="com.epam.races.entity.HorseRace" %>
<%@ page import="com.epam.races.entity.Race" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.races.entity.Horse" %><%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 16.11.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
            <td><h4>Number</h4></td>
            <td><h4>Title</h4></td>
            <td><h4>Organizer</h4></td>
            <td><h4>Date</h4></td>
            <td><h4>Time</h4></td>
            <td><h4>Place</h4></td>
            <td><h4>Price</h4></td>
        </tr>
        <c:forEach items="${horseRaces}" var="horseRace" varStatus="status1">
            <tr>
                <td>${status1.count}</td>
                <td>${horseRace.title}</td>
                <td>${horseRace.organizer}</td>
                <td>${horseRace.date}</td>
                <td>${horseRace.time}</td>
                <td>${horseRace.place}</td>
                <td>${horseRace.ticketPrice}</td>
            </tr>
            <tr>
                <td></td>
                <td><h4>NickName</h4></td>
                <td><h4>Age</h4></td>
                <td><h4>Breed</h4></td>
            </tr>
            <c:forEach items="${horseRace.horses}" var="horse">
                <tr>
                    <td></td>
                    <td>${horse.nickname}</td>
                    <td>${horse.age}</td>
                    <td>${horse.breed}</td>
                </tr>
            </c:forEach>
        </c:forEach>
        <c:forEach items="${dogRaces}" var="dogRace" varStatus="status2">
            <tr>
                <td>${status2.count}</td>
                <td>${dogRace.title}</td>
                <td>${dogRace.organizer}</td>
                <td>${dogRace.date}</td>
                <td>${dogRace.time}</td>
                <td>${dogRace.place}</td>
                <td>${dogRace.ticketPrice}</td>
            </tr>
            <tr>
                <td></td>
                <td><h4>NickName</h4></td>
                <td><h4>Age</h4></td>
                <td><h4>Breed</h4></td>
            </tr>
            <c:forEach items="${dogRace.dogs}" var="dog">
                <tr>
                    <td></td>
                    <td>${dog.nickname}</td>
                    <td>${dog.age}</td>
                    <td>${dog.breed}</td>
                </tr>
            </c:forEach>
        </c:forEach>
    </table>


    <a href="controller?command=to start">To start page</a>

</div>

</body>
</html>
