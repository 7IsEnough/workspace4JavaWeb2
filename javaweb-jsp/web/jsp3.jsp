<%--
  Created by IntelliJ IDEA.
  User: 97694
  Date: 2022/11/12
  Time: 16:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--@include 会将两个页面合二为一--%>

<%@ include file="common/header.jsp"%>
<h1>网页主体</h1>
<%@ include file="common/footer.jsp"%>


<hr>

<%--JSP标签
    jsp：include 拼接页面，本质还是三个
--%>
<jsp:include page="/common/header.jsp"/>
<h1>网页主体</h1>
<jsp:include page="/common/footer.jsp"/>

</body>
</html>
