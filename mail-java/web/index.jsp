<%--
  Created by IntelliJ IDEA.
  User: 陈振寰
  Date: 2023/3/1
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
    <form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
      用户名：<input type="text" name="username"><br/>
      密码：<input type="password" name="password"><br/>
      邮箱：<input type="email" name="email"><br/>
      <input type="submit" value="注册">
    </form>
  </body>
</html>
