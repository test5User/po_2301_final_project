<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home page</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
    <h2>User info:</h2>
    <p>name: ${user.name}</p>
    <p>login: ${user.login}</p>
    <p>email: ${user.email}</p>
</body>
</html>
