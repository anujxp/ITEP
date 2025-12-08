<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>User Login - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<style>
    .login-section {
        min-height: 85vh; /* Increased height slightly for better vertical centering */
        display: flex;
        align-items: center;
        padding: 40px 0; /* Added vertical padding for small screens */
    }
    .login-card {
        border: none;
        border-radius: 20px;
        box-shadow: 0 15px 35px rgba(0,0,0,0.1);
        overflow: hidden; 
    }
    .login-image {
        background: url('https://images.unsplash.com/photo-1555854877-bab0e564b8d5?auto=format&fit=crop&w=800&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100%;
    }
    .login-form-wrapper {
        padding: 40px 30px; /* Reduced padding slightly to make it compact */
        background: white;
    }
    .form-control {
        border-radius: 50px; 
        padding-left: 20px;
        height: 45px; /* Slightly smaller input height */
        background-color: #f8f9fa;
        border: 1px solid #eee;
        font-size: 0.9rem;
    }
    .form-control:focus {
        background-color: #fff;
        border-color: #ffc107; 
        box-shadow: none;
    }
    .btn-login {
        border-radius: 50px;
        height: 45px; /* Matches input height */
        font-weight: bold;
        font-size: 1rem;
        background: #ffc107;
        border: none;
        color: #000;
        transition: 0.3s;
    }
    .btn-login:hover {
        background: #e0a800;
        transform: translateY(-2px);
    }
</style>
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <div class="container login-section">
        <div class="row justify-content-center w-100 mx-0">
            
            <div class="col-lg-8 col-md-10">
                <div class="card login-card">
                    <div class="row no-gutters">
                        
                        <div class="col-md-5 d-none d-md-block login-image">
                            <div class="h-100 d-flex flex-column justify-content-center align-items-center text-white p-4 text-center" 
                                 style="background: rgba(0,0,0,0.4);"> 
                                <h3 class="font-weight-bold">Welcome Back!</h3>
                                <p class="small">Sign in to find your perfect home away from home.</p>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <div class="login-form-wrapper">
                                <div class="text-center mb-4">
                                    <h4 class="font-weight-bold">User Sign In</h4>
                                    <p class="text-muted small">Enter your details to continue</p>
                                </div>

                                <%
                                    String error = (String) session.getAttribute("errorMessage");
                                    String msg = (String) session.getAttribute("msg");
                                    
                                    if (error != null) {
                                %>
                                    <div class="alert alert-danger text-center rounded-pill small py-2"><i class="fa-solid fa-circle-exclamation mr-2"></i> <%=error%></div>
                                <%
                                    session.removeAttribute("errorMessage");
                                    }
                                    if (msg != null) {
                                %>
                                    <div class="alert alert-success text-center rounded-pill small py-2"><i class="fa-solid fa-check-circle mr-2"></i> <%=msg%></div>
                                <%
                                    session.removeAttribute("msg");
                                    }
                                %>

                                <form action="controller/user_login.jsp" method="post">
                                    
                                    <div class="form-group mb-3">
                                        <label class="font-weight-bold small ml-3 text-muted" style="font-size: 0.7rem;">EMAIL ADDRESS</label>
                                        <input type="email" name="email" class="form-control" placeholder="name@example.com" required>
                                    </div>
                                    
                                    <div class="form-group mb-4">
                                        <label class="font-weight-bold small ml-3 text-muted" style="font-size: 0.7rem;">PASSWORD</label>
                                        <input type="password" name="password" class="form-control" placeholder="••••••••" required>
                                    </div>

                                    <button type="submit" class="btn btn-login btn-block shadow-sm">
                                        LOGIN
                                    </button>

                                    <div class="text-center mt-4">
                                        <span class="text-muted small">Don't have an account?</span> 
                                        <a href="user_register.jsp" class="font-weight-bold text-dark ml-1 small">Create Account</a>
                                    </div>
                                    
                                    <hr class="my-3">
                                    
                                    <div class="text-center">
                                        <a href="partner_login.jsp" class="small text-info font-weight-bold">Are you a Property Owner? Login here</a>
                                    </div>
                                </form>
                            </div>
                        </div>
                        
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="bg-dark text-white text-center py-4 mt-auto">
        <div class="container">
            <p class="mb-0">&copy; 2025 Need Connect.</p>
        </div>
    </footer>

</body>
</html>