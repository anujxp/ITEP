<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Partner Registration - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<style>
    body {
        background-color: #f0f8ff; 
    }
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
    .partner-reg-image {
        background: url('https://images.unsplash.com/photo-1556761175-5973dc0f32e7?auto=format&fit=crop&w=800&q=80'); /* Handshake/Business */
        background-size: cover;
        background-position: center;
        min-height: 100%;
        position: relative;
    }
    .image-overlay {
        background: rgba(13, 202, 240, 0.7); /* Cyan Overlay */
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
        padding: 40px 30px;
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
        border-color: #0dcaf0;
        box-shadow: 0 0 0 0.2rem rgba(13, 202, 240, 0.25);
    }
    .btn-partner {
        border-radius: 50px;
        height: 45px;
        font-weight: bold;
        background: linear-gradient(45deg, #0dcaf0, #0d6efd); 
        border: none;
        color: white;
        transition: 0.3s;
    }
    .btn-partner:hover {
        background: linear-gradient(45deg, #0aa2c0, #0b5ed7);
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

    <div class="container register-section">
        <div class="row justify-content-center w-100 mx-0">
            <div class="col-lg-9 col-md-11">
                <div class="card register-card">
                    <div class="row no-gutters">
                        
                        <div class="col-md-5 d-none d-md-block partner-reg-image">
                            <div class="image-overlay text-center">
                                <h3 class="font-weight-bold">Grow Business</h3>
                                <p class="small">List your rooms and reach thousands of students instantly.</p>
                            </div>
                        </div>

                        <div class="col-md-7">
                            <div class="form-wrapper">
                                <div class="text-center mb-4">
                                    <h4 class="font-weight-bold text-partner">Become a Partner</h4>
                                    <p class="text-muted small">Property Owner Registration</p>
                                </div>

                                <%
                                    String error = (String) session.getAttribute("errorMessage");
                                    if (error != null) {
                                %>
                                    <div class="alert alert-danger text-center rounded-pill small py-2"><%=error%></div>
                                <%
                                    session.removeAttribute("errorMessage");
                                    }
                                %>

                                <form action="controller/partner_register.jsp" method="post">
                                    
                                    <div class="form-group mb-3">
                                        <input type="text" name="name" class="form-control" placeholder="Full Name" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="email" name="email" class="form-control" placeholder="Email Address" required>
                                    </div>

                                    <div class="form-group mb-3">
                                        <input type="tel" name="phone" class="form-control" placeholder="Phone (10 Digits)" pattern="[0-9]{10}" required>
                                    </div>

                                    <div class="form-group mb-4">
                                        <input type="password" name="password" class="form-control" placeholder="Password" required>
                                    </div>

                                    <button type="submit" class="btn btn-partner btn-block shadow-sm">
                                        REGISTER AS PARTNER
                                    </button>

                                    <div class="text-center mt-4">
                                        <span class="text-muted small">Already a partner?</span> 
                                        <a href="partner_login.jsp" class="font-weight-bold text-partner ml-1 small">Login Here</a>
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