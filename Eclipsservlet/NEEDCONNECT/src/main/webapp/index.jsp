<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.User"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Category"%>
<%@page import="com.needconnect.dao.CategoryDAO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Need Connect - Find Your Home</title>
<%@ include file="__bootstrap_link.jsp" %>

<style>
    /* Hero Section with Background Image */
    .hero-section {
        background: linear-gradient(rgba(0,0,0,0.6), rgba(0,0,0,0.6)), url('https://images.unsplash.com/photo-1555854877-bab0e564b8d5?auto=format&fit=crop&w=1920&q=80');
        background-size: cover;
        background-position: center;
        padding: 100px 0;
        color: white;
        border-radius: 0 0 30px 30px; /* Curve at bottom */
    }
    
    /* The White Search Box */
    .search-card {
        background: rgba(255, 255, 255, 0.95);
        border-radius: 10px;
        padding: 30px;
        box-shadow: 0 10px 30px rgba(0,0,0,0.2);
    }
    
    /* Room Card Styling */
    .room-card {
        border: none;
        border-radius: 15px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.05);
        transition: transform 0.3s;
        overflow: hidden;
    }
    .room-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 10px 20px rgba(0,0,0,0.1);
    }
    .room-img {
        height: 200px;
        width: 100%;
        object-fit: cover;
    }
    .price-badge {
        position: absolute;
        top: 10px;
        right: 10px;
        background: #28a745;
        color: white;
        padding: 5px 10px;
        border-radius: 20px;
        font-weight: bold;
    }
</style>
</head>
<body class="bg-light d-flex flex-column min-vh-100">

    <%@ include file="__header.jsp" %>

    <% 
        Partner currentPartner = (Partner) session.getAttribute("currentPartner");
        User currentUser = (User) session.getAttribute("currentUser");
    %>

    <div class="hero-section text-center mb-5">
        <div class="container">
            
            <% if(currentPartner != null) { %>
                <h1 class="display-4 font-weight-bold">Welcome, Partner <%= currentPartner.getName() %>!</h1>
                <p class="lead mb-4">Manage your listings and grow your business.</p>
                <div class="d-flex justify-content-center">
                    <a href="add_listing.jsp" class="btn btn-info btn-lg font-weight-bold px-4 shadow mr-3">
                        <i class="fa-solid fa-plus-circle"></i> Post a Room
                    </a>
                    <a href="#" class="btn btn-light btn-lg font-weight-bold px-4 shadow">My Listings</a>
                </div>

            <% } else { %>
                <h1 class="display-4 font-weight-bold mb-3">Find Your Perfect Stay</h1>
                <p class="lead mb-5">Search PGs, Hostels, and Rooms without brokerage.</p>

                <div class="row justify-content-center">
                    <div class="col-md-10 col-lg-8">
                        <div class="search-card text-dark text-left">
                            
                            <form action="view_listing.jsp" method="get">
                                <div class="form-row align-items-end">
                                    
                                    <div class="col-md-5 mb-3 mb-md-0">
                                        <label class="font-weight-bold">City / Area</label>
                                        <div class="input-group">
                                            <div class="input-group-prepend">
                                                <span class="input-group-text bg-white"><i class="fa-solid fa-location-dot text-danger"></i></span>
                                            </div>
                                            <input type="text" name="city" class="form-control" placeholder="e.g. Indore, Vijay Nagar">
                                        </div>
                                    </div>

                                    <div class="col-md-4 mb-3 mb-md-0">
                                        <label class="font-weight-bold">Type</label>
                                        <select name="category" class="form-control">
                                            <option value="">All Types</option>
                                            <%
                                                CategoryDAO catDao = new CategoryDAO();
                                                List<Category> cats = catDao.getAllCategories();
                                                if(cats != null){
                                                    for(Category c : cats){
                                            %>
                                                <option value="<%= c.getId() %>"><%= c.getName() %></option>
                                            <%
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>

                                    <div class="col-md-3">
                                        <button type="submit" class="btn btn-warning btn-block font-weight-bold shadow-sm" style="height: 38px;">
                                            Search
                                        </button>
                                    </div>
                                </div>
                            </form>
                            </div>
                    </div>
                </div>
            <% } %>
        </div>
    </div>

    <div class="container mb-5">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h3 class="font-weight-bold border-left pl-3" style="border-width: 5px !important; border-color: #ffc107 !important;">
                Latest Additions
            </h3>
            <a href="view_listing.jsp" class="btn btn-outline-primary btn-sm">View All</a>
        </div>
        
        <div class="row">
            <%
                ListingDAO listDao = new ListingDAO();
                // Fetch ALL listings
                List<Listing> homeList = listDao.getAllListings();
                
                if(homeList != null && !homeList.isEmpty()) {
                    // LOOP: Show only the first 3 items
                    int count = 0;
                    for(Listing l : homeList) {
                        if(count >= 3) break; // Stop after 3
                        
                        // Handle Image URL (Placeholder if empty)
                        String imgUrl = (l.getImage() != null && l.getImage().startsWith("http")) 
                                        ? l.getImage() 
                                        : "https://images.unsplash.com/photo-1522771753037-63335e6dd782?auto=format&fit=crop&w=600&q=80";
            %>
                <div class="col-md-4 mb-4">
                    <div class="card room-card h-100">
                        <div class="position-relative">
                            <img src="<%= imgUrl %>" class="card-img-top room-img" alt="Room">
                            <span class="price-badge">₹ <%= l.getPrice() %></span>
                        </div>
                        <div class="card-body">
                            <span class="badge badge-primary mb-2"><%= l.getCategory().getName() %></span>
                            <h5 class="card-title font-weight-bold text-truncate"><%= l.getTitle() %></h5>
                            <p class="text-muted small mb-2"><i class="fa-solid fa-map-marker-alt text-danger"></i> <%= l.getCity() %></p>
                            
                            <p class="card-text text-secondary small">
                                <%= (l.getDescription().length() > 60) ? l.getDescription().substring(0, 60) + "..." : l.getDescription() %>
                            </p>
                        </div>
                        <div class="card-footer bg-white border-0 pt-0 pb-4">
                            <a href="book_room.jsp?id=<%= l.getId() %>" class="btn btn-outline-primary btn-block font-weight-bold">
                                View Details
                            </a>
                        </div>
                    </div>
                </div>
            <% 
                        count++;
                    }
                } else {
            %>
                <div class="col-12 text-center py-5">
                    <h5 class="text-muted">No rooms listed yet. Be the first partner!</h5>
                </div>
            <% } %>
        </div>
    </div>

    <footer class="bg-dark text-white text-center py-4 mt-auto">
        <div class="container">
            <p class="mb-0">&copy; 2025 Need Connect.</p>
            <small class="text-muted">Made with <i class="fa-solid fa-heart text-danger"></i> in Java JSP</small>
        </div>
    </footer>

</body>
</html>