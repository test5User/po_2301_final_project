<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="cite" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
  <h2>Hello ${user.name}</h2>
  <c:choose>
    <c:when test="${not empty orders}">
      <h2>Your orders:</h2>
      <c:forEach var="order" items="${orders}">
        <div class="order-list-container">
          <h3>${order.date} you order delivery to address ${order.address}.
          Id of order is ${order.id}</h3>
          <form method="post" action="qqq">
            <input type="hidden" name="orderId" value="${order.id}">
            <input type="submit" value="Print Receipt">
          </form>
        </div>
      </c:forEach>
    </c:when>
    <c:otherwise>
      <h2>You don't have any orders for now</h2>
    </c:otherwise>
  </c:choose>
</body>
</html>
