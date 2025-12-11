<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome to Admin Login </title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #ffffff;
    margin: 0;
    padding: 0;
}

.header {
    background-color: #d90429;
    padding: 20px;
    color: #fff;
    font-size: 30px;
    text-align: center;
    font-weight: bold;
}

.login-box {
    width: 420px;
    margin: 80px auto;
    padding: 35px;
    border-radius: 12px;
    border: 2px solid #d90429;
    background: #fff;
    box-shadow: 0px 0px 12px rgba(0,0,0,0.1);
}

.login-box h2 {
    text-align: center;
    color: #d90429;
    margin-bottom: 25px;
}

input {
    width: 95%;
    padding: 12px;
    margin: 12px 0;
    border: 1px solid #aaa;
    border-radius: 8px;
    font-size: 16px;
}

.btn {
    width: 100%;
    padding: 12px;
    background: #d90429;
    color: white;
    border: none;
    border-radius: 8px;
    font-size: 18px;
    cursor: pointer;
    margin-top: 10px;
}

.btn:hover {
    background: #b80322;
}

.error {
    color: red;
    text-align: center;
    margin-bottom: 10px;
}

</style>
</head>
<body>

<div class="header">JainZBites Admin Panel</div>

<div class="login-box">

    <h2>Admin Login</h2>
    <%
        String msg = request.getParameter("msg");
        if("invalid".equals(msg)){
    %>
        <p class="error">Invalid Email or Password!</p>
    <%
        }
    %>

    <form action="controller/admin_login.jsp" method="post">
        <input type="email" name="email" placeholder="Enter Admin Email">
        <input type="password" name="password" placeholder="Enter Password">

        <button class="btn" type="submit">Login</button>
    </form>

</div>

</body>
</html>
