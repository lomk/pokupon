<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="ua.com.bukvashops.pocupon.Entities.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <title>USERLIST</title>
</head>
<body>
<a href="/admin" class="button" role="button" style="text-align:center;">
    Home admin.
</a>
<br>
<c:if test="${!empty requestScope.message}">
    <h3 style="text-align:center;"><c:out value="${requestScope.message}"/></h3>
</c:if>
<br/>

<table border="2" style="text-align:center;">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Login</th>
        <th>Password</th>
        <th>Is Active</th>
        <th>Is Admin</th>
        <th>Shop</th>
    </tr>
<c:forEach var="user"  items="${requestScope.UserList}">
    <tr>

        <%--<td><c:out value="${user.getLogin()}"></c:out></td>--%>
        <td>${user.getFirstName()}</td>
        <%--<td><c:out value="${user.getFirstName()}"></c:out></td>--%>
        <td>${user.getLastName()}</td>
        <%--<td><c:out value="${user.getLastName()}"></c:out>></td>--%>
        <td>${user.getLogin()}</td>
        <td>${user.getPassword()}</td>
        <td>${user.isActive()}</td>
        <td>${user.isAdmin()}</td>
        <td>${user.getShop()}</td>
    </tr>
</c:forEach>

</body>
</html>