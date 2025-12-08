<%@page import="com.needconnect.dao.BookingDAO"%>
<%@page import="com.needconnect.util.Util"%>
<%@page import="com.needconnect.entity.Booking"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="jakarta.persistence.EntityManager"%>
<%@page import="jakarta.persistence.EntityTransaction"%>

<%
    // Security Check
    Partner p = (Partner) session.getAttribute("currentPartner");
    if(p == null) {
        response.sendRedirect("../partner_login.jsp");
        return;
    }

    String idStr = request.getParameter("id");
    String status = request.getParameter("status");

    if(idStr != null && status != null) {
        int bookingId = Integer.parseInt(idStr);
        
        EntityManager em = Util.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // 1. Update Booking Status
            Booking booking = em.find(Booking.class, bookingId);
            booking.setStatus(status);
            
            // 2. If Confirmed, Update Listing Status to 'Booked'
            if("Confirmed".equalsIgnoreCase(status)) {
                Listing listing = booking.getListing();
                listing.setStatus("Booked");
                // JPA automatically tracks this change because 'listing' is attached
            }
            
            tx.commit();
            
            // Redirect back to Dashboard
            response.sendRedirect("../partner_dashboard.jsp");
            
        } catch(Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    } else {
        response.sendRedirect("../partner_dashboard.jsp");
    }
%>