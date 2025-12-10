<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Admin"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Admin Dashboard - Need Connect</title>

<%@ include file="__bootstrap_link.jsp" %>

<link href="https://fonts.googleapis.com/css2?family=Outfit:wght@300;400;600;700&display=swap" rel="stylesheet">

<style>
    /* 1. ANIMATIONS */
    @keyframes fadeInUp {
        from { opacity: 0; transform: translateY(20px); }
        to { opacity: 1; transform: translateY(0); }
    }
    @keyframes gradientBG {
        0% { background-position: 0% 50%; }
        50% { background-position: 100% 50%; }
        100% { background-position: 0% 50%; }
    }
    @keyframes float {
        0% { transform: translateY(0px); }
        50% { transform: translateY(-5px); }
        100% { transform: translateY(0px); }
    }

    /* 2. GLOBAL STYLES */
    body {
        font-family: 'Outfit', sans-serif;
        background: url('https://images.unsplash.com/photo-1486406146926-c627a92ad1ab?auto=format&fit=crop&w=1920&q=80');
        background-size: cover;
        background-position: center;
        background-attachment: fixed;
        min-height: 100vh;
    }

    .overlay-wrapper {
        background: rgba(15, 23, 42, 0.85); /* Deep dark blue overlay */
        min-height: 100vh;
        width: 100%;
        padding-bottom: 50px;
    }

    /* 3. NAVBAR */
    .admin-navbar {
        background: rgba(255, 255, 255, 0.05);
        backdrop-filter: blur(10px);
        border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        padding: 15px 0;
    }

    .super-user-badge {
        position: relative;
        color: rgba(255,255,255,0.8);
        font-weight: 600;
        letter-spacing: 0.5px;
        cursor: pointer;
        padding-bottom: 5px;
        transition: all 0.3s ease;
    }
    .super-user-badge::after {
        content: '';
        position: absolute;
        width: 0;
        height: 2px;
        bottom: 0;
        left: 0;
        background-color: #0dcaf0;
        box-shadow: 0 0 10px #0dcaf0;
        transition: width 0.4s ease;
    }
    .super-user-badge:hover { color: #fff; text-shadow: 0 0 15px rgba(13, 202, 240, 0.8); }
    .super-user-badge:hover::after { width: 100%; }

    /* 4. STAT CARDS (FIXED: Opacity set to 1) */
    .stat-card {
        border: none;
        border-radius: 20px;
        color: white;
        position: relative;
        overflow: hidden;
        /* Force visible immediately */
        opacity: 1; 
        transition: transform 0.3s, box-shadow 0.3s;
        background-size: 200% 200%;
        animation: gradientBG 5s ease infinite; 
    }

    .stat-card:hover {
        transform: translateY(-10px) scale(1.02);
        box-shadow: 0 20px 40px rgba(0,0,0,0.4);
        z-index: 10;
    }

    /* Custom Gradients for the Cards */
    .bg-gradient-1 { background: linear-gradient(45deg, #4e73df, #224abe); } /* Blue */
    .bg-gradient-2 { background: linear-gradient(45deg, #1cc88a, #13855c); } /* Green */
    .bg-gradient-3 { background: linear-gradient(45deg, #36b9cc, #258391); } /* Cyan */

    .stat-icon {
        position: absolute;
        right: 20px;
        top: 50%;
        transform: translateY(-50%);
        font-size: 3.5rem;
        opacity: 0.2;
        transition: all 0.5s;
    }
    .stat-card:hover .stat-icon {
        transform: translateY(-50%) scale(1.2) rotate(15deg);
        opacity: 0.4;
    }

    /* 5. MAIN DASHBOARD CARD */
    .dashboard-glass {
        background: rgba(255, 255, 255, 0.95);
        border-radius: 20px;
        box-shadow: 0 25px 50px rgba(0,0,0,0.25);
        backdrop-filter: blur(20px);
        border: 1px solid rgba(255,255,255,0.1);
        overflow: hidden;
        animation: fadeInUp 0.8s ease-out forwards;
        animation-delay: 0.4s;
        opacity: 0;
    }

    /* 6. TABLE */
    .custom-table thead th {
        background: #f1f5f9;
        color: #475569;
        text-transform: uppercase;
        font-size: 0.75rem;
        letter-spacing: 1px;
        border: none;
        padding: 15px 20px;
    }
    .custom-table tbody tr {
        transition: all 0.2s;
        border-bottom: 1px solid #f1f5f9;
    }
    .custom-table tbody tr:hover {
        background-color: #f8fafc;
        transform: scale(1.005);
        box-shadow: 0 5px 15px rgba(0,0,0,0.05);
        z-index: 5;
        position: relative;
    }

    /* 7. BADGES & BUTTONS */
    .badge-soft-primary { background: rgba(59, 130, 246, 0.1); color: #2563eb; padding: 5px 10px; border-radius: 8px; }
    
    .btn-action {
        width: 35px; height: 35px;
        border-radius: 50%;
        display: inline-flex;
        align-items: center;
        justify-content: center;
        transition: 0.3s;
        border: none;
        box-shadow: 0 4px 6px rgba(0,0,0,0.1);
    }
    .btn-action:hover {
        transform: translateY(-3px);
        box-shadow: 0 8px 15px rgba(0,0,0,0.2);
    }
    .pulse-badge { animation: float 2s ease-in-out infinite; }
</style>
</head>
<body>

    <div class="overlay-wrapper">

        <nav class="navbar navbar-expand-lg navbar-dark admin-navbar">
            <div class="container">
                <a class="navbar-brand font-weight-bold" href="#">
                    <i class="fa-solid fa-layer-group text-info mr-2"></i> NEED CONNECT <small class="opacity-50">ADMIN</small>
                </a>
                <div class="ml-auto d-flex align-items-center">
                    <span class="super-user-badge mr-4 d-none d-md-block">
                        <i class="fa-solid fa-circle-user mr-2"></i> Logged in as Super Admin
                    </span>
                    <a href="controller/logout.jsp" class="btn btn-outline-light btn-sm rounded-pill px-4 font-weight-bold">LOGOUT</a>
                </div>
            </div>
        </nav>

        <%
            // JAVA LOGIC
            Admin admin = (Admin) session.getAttribute("currentAdmin");
            if(admin == null) {
                response.sendRedirect("admin_login.jsp");
                return;
            }

            ListingDAO dao = new ListingDAO();
            List<Listing> pendingListings = dao.getListingsByAdminStatus("Pending");
            List<Listing> approvedListings = dao.getListingsByAdminStatus("Approved");
            
            int pendingCount = (pendingListings != null) ? pendingListings.size() : 0;
            int approvedCount = (approvedListings != null) ? approvedListings.size() : 0;
            int totalCount = pendingCount + approvedCount;
        %>

        <div class="container mt-5 pb-5">
            
            <div class="d-flex justify-content-between align-items-end mb-5">
                <div style="animation: fadeInUp 0.5s ease-out;">
                    <h2 class="font-weight-bold text-white mb-0">Dashboard Overview</h2>
                    <p class="text-white-50 mb-0">Welcome back, here is what needs your attention.</p>
                </div>
                <div class="text-right text-white-50 small d-none d-md-block" style="animation: fadeInUp 0.5s ease-out;">
                    <i class="fa-regular fa-clock mr-1"></i> System Status: Online
                </div>
            </div>

            <div class="row mb-5">
                <div class="col-md-4 mb-4">
                    <div class="card stat-card bg-gradient-1 h-100 p-4">
                        <div class="stat-icon"><i class="fa-solid fa-hourglass-half"></i></div>
                        <h6 class="text-uppercase font-weight-bold mb-1 opacity-75">Pending Approvals</h6>
                        <h1 class="display-3 font-weight-bold mb-0"><%= pendingCount %></h1>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card stat-card bg-gradient-2 h-100 p-4">
                        <div class="stat-icon"><i class="fa-solid fa-circle-check"></i></div>
                        <h6 class="text-uppercase font-weight-bold mb-1 opacity-75">Active Listings</h6>
                        <h1 class="display-3 font-weight-bold mb-0"><%= approvedCount %></h1>
                    </div>
                </div>

                <div class="col-md-4 mb-4">
                    <div class="card stat-card bg-gradient-3 h-100 p-4">
                        <div class="stat-icon"><i class="fa-solid fa-building"></i></div>
                        <h6 class="text-uppercase font-weight-bold mb-1 opacity-75">Total Database</h6>
                        <h1 class="display-3 font-weight-bold mb-0"><%= totalCount %></h1>
                    </div>
                </div>
            </div>

            <div class="dashboard-glass">
                <div class="p-4 d-flex justify-content-between align-items-center border-bottom">
                    <h5 class="font-weight-bold mb-0 text-dark">
                        <i class="fa-solid fa-list-check text-primary mr-2"></i> Approval Queue
                    </h5>
                    <% if(pendingCount > 0) { %>
                        <span class="badge badge-danger badge-pill px-3 py-2 shadow pulse-badge"><%= pendingCount %> New</span>
                    <% } else { %>
                        <span class="badge badge-success badge-pill px-3 py-2 shadow">All Caught Up</span>
                    <% } %>
                </div>
                
                <div class="table-responsive">
                    <table class="table custom-table mb-0">
                        <thead>
                            <tr>
                                <th class="pl-4">Reference</th>
                                <th>Listing Details</th>
                                <th>Partner</th>
                                <th>Type</th>
                                <th>Price</th>
                                <th class="text-center">Decisions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                if(pendingListings != null && !pendingListings.isEmpty()) {
                                    for(Listing l : pendingListings) {
                            %>
                            <tr>
                                <td class="pl-4 align-middle text-muted font-weight-bold">#<%= l.getId() %></td>
                                <td class="align-middle">
                                    <span class="font-weight-bold text-dark"><%= l.getTitle() %></span><br>
                                    <small class="text-muted"><i class="fa-solid fa-location-arrow mr-1"></i> <%= l.getCity() %></small>
                                </td>
                                <td class="align-middle">
                                    <i class="fa-solid fa-user text-secondary mr-2"></i> <span class="font-weight-bold text-secondary"><%= l.getPartner().getName() %></span>
                                </td>
                                <td class="align-middle">
                                    <span class="badge-soft-primary"><%= l.getCategory().getName() %></span>
                                </td>
                                <td class="align-middle font-weight-bold text-dark">
                                    ₹ <%= l.getPrice() %>
                                </td>
                                <td class="text-center align-middle">
                                    <div class="d-flex justify-content-center">
                                        <form action="controller/admin_action.jsp" method="post" class="mr-2">
                                            <input type="hidden" name="id" value="<%= l.getId() %>">
                                            <input type="hidden" name="action" value="Approved">
                                            <button class="btn btn-action btn-success" title="Approve">
                                                <i class="fa-solid fa-check"></i>
                                            </button>
                                        </form>
                                        <form action="controller/admin_action.jsp" method="post">
                                            <input type="hidden" name="id" value="<%= l.getId() %>">
                                            <input type="hidden" name="action" value="Rejected">
                                            <button class="btn btn-action btn-danger" title="Reject">
                                                <i class="fa-solid fa-xmark"></i>
                                            </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                            <% 
                                    }
                                } else { 
                            %>
                            <tr>
                                <td colspan="6" class="text-center py-5">
                                    <i class="fa-solid fa-mug-hot fa-3x text-secondary opacity-50 mb-3"></i>
                                    <h5 class="text-muted">No pending approvals</h5>
                                </td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
            
        </div>
    </div>

</body>
</html>