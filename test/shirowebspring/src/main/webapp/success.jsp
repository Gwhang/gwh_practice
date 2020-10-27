<%--
  Created by IntelliJ IDEA.
  User: 49841
  Date: 2020/10/14
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--shiro标签--%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
success
<shiro:principal></shiro:principal>,欢迎您！！！！！！
<shiro:authenticated>success</shiro:authenticated>
<shiro:guest>guest</shiro:guest>
</body>
</html>

