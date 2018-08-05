<%-- 
    Document   : register
    Created on : Mar 12, 2018, 10:36:28 AM
    Author     : DEll
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
    </head>
    <body>
        <h4>Register Form</h4>
        <form action="register" method="POST">
            <c:set var="error" value="${requestScope.error}"/>
            <table border="0">
                <tbody>
                    <tr>
                        <td>ID:</td>
                        <td>
                            <input type="text" name="username" value=""/>
                            <c:if test="${not empty error}">
                                <c:if test="${not empty error.getUserIdLength()}">
                                    <span style="color:red">
                                        ${error.getUserIdLength()}
                                    </span>
                                </c:if>
                                <c:if test="${not empty error.getDuplicateId()}">
                                    <span style="color:red">
                                        ${error.getDuplicateId()}
                                    </span>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td>
                            <input type="password" name="password" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getPasswordLength()}">
                                    <span style="color:red">
                                        ${error.getPasswordLength()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your name: </td>
                        <td>
                            <input type="text" name="custName" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getNameLength()}">
                                    <span style="color:red">
                                        ${error.getNameLength()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your last name: </td>
                        <td>
                            <input type="text" name="lastname" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getLastNameLength()}">
                                    <span style="color:red">
                                        ${error.getLastNameLength()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your middle name: </td>
                        <td>
                            <input type="text" name="middlename" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getMiddleNameLength()}">
                                    <span style="color:red">
                                            ${error.getMiddleNameLength()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your address:</td>
                        <td>
                            <input type="text" name="address" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getAddressLength()}">
                                    <span style="color:red">
                                        ${error.getAddressLength()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td>Your phone number: </td>
                        <td>
                            <input type="text" name="phonenum" value=""/>
                            <c:if test="${not empty error}">
                               <c:if test="${not empty error.getPhoneNumber()}">
                                    <span style="color:red">
                                        ${error.getPhoneNumber()}
                                    </span>
                                </c:if> 
                            </c:if>
                        </td>
                    </tr>
                </tbody>
            </table>
            <button type="submit">Register</button>
        </form>
        <a href="login.html">Go to login page</a>
    </body>
</html>
