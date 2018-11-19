<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 15.11.2018
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="pageContent" var="var"/>
<html>
<head>
    <title><fmt:message key="label.startPage" bundle="${var}"/></title>
    <link href="/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="select-field">
    <form method="POST" action="controller" enctype="multipart/form-data">
        <input type="hidden" name="command" value="parse">
        <h5>Parser: </h5><input type="text" name="parser" pattern="(sax|dom|stax)" required><br/>
        <%--<input type="radio" name="parser" value="sax"/> <fmt:message key="label.SAXParser" bundle="${var}"/><br/>--%>
        <%--<input type="radio" name="parser" value="dom"/> <fmt:message key="label.DOMParser" bundle="${var}"/><br/>--%>
        <%--<input type="radio" name="parser" value="stax"/> <fmt:message key="label.StAXParser" bundle="${var}"/><br/>--%>
        <input type="file" name="fileName" required><br/>
        <input type="submit" value=<fmt:message key="label.Parse" bundle="${var}"/>>
    </form>
    <form>
        <a href="controller?command=russian">RU<a/>
        <a href="controller?command=english">EN<a/>
    </form>
</div>

</body>
</html>
