<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Admin Login - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<style>
    /* Full Page Background - Updated Theme: Modern City Night */
    body {
        /* High-quality City Night View - Fits Real Estate/Admin Theme */
        background: url('https://images.unsplash.com/photo-1477959858617-67f85cf4f1df?auto=format&fit=crop&w=1920&q=80');
        background-size: cover;
        background-position: center;
        background-repeat: no-repeat;
        height: 100vh;
        display: flex;
        align-items: center;
        justify-content: center;
        font-family: 'Poppins', sans-serif;
    }

    /* The Glass Box */
    .glass-card {
        background: rgba(0, 0, 0, 0.4); /* Darker transparent background for better readability */
        backdrop-filter: blur(10px);     /* The Blur Effect */
        -webkit-backdrop-filter: blur(10px);
        border-radius: 20px;
        border: 1px solid rgba(255, 255, 255, 0.1); 
        box-shadow: 0 25px 45px rgba(0, 0, 0, 0.2); 
        padding: 50px 40px;
        width: 100%;
        max-width: 400px;
        color: white;
    }

    /* Input Fields (Transparent Style) */
    .glass-input {
        background: rgba(255, 255, 255, 0.1);
        border: 1px solid rgba(255, 255, 255, 0.2);
        border-radius: 50px;
        height: 50px;
        padding-left: 20px;
        color: white;
        margin-bottom: 20px;
        transition: 0.3s;
    }
    
    .glass-input::placeholder {
        color: rgba(255, 255, 255, 0.7);
    }

    .glass-input:focus {
        background: rgba(255, 255, 255, 0.2);
        border-color: rgba(255, 255, 255, 0.5);
        box-shadow: none;
        color: white;
        outline: none;
    }

    /* Login Button */
    .btn-glass {
        width: 100%;
        height: 50px;
        border-radius: 50px;
        background: white;
        color: #333;
        font-weight: bold;
        border: none;
        font-size: 1.1rem;
        transition: 0.3s;
        margin-top: 10px;
        text-transform: uppercase;
        letter-spacing: 1px;
    }

    .btn-glass:hover {
        background: #f8f9fa;
        transform: scale(1.02);
        box-shadow: 0 0 15px rgba(255, 255, 255, 0.5);
    }

    /* Headings */
    h3 {
        font-weight: 600;
        letter-spacing: 1px;
        margin-bottom: 30px;
    }
    
    .logo-glow {
        text-shadow: 0 0 10px rgba(255,255,255,0.5);
    }
</style>
</head>
<body>

    <div class="glass-card text-center">
        <div class="mb-4 logo-glow">
            <i class="fa-solid fa-city fa-3x"></i> 
        </div>
        <h3>Admin Console</h3>

        <%
            String error = (String) session.getAttribute("errorMessage");
            if (error != null) {
        %>
            <div class="alert alert-danger" style="background: rgba(220, 53, 69, 0.6); border: none; color: white; border-radius: 30px;">
                <small><i class="fa-solid fa-triangle-exclamation mr-2"></i> <%= error %></small>
            </div>
        <%
            session.removeAttribute("errorMessage");
            }
        %>

        <form action="controller/admin_login.jsp" method="post">
            
            <input type="email" name="email" class="form-control glass-input" placeholder="Admin ID" required autocomplete="off">
            
            <input type="password" name="password" class="form-control glass-input" placeholder="Password" required>
            
            <button type="submit" class="btn btn-glass">
                Secure Login
            </button>

        </form>
        
        <div class="mt-4">
            <a href="index.jsp" class="text-white small" style="text-decoration: none; opacity: 0.7; transition: 0.3s;" onmouseover="this.style.opacity='1'" onmouseout="this.style.opacity='0.7'">
                <i class="fa-solid fa-arrow-left mr-1"></i> Back to Website
            </a>
        </div>
    </div>

</body>
</html>