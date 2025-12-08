<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.dao.BookingDAO"%>
<%@page import="com.needconnect.entity.Booking"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Partner Dashboard</title>
<%@ include file="__bootstrap_link.jsp" %>
<style>
    .nav-pills .nav-link.active {
        background-color: #17a2b8; /* Partner Info Color */
    }
    .status-pending { color: #856404; font-weight: bold; }
    .status-confirmed { color: #155724; font-weight: bold; }
    .status-cancelled { color: #721c24; font-weight: bold; }
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
        <h2 class="font-weight-bold mb-4">Partner Dashboard</h2>

        <ul class="nav nav-pills mb-4" id="pills-tab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="pills-rooms-tab" data-toggle="pill" href="#pills-rooms" role="tab">My Rooms (<%= (myListings!=null)?myListings.size():0 %>)</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="pills-bookings-tab" data-toggle="pill" href="#pills-bookings" role="tab">
                    Booking Requests 
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
                            <div class="card shadow-sm h-100">
                                <div class="card-body">
                                    <h5 class="card-title text-truncate"><%= l.getTitle() %></h5>
                                    <p class="text-muted small"><%= l.getCity() %> | ₹ <%= l.getPrice() %></p>
                                    <span class="badge badge-info"><%= l.getStatus() %></span>
                                </div>
                                <div class="card-footer bg-white">
                                    <small class="text-muted">ID: <%= l.getId() %></small>
                                </div>
                            </div>
                        </div>
                    <% } } else { %>
                        <div class="col-12 text-center p-5 text-muted">You haven't posted any rooms yet.</div>
                    <% } %>
                </div>
            </div>

            <div class="tab-pane fade" id="pills-bookings" role="tabpanel">
                <div class="card shadow-sm border-0">
                    <div class="table-responsive">
                        <table class="table table-hover mb-0">
                            <thead class="thead-light">
                                <tr>
                                    <th>Student Name</th>
                                    <th>Room Requested</th>
                                    <th>Move-in Date</th>
                                    <th>Current Status</th>
                                    <th>Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% if(myBookings != null && !myBookings.isEmpty()) { 
                                    for(Booking b : myBookings) { %>
                                    <tr>
                                        <td>
                                            <strong><%= b.getUser().getName() %></strong><br>
                                            <small><%= b.getUser().getPhone() %></small>
                                        </td>
                                        <td><%= b.getListing().getTitle() %></td>
                                        <td><%= b.getBookingDate() %></td>
                                        <td>
                                            <% if("Pending".equalsIgnoreCase(b.getStatus())) { %>
                                                <span class="status-pending">Pending</span>
                                            <% } else if("Confirmed".equalsIgnoreCase(b.getStatus())) { %>
                                                <span class="status-confirmed">Confirmed</span>
                                            <% } else { %>
                                                <span class="status-cancelled">Cancelled</span>
                                            <% } %>
                                        </td>
                                        <td>
                                            <% if("Pending".equalsIgnoreCase(b.getStatus())) { %>
                                                <div class="btn-group">
                                                    <form action="controller/update_booking.jsp" method="post">
                                                        <input type="hidden" name="id" value="<%= b.getId() %>">
                                                        <input type="hidden" name="status" value="Confirmed">
                                                        <button class="btn btn-success btn-sm mr-2" title="Accept">
                                                            <i class="fa-solid fa-check"></i>
                                                        </button>
                                                    </form>
                                                    
                                                    <form action="controller/update_booking.jsp" method="post">
                                                        <input type="hidden" name="id" value="<%= b.getId() %>">
                                                        <input type="hidden" name="status" value="Cancelled">
                                                        <button class="btn btn-danger btn-sm" title="Reject">
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
                                    <tr><td colspan="5" class="text-center p-4">No booking requests yet.</td></tr>
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