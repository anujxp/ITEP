<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="com.needconnect.dao.BookingDAO"%>
<%@page import="com.needconnect.entity.Booking"%>
<%@page import="com.needconnect.entity.User"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Bookings</title>
<%@ include file="__bootstrap_link.jsp"%>
</head>
<body class="bg-light">
	<%@ include file="__header.jsp"%>

	<% User user = (User) session.getAttribute("currentUser"); %>

	<div class="container mt-5">
		<h3>My Bookings</h3>
		<table class="table table-bordered bg-white shadow-sm mt-3">
			<thead class="thead-dark">
				<tr>
					<th>Room Name</th>
					<th>City</th>
					<th>Date</th>
					<th>Status</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
				<%
                    if(user != null) {
                        BookingDAO dao = new BookingDAO();
                        List<Booking> list = dao.getBookingsByUser(user.getId());
                        if(list != null) {
                            for(Booking b : list) {
                %>
				<tr>
					<td><%= b.getListing().getTitle() %></td>
					<td><%= b.getListing().getCity() %></td>
					<td><%= b.getBookingDate() %></td>
					<td><span class="badge badge-info"><%= b.getStatus() %></span></td>
					<td>₹ <%= b.getListing().getPrice() %></td>
				</tr>
				<% 
                            }
                        }
                    }
                %>
			</tbody>
		</table>
	</div>
</body>
</html>