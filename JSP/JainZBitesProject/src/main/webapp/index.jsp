<%@page import="com.info.jainzbitesproject.util.*"%>
<%@page import="jakarta.persistence.EntityManagerFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<% JPAUtil.getFactory(); %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JainZBites Web </title>

<style>
body {
    font-family: Arial, sans-serif;
    background-color: #ffffff;
    margin: 0;
    padding: 0;
    text-align: center;
}

.header {
    background-color: #d90429; /* red */
    padding: 25px;
    color: #fff;
    font-size: 32px;
    font-weight: bold;
    letter-spacing: 2px;
}

.card {
    margin: 80px auto;
    width: 50%;
    padding: 40px;
    background: #fff;
    border-radius: 12px;
    border: 2px solid #d90429;
    box-shadow: 0px 0px 12px rgba(0,0,0,0.1);
}

.btn {
    display: block;
    width: 70%;
    margin: 15px auto;
    padding: 15px;
    background: #d90429;
    color: white;
    border: none;
    text-decoration: none;
    font-size: 20px;
    border-radius: 10px;
    cursor: pointer;
}

.btn:hover {
    background: #b80322;
}
</style>
</head>
<body>

<div class="header">JainZBites</div>

<div class="card">
    <h2>Welcome to JainZBites</h2>
    <p>Select where you want to go</p>

    <a href="admin_login.jsp" class="btn">Admin Login</a>
    <a href="customer_login.jsp" class="btn">Customer Login</a>
    <a href="register_customer.jsp" class="btn">Customer Register</a>
</div>

</body>
</html>
