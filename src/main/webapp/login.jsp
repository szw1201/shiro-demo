<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2018/5/25
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h1>登录</h1>
    <font color="red">${errorMsg}</font>
    <form href="/login" method="post">
        账号：<input type="text" name="username" /><br><br>
        密码：<input type="password" name="password" /><br>
        <input type="submit" />
    </form>
</body>
</html>
