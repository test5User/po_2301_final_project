<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="nav-ul">
    <c:choose>
        <c:when test="${empty user}">
            <li class="nav-li">
                <a href="<%=JspConstants.LOGIN_JSP%>">Login</a></li>
            <li class="nav-li">
                <a href="<%=JspConstants.REGISTRATION_JSP%>">Register</a></li>
        </c:when>
        <c:otherwise>
            <li class="nav-li float-left">
                <a class="active" href="<%=JspConstants.HOME_JSP%>">Home</a></li>
            <li class="nav-li float-left">
                <a href="<%=ApplicationConstants.PIZZAS_MENU%>">Pizza</a></li>
            <li class="nav-li float-left">
                <a href="<%=ApplicationConstants.DRINKS_MENU%>">Drink</a></li>
            <li class="nav-li">
                <a href="<%=ApplicationConstants.LOGOUT_CONTROLLER%>">Logout</a></li>
            <li class="nav-li">
                <a href="<%=JspConstants.CART_JSP%>">Cart</a></li>
            <li class="nav-li">
                <a href="<%=ApplicationConstants.ORDER_HISTORY_CONTROLLER%>">Orders</a></li>
        </c:otherwise>
    </c:choose>
</ul>
