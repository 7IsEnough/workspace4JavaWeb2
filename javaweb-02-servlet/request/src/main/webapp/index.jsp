<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/9/19
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <div>
        <%--这里表单的意思是：以post方式提交表单，提交到我们的login请求--%>
        <form action="${pageContext.request.contextPath}/login" method="post">
            用户名：<input type="text" name="username"> <br>
            密码：<input type="password" name="password"> <br>
            爱好：
            <input type="checkbox" name="hobbies" value="女孩">女孩
            <input type="checkbox" name="hobbies" value="代码">代码
            <input type="checkbox" name="hobbies" value="唱歌">唱歌
            <input type="checkbox" name="hobbies" value="电影">电影
            <br>
            <input type="submit">
        </form>
    </div>

</body>
</html>
