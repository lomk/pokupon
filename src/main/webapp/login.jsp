<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 18-Mar-16
  Time: 13:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>

<%--<c:if test="${!empty requestScope.message}">
    <h3 style="text-align:center;"><c:out value="${requestScope.message}"/></h3>
</c:if>--%>
<%@ include file="basepages/checkerror.jsp" %>
<form action="login" method="post" style="text-align:center;">
    <strong>Логин :   </strong><input type="text" name="login"><br>
    <br>
    <strong>Пароль:  </strong><input type="password" name="password"><br>
    <br>
    <input type="submit" value="  OK  ">
</form>
</body>
</html>
