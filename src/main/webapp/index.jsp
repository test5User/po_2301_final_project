<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>2301 Pizza</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
    <img class="default-image" src="<%=JspConstants.BACKGROUND_IMG%>">
    <h1 class="greeting">Welcome!!!</h1>
</body>
</html>
