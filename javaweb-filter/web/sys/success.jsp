<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/26
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<%--%>
<%--    Object userSession = request.getSession().getAttribute("USER_SESSION");--%>
<%--    if (userSession == null) {--%>
<%--        response.sendRedirect(request.getContextPath() + "/Login.jsp");--%>
<%--    }--%>
<%--%>--%>

    <h1>主页</h1>
<p>
    <a href="${pageContext.request.contextPath}/servlet/logout">注销</a>
</p>


</body>
</html>
