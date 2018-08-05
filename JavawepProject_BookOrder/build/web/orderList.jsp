<%-- 
    Document   : orderList
    Created on : Mar 12, 2018, 3:34:12 PM
    Author     : DEll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Page</title>
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
            
            <c:set var="fromDate" value="${requestScope.fromDate}"/>
            <c:set var="toDate" value="${requestScope.toDate}"/>
            <c:set var="isDeliver" value="${requestScope.Delivered}"/>
            <c:set var="searchResult" value="${requestScope.searchResult}"/>
            <h4>Order List</h4>
            <span>From: ${fromDate}</span>
            <span style="position:relative;left:100px">To: ${toDate}</span>
            <br>
            <c:if test="${not empty searchResult}">
                <form action="updateOrder">
                <table border="1">
                    <thead>
                        <tr>
                            <th>No.</th>
                            <th>Date</th>
                            <th>Total</th>
                            <th>Customer</th>
                            <th>Action</th>
                            <th>Reason</th>
                            <th>Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${searchResult}"
                                   varStatus="counter">
                            <tr>
                                <td>
                                    ${counter.count}
                                </td>
                                <td>
                                    ${order.orderDate}
                                </td>
                                <td>
                                    ${order.totalPrice}
                                </td>
                                <td>
                                    ${order.customerId}
                                </td>
                                <td>
                                    <input type="checkbox" name="chkAction"
                                           value="${order.orderId}"/>
                                    <input type="hidden" name="isDeliver"
                                           value="${isDeliver}"/>
                                    <input type="hidden" name="fromDate"
                                           value="${fromDate}"/>
                                    <input type="hidden" name="toDate"
                                           value="${toDate}"/>
                                </td>
                                <td>
                                    ${order.reason}
                                </td>
                                <td>
                                    <c:url var="viewOrderDetail" value="orderDetail">
                                        <c:param name="orderId" 
                                                 value="${order.orderId}"/>
                                        <c:param name="customerId" 
                                                 value="${order.customerId}"/>
                                        <c:param name="orderDate" 
                                                 value="${order.orderDate}"/>
                                        <c:param name="fromDate"
                                                 value="${fromDate}"/>
                                        <c:param name="toDate"
                                                 value="${toDate}"/>
                                        <c:param name="isDeliver"
                                                 value="${isDeliver}"/>
                                    </c:url>
                                    <a href="${viewOrderDetail}">View</a>
                                </td>
                            </tr>
                        </c:forEach>                   
                    </tbody>
                </table>   
                <div style="width:75%;margin-top: 10px">
                    <center>
                        <button type="submit">
                            <c:if test="${isDeliver eq true}">
                                Undelivered
                            </c:if>

                            <c:if test="${isDeliver eq false}">
                                Delivered
                            </c:if>
                        </button>
                    </center>
                </div>
                
                </form>
            </c:if>
            
            <c:if test="${empty searchResult}">
                <h4>No record is matched</h4>
            </c:if>
            <br>
            <a href='searchOrder.jsp'>Back to search page</a>
            
        </c:if>
    </body>
</html>
