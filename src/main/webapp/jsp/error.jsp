<%--
  Created by IntelliJ IDEA.
  User: Иван
  Date: 16.11.2018
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Error_JSP</title>
</head>
<body>
Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name or type: ${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}

<a href="controller?command=to login">To Login Page</a>
</body>
</html>
