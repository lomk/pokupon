<%@ page import="ua.com.bukvashops.pocupon.Entities.User" %>
<%@ page import="ua.com.bukvashops.pocupon.Entities.Certificate" %><%--
  Created by IntelliJ IDEA.
  User: mater
  Date: 20-Mar-16
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%User user = (User) session.getAttribute("user"); %>
<h3 style="text-align:center;">Пользователь:<%=user.getLogin() %></h3>
<br>
<h3 style="text-align:center;">Магазин:<%=user.getShop() %></h3>
<br>
<br>
<% Certificate certificate = (Certificate) request.getAttribute("certificate");%>
<%session.setAttribute("certificate", certificate);%>
<br>
<%certificate.getCode();%>
<h3 style="text-align:center;">Сертификат актуален!</h3>
<br>

<a style="text-align:center;" href="/usecertificate" class="button" role="button">
    <h2>Использовать сертификат</h2>
</a>
<br>
<br>
<a style="text-align:center;" href="/check.jsp" class="button" role="button">
    <h2>Отмена!</h2>
</a>
</body>
</html>
