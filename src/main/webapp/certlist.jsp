<%@ page import="ua.com.bukvashops.pocupon.Entities.Certificate" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: mater
  Date: 27-Mar-16
  Time: 12:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CERTIFICATES</title>
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
<%
        }
    }catch (Exception e){
        e.printStackTrace();
    }
%>
<%
    List<Certificate> certs = (List<Certificate>) request.getAttribute("CertList");
%>

<table border="1">
    <tr>
        <th>Code</th>
        <th>IsActive</th>
        <th>UsageDate</th>
        <th>Comment</th>
        <th>Used By</th>
    </tr>
    <%for (Certificate cert : certs){%>
    <tr>
        <td><%=cert.getCode()%></td>
        <td><%=cert.isActive()%></td>
        <td><%=cert.getDateOfApplication()%></td>
        <td><%=cert.getComment()%></td>
        <td><%=cert.getUsedBy()%></td>
    </tr>
    <%}%>
</table>

<br/>

</body>
</html>
