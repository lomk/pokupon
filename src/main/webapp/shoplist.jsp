<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="ua.com.bukvashops.pocupon.Entities.Shop" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mater
  Date: 27-Mar-16
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/admin" class="button" role="button">
    Home admin.
</a>
<br>
<% try {
    if (request.getAttribute("message").toString().isEmpty()) {
    } else {
        String message = (String) request.getAttribute("message");
%>
<h3><%=message%></h3>
<br/>
<%
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<%
    List<Shop> shops = (List<Shop>) request.getAttribute("ShopList");
%>
<table border="1">
    <tr>
        <th>id</th>
        <th>Name</th>
    </tr>
    <%for (Shop shop : shops){%>
    <tr>
        <td><%=shop.getId()%></td>
        <td><%=shop.getName()%></td>
    </tr>
    <%}%>
</table>
<br/>
</body>
</html>
