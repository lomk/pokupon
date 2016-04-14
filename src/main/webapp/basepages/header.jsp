<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 08-Apr-16
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>
<body>--%>
<c:if test="${!empty sessionScope.user}">
    <h3 style="text-align:center;">Пользователь: <c:out value="${sessionScope.user.getLogin()}"/></h3>
    <br>
    <h3 style="text-align:center;">Магазин: <c:out value="${sessionScope.user.getShop()}"/></h3>
    <br>
</c:if>
<%--
</html>
</body>
--%>
