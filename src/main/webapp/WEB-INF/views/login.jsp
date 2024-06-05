<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 24. 5. 14.
  Time: 오전 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>

<div class="login-box">
    <h2>Login</h2>
    <!-- form에 id 추가 -->
    <form id="loginForm" action="api/user/login" method="post">
        <div class="user-box">
            <input type="text" name="id" required="">
            <label>Username</label>
        </div>
        <div class="user-box">
            <input type="password" name="pwd" required="">
            <label>Password</label>
        </div>
        <!-- onclick 이벤트 추가 -->
        <a onclick="document.getElementById('loginForm').submit();">
            <span></span>
            <span></span>
            <span></span>
            <span></span>
            Submit
        </a>
        <!-- visibility: hidden 대신 type="submit"만 사용 -->
        <input type="submit" value="Login" style="visibility: hidden;"/>
    </form>
</div>

</body>
</html>
