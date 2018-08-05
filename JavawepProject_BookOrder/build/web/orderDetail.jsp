<%-- 
    Document   : orderDetail
    Created on : Mar 13, 2018, 1:14:28 PM
    Author     : DEll
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail Page</title>
    </head>
    <body>
        
        <c:set var="user" value="${sessionScope.user}"/>
        
        <c:if test="${empty user}">
            <h4>You have to log in first</h4>
            <a href="login.html">Go to login page</a>
        </c:if>
            
        <c:if test="${not empty user}">
            <div style="position:absolute; top:20px;right:20px">
                <a href="logout">logout</a>
            </div>
            
            <h4>Order Details</h4>
            <c:set var="customer" value="${requestScope.customer}"/>
            <c:set var="orderDetail" value="${requestScope.orderDetail}"/>
            <c:set var="orderDate" value="${requestScope.orderDate}"/>
            <c:set var ="orderId" value="${requestScope.orderID}"/>
            <div style="display: inline-block;width:40%;">
                OrderId: ${orderId}
                <br>
                Customer: ${customer.name}
            </div>
            <div style="display: inline-block;width:40%;">
                Date: ${orderDate}
                <br>
                Phone: ${customer.phone}
            </div>
            <br>
            Address: ${customer.address}
            <br>
            Details:
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Product</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Total</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="orderItem" items="${orderDetail}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>
                                ${orderItem.productName}
                            </td>
                            <td>
                                ${orderItem.quantity}
                            </td>
                            <td>
                                ${orderItem.price}
                            </td>
                            <td>
                                ${orderItem.total}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <br>
            <c:set var="fromDate" value="${requestScope.fromDate}"/>
            <c:set var="toDate" value="${requestScope.toDate}"/>
            <a href='searchOrder?fromDate=${fromDate}&toDate=${toDate}'>Back to order list page</a>
            <br>
            <a href="searchOrder.jsp">Back to search page</a>
        </c:if>
    </body>
</html>
