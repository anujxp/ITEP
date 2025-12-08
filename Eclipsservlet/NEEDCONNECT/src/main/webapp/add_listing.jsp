<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.Category"%> <%@page import="com.needconnect.dao.CategoryDAO"%> <%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Post a Room - Need Connect</title>
<%@ include file="__bootstrap_link.jsp" %>
</head>
<body class="bg-light">

    <%@ include file="__header.jsp" %>

    <%
        // SECURITY CHECK: Only allow Partners to see this page
        Partner p = (Partner) session.getAttribute("currentPartner");
        if (p == null) {
            session.setAttribute("errorMessage", "You must login as a Partner to post a room.");
            response.sendRedirect("partner_login.jsp");
            return;
        }
        
        // FETCH CATEGORIES: Get list from DB to populate dropdown
        CategoryDAO catDao = new CategoryDAO();
        List<Category> categories = catDao.getAllCategories();
    %>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow border-0 rounded-3">
                    <div class="card-header bg-info text-white text-center p-4">
                        <h3><i class="fa-solid fa-house-chimney-medical"></i> Post a New Room</h3>
                        <p class="mb-0">Fill in the details to list your property</p>
                    </div>
                    <div class="card-body p-4">
                        
                        <%
                        String error = (String) session.getAttribute("errorMessage");
                        if (error != null) {
                        %>
                            <div class="alert alert-danger"><%=error%></div>
                        <%
                            session.removeAttribute("errorMessage");
                        }
                        %>

                        <form action="controller/add_listing.jsp" method="post">
                            
                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <label class="form-label fw-bold">Property Title</label>
                                    <input type="text" name="title" class="form-control" placeholder="e.g. Single Room near Holkar College" required>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="form-label fw-bold">Property Type</label>
                                    <select name="categoryId" class="form-select" required>
                                        <option value="" disabled selected>Select Type...</option>
                                        <% 
                                            if(categories != null) {
                                                for(Category c : categories) {
                                        %>
                                            <option value="<%= c.getId() %>"><%= c.getName() %></option>
                                        <%      
                                                }
                                            }
                                        %>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="form-label fw-bold">City</label>
                                    <input type="text" name="city" class="form-control" placeholder="e.g. Indore" required>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="form-label fw-bold">Monthly Rent (₹)</label>
                                    <input type="number" name="price" class="form-control" placeholder="e.g. 5000" required>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label class="form-label fw-bold">Availability</label>
                                    <select name="status" class="form-select">
                                        <option value="Available">Available Now</option>
                                        <option value="Booked">Booked / Unavailable</option>
                                    </select>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label class="form-label fw-bold">Image Link</label>
                                    <input type="text" name="image" class="form-control" placeholder="Paste an image URL here">
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label class="form-label fw-bold">Description & Amenities</label>
                                    <textarea name="description" class="form-control" rows="5" placeholder="Describe the room, amenities (WiFi, AC, Food), and rules..."></textarea>
                                </div>
                            </div>

                            <div class="d-grid gap-2 mt-3">
                                <button type="submit" class="btn btn-info text-white btn-lg">Publish Listing</button>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>