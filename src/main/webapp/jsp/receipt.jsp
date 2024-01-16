<%@ page import="by.itclass.constants.JspConstants" %>
<%@ page import="by.itclass.constants.ApplicationConstants" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Receipt</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
  <jsp:include page="<%=JspConstants.MENU_JSP%>"/>
  <div class="receipt-container">
      <h2>Order's id: ${orderReceipt.order.id}</h2>
      <h2>Order's date: ${orderReceipt.order.date}</h2>
      <h2>Delivery address: ${orderReceipt.order.address}</h2>
      <h2 class="underlined">You ordered:</h2>
      <c:forEach var="item" items="${orderReceipt.receiptItems}">
          <h2>${item.quantity} ${item.foodName} by ${item.foodPrice} byn.
              Amount: ${item.itemAmount} byn.</h2>
      </c:forEach>
      <h2 class="underlined"> Total amount: ${orderReceipt.total} byn.</h2>
  </div>

</body>
</html>
