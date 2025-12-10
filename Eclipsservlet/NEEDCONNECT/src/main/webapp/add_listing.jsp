<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.Category"%>
<%@page import="com.needconnect.dao.CategoryDAO"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Post a Room - Need Connect</title>
<%@ include file="__bootstrap_link.jsp" %>
</head>
<body class="bg-light">

    <%@ include file="__header.jsp" %>

    <%
        Partner p = (Partner) session.getAttribute("currentPartner");
        if (p == null) {
            session.setAttribute("errorMessage", "You must login as a Partner to post a room.");
            response.sendRedirect("partner_login.jsp");
            return;
        }
        
        CategoryDAO catDao = new CategoryDAO();
        List<Category> categories = catDao.getAllCategories();
    %>

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow border-0 rounded-lg">
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
                            
                            <div class="form-row">
                                <div class="col-md-12 mb-3">
                                    <label class="font-weight-bold">Property Title</label>
                                    <input type="text" name="title" class="form-control" placeholder="e.g. Single Room near Holkar College" required>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="font-weight-bold">Property Type</label>
                                    <select name="categoryId" class="form-control" required>
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
                                    <label class="font-weight-bold">City</label>
                                    <select name="city" class="form-control" required>
                                        <option value="" disabled selected>Select City...</option>
                                        <option value="Indore">Indore</option>
                                        <option value="Bhopal">Bhopal</option>
                                        <option value="Mumbai">Mumbai</option>
                                        <option value="Pune">Pune</option>
                                        <option value="Bangalore">Bangalore</option>
                                        <option value="Delhi">Delhi</option>
                                        <option value="Kota">Kota</option>
                                        <option value="Hyderabad">Hyderabad</option>
                                        <option value="Chennai">Chennai</option>
                                        <option value="Jaipur">Jaipur</option>
                                    </select>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label class="font-weight-bold">Monthly Rent (₹)</label>
                                    <input type="number" name="price" class="form-control" placeholder="e.g. 5000" required>
                                </div>
                                
                                <div class="col-md-6 mb-3">
                                    <label class="font-weight-bold">Availability</label>
                                    <select name="status" class="form-control">
                                        <option value="Available">Available Now</option>
                                        <option value="Booked">Booked / Unavailable</option>
                                    </select>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label class="font-weight-bold">Image Link</label>
                                    <input type="text" name="image" class="form-control" placeholder="Paste an image URL here">
                                    <small class="text-muted">Tip: Use an image from Unsplash or Google Images.</small>
                                </div>

                                <div class="col-md-12 mb-3">
                                    <label class="font-weight-bold">Description & Amenities</label>
                                    <textarea name="description" class="form-control" rows="5" placeholder="Describe the room, amenities (WiFi, AC, Food), and rules..."></textarea>
                                </div>
                            </div>

                            <button type="submit" class="btn btn-info btn-block btn-lg mt-3">Publish Listing</button>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>