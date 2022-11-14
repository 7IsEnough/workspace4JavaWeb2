<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/14
  Time: 22:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>1</h1>
<%--jsp:include--%>


<%--http://localhost:8080/javaweb_jsp/jsptag.jsp?name=clearlove&age=12--%>

<jsp:forward page="/jsptag2.jsp">
    <jsp:param name="name" value="clearlove"/>
    <jsp:param name="age" value="12"/>
</jsp:forward>

</body>
</html>
