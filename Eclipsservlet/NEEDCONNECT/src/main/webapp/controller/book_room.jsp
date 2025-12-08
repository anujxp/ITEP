<%@page import="com.needconnect.dao.BookingDAO"%>
<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Booking"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.entity.User"%>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    
    // Security: Only logged-in users can book
    if(currentUser == null) {
        response.sendRedirect("../login.jsp");
    } else {
        try {
            int listingId = Integer.parseInt(request.getParameter("listingId"));
            String date = request.getParameter("bookingDate");

            ListingDAO lDao = new ListingDAO();
            Listing listing = lDao.getListingById(listingId);

            Booking booking = new Booking();
            booking.setBookingDate(date);
            booking.setStatus("Pending");
            booking.setUser(currentUser);
            booking.setListing(listing);

            BookingDAO bDao = new BookingDAO();
            if(bDao.saveBooking(booking)) {
%>
                <script>
                    alert("Booking Request Sent!");
                    window.location.href = "../my_bookings.jsp";
                </script>
<%
            } else {
%>
                <script>alert("Failed to book."); window.history.back();</script>
<%
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
%>