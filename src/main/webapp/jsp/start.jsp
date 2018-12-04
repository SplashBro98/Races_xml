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

</head>
<body>
<div class="form-style-2">
<c:import url="test.jsp"/>
<form method="POST" action="controller" enctype="multipart/form-data">
<input type="hidden" name="command" value="parse">
<h5>Parser: </h5><input type="text" name="parser" pattern="(sax|dom|stax)" required><br/>
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








<%--<div class="form-group">--%>
        <%--<label>Email address</label>--%>
        <%--<input type="email" class="form-control" aria-describedby="emailHelp" placeholder="Enter email">--%>
        <%--<small class="form-text text-muted">We'll never share your email with anyone else.</small>--%>
    <%--</div>--%>
    <%--<div class="form-group">--%>
        <%--<label>Password</label>--%>
        <%--<input type="password" class="form-control" placeholder="Password">--%>
        <%--<small class="form-text text-muted">--%>
            <%--Your password must be 8-20 characters long, contain letters and numbers, and must not contain spaces, special characters, or emoji.--%>
        <%--</small>--%>
    <%--</div>--%>
    <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
<%--</form>--%>
<%--<form>--%>
    <%--<div class="form-group">--%>
        <%--<label for="exampleFormControlFile1">Example file input</label>--%>
        <%--<input type="file" class="form-control-file" id="exampleFormControlFile1">--%>
    <%--</div>--%>
<%--</form>--%>



<%--</body>--%>
<%--</html>--%>
