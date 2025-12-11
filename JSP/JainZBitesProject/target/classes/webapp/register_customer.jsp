<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Registration | JainZBites</title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #ffffff;
    margin: 0;
    padding: 0;
}

.register-box {
    width: 480px;
    margin: 60px auto;
    padding: 35px;
    border-radius: 15px;
    border: 2px solid #d90429;
    background: #fff;
    box-shadow: 0px 0px 12px rgba(0,0,0,0.1);
}

.register-box h2 {
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

.msg {
    text-align: center;
    color: green;
    margin-bottom: 10px;
}
.error {
    text-align: center;
    color: red;
    margin-bottom: 10px;
}
</style>

</head>
<body>

<div class="register-box">

    <h2>Customer Registration</h2>

    <!-- Success or error message -->
    <%
        String msg = request.getParameter("msg");
        if("success".equals(msg)){
    %>
        <p class="msg">Registration Successful! Please Login.</p>
    <% } else if("error".equals(msg)){ %>
        <p class="error">Registration Failed! Try Again.</p>
    <% } %>

    <form action="register_customer_action.jsp" method="post">
        
        <input type="text" name="name" placeholder="Enter Full Name" >

        <input type="email" name="email" placeholder="Enter Email Address">

        <input type="text" name="mobile" placeholder="Enter Mobile Number">

        <input type="password" name="password" placeholder="Create Password">
        
        <input type="text" name="address" placeholder="Enter address">

        <button class="btn">Register</button>
    </form>

</div>

</body>
</html>
