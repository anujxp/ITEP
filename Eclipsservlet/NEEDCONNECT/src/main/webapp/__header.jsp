<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.User"%>

<style>
    /* Custom Header Styling */
    .navbar-custom {
        /* Deep Blue/Purple Gradient - Professional & Trustworthy */
        background: linear-gradient(135deg, #1e3c72 0%, #2a5298 100%);
        box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        padding: 15px 0; /* More vertical space */
        transition: all 0.3s;
    }

    .navbar-brand {
        font-family: 'Poppins', sans-serif;
        font-weight: 700;
        letter-spacing: 1px;
        font-size: 1.5rem;
    }

    .nav-link {
        font-family: 'Poppins', sans-serif;
        font-weight: 500;
        font-size: 0.95rem;
        margin-left: 15px;
        position: relative;
        color: rgba(255,255,255,0.9) !important;
        transition: 0.3s;
    }

    /* Hover Underline Animation */
    .nav-link::after {
        content: '';
        position: absolute;
        width: 0;
        height: 2px;
        bottom: 0;
        left: 0;
        background-color: #ffc107; /* Warning Yellow */
        transition: width 0.3s ease-in-out;
    }

    .nav-link:hover::after {
        width: 100%;
    }

    /* Active Link State */
    .nav-item.active .nav-link::after {
        width: 100%;
    }

    /* User/Partner Badges */
    .user-badge {
        background: rgba(255,255,255,0.15);
        padding: 8px 20px;
        border-radius: 30px;
        backdrop-filter: blur(5px);
        border: 1px solid rgba(255,255,255,0.2);
    }

    /* Dropdown Menu Polish */
    .dropdown-menu {
        border: none;
        border-radius: 15px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.15);
        margin-top: 15px;
        overflow: hidden;
    }
    .dropdown-item {
        padding: 10px 20px;
        transition: 0.2s;
    }
    .dropdown-item:hover {
        background-color: #f8f9fa;
        padding-left: 25px; /* Slide effect */
    }

    /* Login/Register Buttons */
    .btn-header {
        border-radius: 50px;
        padding: 8px 25px;
        font-weight: 600;
        font-size: 0.9rem;
        transition: 0.3s;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    .btn-header:hover {
        transform: translateY(-2px);
        box-shadow: 0 6px 12px rgba(0,0,0,0.15);
    }
</style>

<nav class="navbar navbar-expand-lg navbar-dark navbar-custom sticky-top">
  <div class="container">
    
    <a class="navbar-brand" href="index.jsp">
        <i class="fa-solid fa-building-user text-warning mr-2"></i>NEED CONNECT
    </a>

    <button class="navbar-toggler border-0" type="button" data-toggle="collapse" data-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto align-items-center">
        
        <li class="nav-item">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>

        <%
            Partner navPartner = (Partner) session.getAttribute("currentPartner");
            User navUser = (User) session.getAttribute("currentUser");

            if (navPartner != null) { 
                // ==========================
                // PARTNER VIEW
                // ==========================
        %>
            <li class="nav-item">
                <a class="nav-link" href="add_listing.jsp"><i class="fa-solid fa-plus-circle mr-1"></i> Post Room</a>
            </li>
            
            <li class="nav-item dropdown ml-3">
                <a class="nav-link dropdown-toggle user-badge text-white" href="#" id="pDrop" role="button" data-toggle="dropdown">
                    <i class="fa-solid fa-briefcase mr-2"></i> <%= navPartner.getName() %>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <h6 class="dropdown-header">Partner Controls</h6>
                    <a class="dropdown-item" href="partner_dashboard.jsp"><i class="fa-solid fa-gauge mr-2 text-info"></i> Dashboard</a>
                    
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="controller/logout.jsp"><i class="fa-solid fa-power-off mr-2"></i> Logout</a>
                </div>
            </li>

        <% } else if (navUser != null) { 
                // ==========================
                // STUDENT VIEW
                // ==========================
        %>
            <li class="nav-item">
                <a class="nav-link" href="view_listings.jsp"><i class="fa-solid fa-search mr-1"></i> Find Room</a>
            </li>
            
            <li class="nav-item dropdown ml-3">
                <a class="nav-link dropdown-toggle user-badge text-warning" href="#" id="uDrop" role="button" data-toggle="dropdown" style="border-color: rgba(255, 193, 7, 0.5);">
                    <i class="fa-solid fa-user-graduate mr-2"></i> <%= navUser.getName() %>
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <h6 class="dropdown-header">Student Account</h6>
                    <a class="dropdown-item" href="my_bookings.jsp"><i class="fa-solid fa-calendar-check mr-2 text-success"></i> My Bookings</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="controller/logout.jsp"><i class="fa-solid fa-power-off mr-2"></i> Logout</a>
                </div>
            </li>

        <% } else { 
                // ==========================
                // GUEST VIEW
                // ==========================
        %>
            <li class="nav-item">
                <a class="nav-link" href="view_listings.jsp">Browse Rooms</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about.jsp">About</a>
            </li>
            
            <li class="nav-item mx-2 d-none d-lg-block text-white-50">|</li>

            <li class="nav-item">
                <a class="btn btn-header btn-outline-light ml-2" href="login.jsp">Login</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-header btn-warning ml-2 text-dark" href="partner_register.jsp">List Property</a>
            </li>
        <% } %>

      </ul>
    </div>
  </div>
</nav>