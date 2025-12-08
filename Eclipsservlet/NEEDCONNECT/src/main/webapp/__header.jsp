<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.User"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark shadow-sm">
  <div class="container">
    
    <a class="navbar-brand font-weight-bold" href="index.jsp">
        <i class="fa-solid fa-building-user text-warning"></i> Need Connect
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav ml-auto align-items-center">
        
        <li class="nav-item active">
          <a class="nav-link" href="index.jsp">Home</a>
        </li>

        <%
            Partner navPartner = (Partner) session.getAttribute("currentPartner");
            User navUser = (User) session.getAttribute("currentUser");

            if (navPartner != null) { 
        %>
            <li class="nav-item">
                <a class="nav-link" href="add_listing.jsp"><i class="fa-solid fa-plus-circle"></i> Post Room</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-info" href="#" id="pDrop" role="button" data-toggle="dropdown">
                    <%= navPartner.getName() %> (Partner)
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">My Listings</a>
                    <a class="dropdown-item" href="partner_dashboard.jsp">Dashboard</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="controller/logout.jsp">Logout</a>
                </div>
            </li>

        <% } else if (navUser != null) { %>
            <li class="nav-item">
                <a class="nav-link" href="view_listings.jsp"><i class="fa-solid fa-search"></i> Find Room</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-warning" href="#" id="uDrop" role="button" data-toggle="dropdown">
                    <%= navUser.getName() %>
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">My Bookings</a>
                    
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item text-danger" href="controller/logout.jsp">Logout</a>
                </div>
            </li>

        <% } else { %>
            <li class="nav-item">
                <a class="nav-link" href="view_listing.jsp">Browse Rooms</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-outline-light btn-sm ml-2" href="login.jsp">Login</a>
            </li>
            <li class="nav-item">
                <a class="btn btn-warning btn-sm ml-2 text-dark font-weight-bold" href="partner_register.jsp">Become a Partner</a>
            </li>
        <% } %>

      </ul>
    </div>
  </div>
</nav>