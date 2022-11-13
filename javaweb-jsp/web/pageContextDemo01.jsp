<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/13
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--内置对象--%>

<%
    // 保存的数据只在一个页面中有效
    pageContext.setAttribute("name1", "明凯1号");
    // 保存的数据只在一次请求中有效，请求转发会携带这个数据
    request.setAttribute("name2", "明凯2号");
    // 保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
    session.setAttribute("name3", "明凯3号");
    // 保存的数据只在服务器中有效，从打开服务器到关闭服务器
    application.setAttribute("name4", "明凯4号");
%>

<%--脚本片段生成的代码，会被原封不动生成到.jsp.java
    要求：这里面的代码：必须保证Java语法的正确性
--%>

<%
    // 从pageContext取出，通过寻找的方式来
    // 从底层到高层（作用域）：page -> request -> session -> application
    // 双亲委派机制
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    // 不存在
    String name5 = (String) pageContext.findAttribute("name5");
%>

<%--使用EL表达式输出 ${}--%>

<h1>取出的值为：</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3>${name5}</h3>


</body>
</html>
