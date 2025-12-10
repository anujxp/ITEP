<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>About Us - Need Connect</title>
<%@ include file="__bootstrap_link.jsp" %>
<style>
    .about-header {
        background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('https://images.unsplash.com/photo-1521737604893-d14cc237f11d?auto=format&fit=crop&w=1920&q=80');
        background-size: cover;
        background-position: center;
        color: white;
        padding: 100px 0;
        border-radius: 0 0 50px 50px;
    }
    .team-member {
        border: none;
        box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        transition: 0.3s;
    }
    .team-member:hover { transform: translateY(-5px); }
</style>
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <div class="about-header text-center mb-5">
        <div class="container">
            <h1 class="display-4 font-weight-bold">About Need Connect</h1>
            <p class="lead">Bridging the gap between Students and Property Owners.</p>
        </div>
    </div>

    <div class="container mb-5">
        <div class="row align-items-center mb-5">
            <div class="col-md-6">
                <img src="https://images.unsplash.com/photo-1529156069898-49953e39b3ac?auto=format&fit=crop&w=800&q=80" class="img-fluid rounded shadow-lg" alt="Friends">
            </div>
            <div class="col-md-6">
                <h3 class="font-weight-bold text-primary">Our Mission</h3>
                <p class="text-muted" style="line-height: 1.8;">
                    Finding a place to stay in a new city shouldn't be hard. "Need Connect" was born out of the struggle students face when moving for education. 
                    <br><br>
                    We provide a <strong>brokerage-free</strong> platform where owners can list their properties directly, and students can find safe, verified, and affordable housing with just a few clicks.
                </p>
                <div class="row text-center mt-4">
                    <div class="col-4">
                        <h2 class="font-weight-bold">500+</h2>
                        <small>Verified Rooms</small>
                    </div>
                    <div class="col-4">
                        <h2 class="font-weight-bold">1k+</h2>
                        <small>Happy Students</small>
                    </div>
                    <div class="col-4">
                        <h2 class="font-weight-bold">0%</h2>
                        <small>Brokerage Fee</small>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@ include file="__footer.jsp" %>

</body>
</html>