<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Room</title>
<%@ include file="__bootstrap_link.jsp" %>
</head>
<body class="bg-light">
    <%@ include file="__header.jsp" %>

    <%
        // Fetch Room Details based on ID in URL
        String idStr = request.getParameter("id");
        Listing listing = null;
        if(idStr != null) {
            ListingDAO dao = new ListingDAO();
            listing = dao.getListingById(Integer.parseInt(idStr));
        }
        
        if(listing != null) {
            String imgUrl = (listing.getImage() != null && listing.getImage().startsWith("http")) ? listing.getImage() : "https://via.placeholder.com/800x400";
    %>

    <div class="container mt-5 mb-5">
        <div class="row">
            <div class="col-md-8">
                <div class="card shadow-sm border-0 mb-4">
                    <img src="<%= imgUrl %>" class="card-img-top" style="height: 350px; object-fit: cover;">
                    <div class="card-body">
                        <h2><%= listing.getTitle() %></h2>
                        <p class="text-muted"><i class="fa-solid fa-map-marker-alt"></i> <%= listing.getCity() %></p>
                        <hr>
                        <h5>Details:</h5>
                        <p><%= listing.getDescription() %></p>
                        <h5>Owner:</h5>
                        <p><strong><%= listing.getPartner().getName() %></strong> (<%= listing.getPartner().getPhone() %>)</p>
                    </div>
                </div>
            </div>

            <div class="col-md-4">
                <div class="card shadow border-0 sticky-top" style="top: 20px;">
                    <div class="card-body">
                        <h4 class="text-success font-weight-bold">₹ <%= listing.getPrice() %> <small>/ mo</small></h4>
                        <hr>
                        <form action="controller/book_room.jsp" method="post">
                            <input type="hidden" name="listingId" value="<%= listing.getId() %>">
                            
                            <div class="form-group">
                                <label>Move-in Date</label>
                                <input type="date" name="bookingDate" class="form-control" required>
                            </div>
                            
                            <button type="submit" class="btn btn-warning btn-block font-weight-bold">Confirm Booking</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <% } %>
</body>
</html>