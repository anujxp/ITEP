<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Browse Rooms - Need Connect</title>
<%@ include file="__bootstrap_link.jsp" %>
<style>
    .room-card {
        transition: transform 0.3s;
        border: none;
        border-radius: 15px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.05);
        background: #fff;
        overflow: hidden;
    }
    .room-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 25px rgba(0,0,0,0.1);
    }
    .room-img {
        height: 220px;
        width: 100%;
        object-fit: cover;
    }
    .price-badge {
        position: absolute;
        top: 15px; right: 15px;
        background: rgba(255, 255, 255, 0.95);
        padding: 5px 12px; border-radius: 20px;
        font-weight: bold; color: #28a745;
        box-shadow: 0 2px 5px rgba(0,0,0,0.1);
    }
    .search-container {
        background: #fff;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 2px 10px rgba(0,0,0,0.05);
        margin-bottom: 30px;
        border: 1px solid #eee;
    }
</style>
</head>
<body class="bg-light">

    <%@ include file="__header.jsp" %>

    <div class="container mt-4 mb-5">
        
        <div class="search-container">
            <%@ include file="__search_bar.jsp" %>
        </div>

        <%
            ListingDAO dao = new ListingDAO();
            List<Listing> list = null;

            String searchCity = request.getParameter("city");
            String searchCat = request.getParameter("category");
            String searchTitle = "All Listings";

            if ((searchCity != null && !searchCity.isEmpty()) || (searchCat != null && !searchCat.isEmpty())) {
                // User is filtering
                list = dao.searchListings(searchCity, searchCat);
                searchTitle = "Search Results";
            } else {
                // User wants to see everything
                list = dao.getAllListings();
            }
        %>

        <h4 class="font-weight-bold mb-4 border-left pl-3 text-dark" style="border-width: 5px !important; border-color: #0dcaf0 !important;">
            <%= searchTitle %>
        </h4>

        <div class="row"> 
            <%
                if(list != null && !list.isEmpty()) {
                    for(Listing l : list) {
                        String imgUrl = (l.getImage() != null && l.getImage().startsWith("http")) 
                                        ? l.getImage() 
                                        : "https://images.unsplash.com/photo-1522771753037-63335e6dd782?auto=format&fit=crop&w=600&q=80";
            %>
                <div class="col-md-4 mb-4">
                    <div class="card room-card h-100">
                        <div class="position-relative">
                            <img src="<%= imgUrl %>" class="card-img-top room-img" alt="Room Image">
                            <span class="price-badge">₹ <%= l.getPrice() %></span>
                        </div>
                        
                        <div class="card-body">
                            <span class="badge badge-primary mb-2"><%= l.getCategory().getName() %></span>
                            <h5 class="card-title font-weight-bold text-truncate"><%= l.getTitle() %></h5>
                            <p class="text-muted small mb-2">
                                <i class="fa-solid fa-location-dot text-danger mr-1"></i> <%= l.getCity() %>
                            </p>
                            <p class="card-text text-secondary small">
                                <%= (l.getDescription() != null && l.getDescription().length() > 80) ? l.getDescription().substring(0, 80) + "..." : l.getDescription() %>
                            </p>
                        </div>
                        
                        <div class="card-footer bg-white border-0 pt-0 pb-4">
                            <% if("Available".equalsIgnoreCase(l.getStatus())) { %>
                                <a href="book_room.jsp?id=<%= l.getId() %>" class="btn btn-outline-primary font-weight-bold btn-block">
                                    View Details & Book
                                </a>
                            <% } else { %>
                                <button class="btn btn-secondary btn-block" disabled>Currently Booked</button>
                            <% } %>
                        </div>
                    </div>
                </div>
            <% 
                    }
                } else {
            %>
                <div class="col-12 text-center py-5">
                    <div class="mb-3">
                        <i class="fa-solid fa-magnifying-glass text-muted" style="font-size: 3rem;"></i>
                    </div>
                    <h4>No rooms found matching your search</h4>
                    <a href="view_listings.jsp" class="btn btn-link">Clear Filters</a>
                </div>
            <% } %>
        </div>
    </div>

</body>
</html>