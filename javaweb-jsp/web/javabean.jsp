<%@ page import="com.clearlove.pojo.People" %><%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/16
  Time: 0:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
//    People people = new People();
//    people.setAddress("西安");
//    people.setId();
//    people.setAge();
//    people.setName();
%>

<jsp:useBean id="people" class="com.clearlove.pojo.People" scope="page"></jsp:useBean>

<jsp:setProperty name="people" property="address" value="西安"></jsp:setProperty>
<jsp:setProperty name="people" property="id" value="1"></jsp:setProperty>
<jsp:setProperty name="people" property="age" value="3"></jsp:setProperty>
<jsp:setProperty name="people" property="name" value="明凯"></jsp:setProperty>

姓名：<jsp:getProperty name="people" property="name"/>
ID：<jsp:getProperty name="people" property="id"/>
年龄：<jsp:getProperty name="people" property="age"/>
地址：<jsp:getProperty name="people" property="address"/>

</body>
</html>
