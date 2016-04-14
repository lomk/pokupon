<%--
  Created by IntelliJ IDEA.
  User: Igor
  Date: 08-Apr-16
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<html>
<body>--%>
<c:if test="${!empty requestScope.message}">
    <h3 style="text-align:center;"><c:out value="${requestScope.message}"/></h3>
</c:if>
<%--
</body>
</html>
--%>
