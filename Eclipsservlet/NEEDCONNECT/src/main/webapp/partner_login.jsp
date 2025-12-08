<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Partner Login - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<style>
    body {
        /* Subtle Blue Tint for Partner Pages */
        background-color: #f0f8ff; 
    }
    .login-section {
        min-height: 85vh;
        display: flex;
        align-items: center;
        padding: 40px 0;
    }
    .login-card {
        border: none;
        border-radius: 20px;
        box-shadow: 0 15px 35px rgba(0,0,0,0.1);
        overflow: hidden;
    }
    .partner-image {
        /* Professional Building / Keys Image */
        background: url('https://images.unsplash.com/photo-1560518883-ce09059eeffa?auto=format&fit=crop&w=800&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100%;
        position: relative;
    }
    .image-overlay {
        background: rgba(13, 110, 253, 0.6); /* Blue Overlay */
        position: absolute;
        top: 0; left: 0; right: 0; bottom: 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: white;
        padding: 20px;
    }
    .form-wrapper {
        padding: 50px 40px;
        background: white;
    }
    .form-control {
        border-radius: 50px;
        padding-left: 20px;
        height: 45px;
        background-color: #f8f9fa;
        border: 1px solid #e9ecef;
        font-size: 0.9rem;
    }
    .form-control:focus {
        background-color: #fff;
        border-color: #0dcaf0; /* Partner Cyan */
        box-shadow: 0 0 0 0.2rem rgba(13, 202, 240, 0.25);
    }
    .btn-partner {
        border-radius: 50px;
        height: 45px;
        font-weight: bold;
        /* Blue Gradient */
        background: linear-gradient(45deg, #0d6efd, #0dcaf0); 
        border: none;
        color: white;
        transition: 0.3s;
    }
    .btn-partner:hover {
        background: linear-gradient(45deg, #0b5ed7, #0aa2c0);
        transform: translateY(-2px);
        color: white;
    }
    .text-partner {
        color: #0d6efd;
    }
</style>
</head>
<body class="d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <div class="container login-section">
        <div class="row justify-content-center w-100 mx-0">
            <div class="col-lg-9 col-md-11">
                <div class="card login-card">
                    <div class="row no-gutters">
                        
                        <div class="col-md-5 d-none d-md-block partner-image">
                            <div class="image-overlay text-center">
                                <h3 class="font-weight-bold">Partner Portal</h3>
                                <p class="small">Manage your properties and connect with tenants efficiently.</p>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <div class="form-wrapper">
                                <div class="text-center mb-4">
                                    <h4 class="font-weight-bold text-partner">Welcome Back</h4>
                                    <p class="text-muted small">Login to access your dashboard</p>
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

                                <form action="controller/partner_login.jsp" method="post">
                                    
                                    <div class="form-group mb-3">
                                        <label class="font-weight-bold small ml-3 text-muted">EMAIL</label>
                                        <input type="email" name="email" class="form-control" placeholder="partner@example.com" required>
                                    </div>

                                    <div class="form-group mb-4">
                                        <label class="font-weight-bold small ml-3 text-muted">PASSWORD</label>
                                        <input type="password" name="password" class="form-control" placeholder="••••••••" required>
                                    </div>

                                    <button type="submit" class="btn btn-partner btn-block shadow-sm">
                                        SECURE LOGIN
                                    </button>

                                    <div class="text-center mt-4">
                                        <span class="text-muted small">Don't have a partner account?</span> 
                                        <a href="partner_register.jsp" class="font-weight-bold text-partner ml-1 small">Register Now</a>
                                    </div>
                                    
                                    <hr class="my-3">
                                    
                                    <div class="text-center">
                                        <a href="login.jsp" class="small text-secondary">Looking for a room? Student Login</a>
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