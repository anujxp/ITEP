<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.dao.BookingDAO"%>
<%@page import="com.needconnect.entity.Booking"%>
<%@page import="com.needconnect.entity.User"%> 
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Partner Dashboard</title>
<%@ include file="__bootstrap_link.jsp" %>
<style>
    /* === TAB STYLING FIX === */
    
    /* 1. Inactive Tabs (The ones you couldn't see well) */
    .nav-pills .nav-link {
        background-color: #ffffff;  /* Solid White Background */
        color: #495057;             /* Dark Grey Text */
        border: 1px solid #ced4da;  /* Visible Border */
        margin-right: 10px;
        font-weight: 600;
        box-shadow: 0 2px 5px rgba(0,0,0,0.05); /* 3D Shadow */
        transition: 0.3s;
    }

    /* 2. Hover Effect */
    .nav-pills .nav-link:hover {
        background-color: #e9ecef; /* Light Grey on Hover */
        transform: translateY(-2px);
    }

    /* 3. Active Tab (Selected) */
    .nav-pills .nav-link.active {
        background-color: #17a2b8; /* Partner Cyan */
        color: #fff !important;
        border-color: #17a2b8;
        box-shadow: 0 4px 10px rgba(23, 162, 184, 0.4); /* Glow effect */
    }

    /* Status Badges */
    .status-pending { color: #856404; font-weight: bold; background: #fff3cd; padding: 4px 10px; border-radius: 4px; }
    .status-confirmed { color: #155724; font-weight: bold; background: #d4edda; padding: 4px 10px; border-radius: 4px; }
    .status-cancelled { color: #721c24; font-weight: bold; background: #f8d7da; padding: 4px 10px; border-radius: 4px; }
</style>
</head>
<body class="bg-light">

    <%@ include file="__header.jsp" %>

    <%
        Partner p = (Partner) session.getAttribute("currentPartner");
        if(p == null) {
            response.sendRedirect("partner_login.jsp");
            return;
        }
        
        ListingDAO lDao = new ListingDAO();
        BookingDAO bDao = new BookingDAO();
        
        List<Listing> myListings = lDao.getListingsByPartner(p.getId());
        List<Booking> myBookings = bDao.getBookingsByPartner(p.getId());
    %>

    <div class="container mt-5 mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 class="font-weight-bold text-dark">Partner Dashboard</h2>
            <span class="badge badge-info p-2 shadow-sm">Welcome, <%= p.getName() %></span>
        </div>

        <ul class="nav nav-pills mb-4" id="pills-tab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="pills-rooms-tab" data-toggle="pill" href="#pills-rooms" role="tab">
                    <i class="fa-solid fa-list mr-2"></i> My Rooms (<%= (myListings!=null)?myListings.size():0 %>)
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-bookings-tab" data-toggle="pill" href="#pills-bookings" role="tab">
                    <i class="fa-solid fa-calendar-check mr-2"></i> Booking Requests 
                    <% if(myBookings != null && !myBookings.isEmpty()) { %>
                        <span class="badge badge-danger ml-2"><%= myBookings.size() %></span>
                    <% } %>
                </a>
            </li>
        </ul>

        <div class="tab-content" id="pills-tabContent">
            
            <div class="tab-pane fade show active" id="pills-rooms" role="tabpanel">
                <div class="row">
                    <% if(myListings != null && !myListings.isEmpty()) { 
                        for(Listing l : myListings) { %>
                        <div class="col-md-4 mb-4">
                            <div class="card shadow-sm h-100 border-0">
                                <div class="card-body">
                                    <h5 class="card-title text-truncate text-info font-weight-bold"><%= l.getTitle() %></h5>
                                    <p class="text-muted small mb-2"><i class="fa-solid fa-map-marker-alt"></i> <%= l.getCity() %> | <strong>₹ <%= l.getPrice() %></strong></p>
                                    
                                    <% if("Approved".equalsIgnoreCase(l.getAdminStatus())) { %>
                                        <span class="badge badge-success px-2 py-1">Live</span>
                                    <% } else { %>
                                        <span class="badge badge-warning px-2 py-1">Pending Approval</span>
                                    <% } %>
                                </div>
                                <div class="card-footer bg-white d-flex justify-content-between align-items-center">
                                    <small class="text-muted">ID: <%= l.getId() %></small>
                                    
                                    <form action="controller/delete_listing.jsp" method="post" onsubmit="return confirm('Are you sure you want to delete this room?');">
                                        <input type="hidden" name="id" value="<%= l.getId() %>">
                                        <button type="submit" class="btn btn-outline-danger btn-sm rounded-circle" style="width: 35px; height: 35px; padding: 0;">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    <% } } else { %>
                        <div class="col-12 text-center p-5 bg-white rounded shadow-sm">
                            <i class="fa-solid fa-folder-open fa-3x text-muted mb-3"></i>
                            <h5 class="text-muted">You haven't posted any rooms yet.</h5>
                            <a href="add_listing.jsp" class="btn btn-info mt-2 px-4 rounded-pill">Post Your First Room</a>
                        </div>
                    <% } %>
                </div>
            </div>

            <div class="tab-pane fade" id="pills-bookings" role="tabpanel">
                <div class="card shadow-sm border-0">
                    <div class="table-responsive">
                        <table class="table table-hover mb-0">
                            <thead class="bg-light">
                                <tr>
                                    <th class="border-top-0 pl-4">Student Name</th>
                                    <th class="border-top-0">Room Requested</th>
                                    <th class="border-top-0">Move-in Date</th>
                                    <th class="border-top-0">Current Status</th>
                                    <th class="border-top-0">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if(myBookings != null && !myBookings.isEmpty()) { 
                                    for(Booking b : myBookings) { %>
                                    <tr>
                                        <td class="pl-4 align-middle">
                                            <strong class="text-dark"><%= b.getUser().getName() %></strong><br>
                                            <small class="text-muted"><i class="fa-solid fa-phone mr-1"></i> <%= b.getUser().getPhone() %></small>
                                        </td>
                                        <td class="align-middle text-primary"><%= b.getListing().getTitle() %></td>
                                        <td class="align-middle"><%= b.getBookingDate() %></td>
                                        <td class="align-middle">
                                            <% if("Pending".equalsIgnoreCase(b.getStatus())) { %>
                                                <span class="status-pending">Pending</span>
                                            <% } else if("Confirmed".equalsIgnoreCase(b.getStatus())) { %>
                                                <span class="status-confirmed">Confirmed</span>
                                            <% } else { %>
                                                <span class="status-cancelled">Cancelled</span>
                                            <% } %>
                                        </td>
                                        <td class="align-middle">
                                            <% if("Pending".equalsIgnoreCase(b.getStatus())) { %>
                                                <div class="btn-group">
                                                    <form action="controller/update_booking.jsp" method="post" class="mr-2">
                                                        <input type="hidden" name="id" value="<%= b.getId() %>">
                                                        <input type="hidden" name="status" value="Confirmed">
                                                        <button class="btn btn-success btn-sm shadow-sm" title="Accept">
                                                            <i class="fa-solid fa-check"></i>
                                                        </button>
                                                    </form>
                                                    
                                                    <form action="controller/update_booking.jsp" method="post">
                                                        <input type="hidden" name="id" value="<%= b.getId() %>">
                                                        <input type="hidden" name="status" value="Cancelled">
                                                        <button class="btn btn-danger btn-sm shadow-sm" title="Reject">
                                                            <i class="fa-solid fa-times"></i>
                                                        </button>
                                                    </form>
                                                </div>
                                            <% } else { %>
                                                <span class="text-muted small">Closed</span>
                                            <% } %>
                                        </td>
                                    </tr>
                                <% } } else { %>
                                    <tr>
                                        <td colspan="5" class="text-center p-5">
                                            <i class="fa-solid fa-inbox fa-3x text-muted mb-3 opacity-50"></i>
                                            <h5 class="text-muted">No booking requests yet.</h5>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
        </div>
    </div>

</body>
</html>