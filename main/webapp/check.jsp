<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.com.bukvashops.pocupon.Entities.User" %><%--
  Created by IntelliJ IDEA.
  User: mater
  Date: 20-Mar-16
  Time: 16:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ПРОВЕРКА СЕРТИФИКАТА</title>
</head>
<body>
<%@ include file="basepages/header.jsp" %>
<%--<c:if test="${!empty sessionScope.user}">
    <h3 style="text-align:center;">Пользователь: <c:out value="${sessionScope.user.getLogin()}"/></h3>
    <br>
    <h3 style="text-align:center;">Магазин: <c:out value="${sessionScope.user.getShop()}"/></h3>
    <br>
</c:if>--%>
<br>
<%--<c:if test="${!empty requestScope.message}">
    <h3 style="text-align:center;"><c:out value="${requestScope.message}"/></h3>
</c:if>--%>
<%@ include file="basepages/checkerror.jsp" %>
<br>
<form action="check" method="post" style="text-align: center">
    <strong>Сертификат</strong>:<input type="text" name="code">
    <input type="submit" value="Проверить">
</form>
<br>

</body>
</html>
