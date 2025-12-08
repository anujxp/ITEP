<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Student Registration - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<style>
    .register-section {
        min-height: 90vh;
        display: flex;
        align-items: center;
        padding: 40px 0;
    }
    .register-card {
        border: none;
        border-radius: 20px;
        box-shadow: 0 15px 35px rgba(0,0,0,0.1);
        overflow: hidden;
    }
    .register-image {
        background: url('https://images.unsplash.com/photo-1523240795612-9a054b0db644?auto=format&fit=crop&w=800&q=80');
        background-size: cover;
        background-position: center;
        min-height: 100%;
    }
    .form-wrapper {
        padding: 40px 30px;
        background: white;
    }
    .form-control {
        border-radius: 50px;
        padding-left: 20px;
        height: 45px;
        background-color: #f8f9fa;
        border: 1px solid #eee;
        font-size: 0.9rem;
    }
    .form-control:focus {
        background-color: #fff;
        border-color: #ffc107; /* User Theme */
        box-shadow: none;
    }
    .btn-register {
        border-radius: 50px;
        height: 45px;
        font-weight: bold;
        background: #ffc107;
        border: none;
        color: #000;
        transition: 0.3s;
    }
    .btn-register:hover {
        background: #e0a800;
        transform: translateY(-2px);
    }
</style>
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <div class="container register-section">
        <div class="row justify-content-center w-100 mx-0">
            <div class="col-lg-9 col-md-11">
                <div class="card register-card">
                    <div class="row no-gutters">
                        
                        <div class="col-md-5 d-none d-md-block register-image">
                            <div class="h-100 d-flex flex-column justify-content-center align-items-center text-white p-4 text-center" 
                                 style="background: rgba(0,0,0,0.5);">
                                <h3 class="font-weight-bold">Join Us!</h3>
                                <p class="small">Create an account to browse thousands of verified rooms and connect with owners directly.</p>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <div class="form-wrapper">
                                <div class="text-center mb-4">
                                    <h4 class="font-weight-bold">Create Account</h4>
                                    <p class="text-muted small">Student / Tenant Registration</p>
                                </div>

                                <%
                                    String error = (String) session.getAttribute("errorMessage");
                                    if (error != null) {
                                %>
                                    <div class="alert alert-danger text-center rounded-pill small py-2">
                                        <%= error %>
                                    </div>
                                <%
                                    session.removeAttribute("errorMessage");
                                    }
                                %>

                                <form action="controller/user_register.jsp" method="post">
                                    
                                    <div class="form-group mb-3">
                                        <input type="text" name="name" class="form-control" placeholder="Full Name" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="email" name="email" class="form-control" placeholder="Email Address" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="tel" name="phone" class="form-control" placeholder="Phone Number" pattern="[0-9]{10}" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="password" name="password" class="form-control" placeholder="Password" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="text" name="city" class="form-control" placeholder="Current City" required>
                                    </div>

                                    <div class="form-row mb-4">
                                        <div class="col">
                                            <input type="number" name="age" class="form-control" placeholder="Age" min="16" max="89" required>
                                        </div>
                                        <div class="col">
                                            <select name="gender" class="form-control" required style="padding-left: 15px;">
                                                <option value="" disabled selected>Gender</option>
                                                <option value="Male">Male</option>
                                                <option value="Female">Female</option>
                                                <option value="Other">Other</option>
                                            </select>
                                        </div>
                                    </div>

                                    <button type="submit" class="btn btn-register btn-block shadow-sm">
                                        REGISTER
                                    </button>

                                    <div class="text-center mt-4">
                                        <span class="text-muted small">Already have an account?</span> 
                                        <a href="login.jsp" class="font-weight-bold text-dark ml-1 small">Login</a>
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