<%-- 
    Document   : searchOrder
    Created on : Mar 12, 2018, 10:45:22 AM
    Author     : DEll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search order Page</title>
    </head>
    <body>
        <c:set var="user" value="${sessionScope.user}"/>
        
        <c:if test="${empty user}">
            <h4>You have to log in first</h4>
            <a href="login.html">Go to login page</a>
        </c:if>
            
        <c:if test="${not empty user}">
            <h4>Welcome, ${user}</h4>
            <h4>Search Order</h4>
            
            <div style="position:absolute; top:20px;right:20px">
                <a href="logout">logout</a>
            </div>
            <br>
            
            <form action="searchOrder">
                <c:set var="error" value="${requestScope.error}"/>
                <table border="0">
                <tbody>
                    <tr>
                        <td>From Date: </td>
                        <td>
                            <input type="text" name="fromDate"
                                   placeholder="dd/mm/yyyy"/>
                            <c:if test="${not empty error}">
                                <c:if test="${not empty error.getDateError()}">
                                    <span style="color:red">${error.getDateError()}</span>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>To Date: </td>
                        <td>
                            <input type="text" name="toDate"
                                   placeholder="dd/mm/yyyy"/>
                            <c:if test="${not empty error}">
                                <c:if test="${not empty error.getDateError()}">
                                    <span style="color:red">${error.getDateError()}</span>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <input type="checkbox" name="isDelivered">
                            &nbsp;Delivered
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <button type="submit">Search</button>
                            &nbsp;
                            <button type="reset">Reset</button>
                        </td>
                    </tr>
                </tbody>
            </table>
            </form>
        </c:if>
    </body>
</html>
