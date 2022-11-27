<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/26
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登录</h1>
    <form action="${pageContext.request.contextPath}/servlet/login" method="post">
        <input type="text" name="username"/>
        <input type="submit">
    </form>
</body>
</html>
