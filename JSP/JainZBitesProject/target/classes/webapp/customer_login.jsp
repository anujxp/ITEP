<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Login | JainZBites</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #ffffff;
    margin: 0;
    padding: 0;
}

.login-box {
    width: 450px;
    margin: 80px auto;
    padding: 35px;
    border-radius: 15px;
    border: 2px solid #d90429;
    background: #fff;
    box-shadow: 0px 0px 12px rgba(0,0,0,0.1);
}

.login-box h2 {
    text-align: center;
    color: #d90429;
    margin-bottom: 25px;
    font-size: 28px;
}

input {
    width: 95%;
    padding: 12px;
    margin: 12px 0;
    border: 1px solid #ccc;
    border-radius: 10px;
    font-size: 16px;
}

.btn {
    width: 100%;
    padding: 12px;
    background: #d90429;
    color: white;
    border: none;
    border-radius: 10px;
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

<div class="login-box">

    <h2>Customer Login</h2>

    <!-- Show error if incorrect login -->
    <%
        String msg = request.getParameter("msg");
        if("invalid".equals(msg)){
    %>
        <p class="error">Invalid Email or Password!</p>
    <%
        }
    %>

    <form action="customer_login.jsp" method="post">
        <input type="email" name="email" placeholder="Enter Customer Email">
        <input type="password" name="password" placeholder="Enter Password">

        <button class="btn">Login</button>
    </form>

</div>

</body>
</html>
