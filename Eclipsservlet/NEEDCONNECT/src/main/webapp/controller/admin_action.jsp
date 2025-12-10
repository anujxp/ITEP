<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.util.Util"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.entity.Admin"%>
<%@page import="jakarta.persistence.EntityManager"%>
<%@page import="jakarta.persistence.EntityTransaction"%>

<%
    // 1. Security Check: Ensure only Admin can access this
    Admin admin = (Admin) session.getAttribute("currentAdmin");
    if(admin == null) {
        response.sendRedirect("../admin_login.jsp");
        return;
    }

    // 2. Get Parameters
    String idStr = request.getParameter("id");
    String action = request.getParameter("action"); // "Approved" or "Rejected"

    if(idStr != null && action != null) {
        int listingId = Integer.parseInt(idStr);
        
        EntityManager em = Util.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        
        try {
            tx.begin();
            
            // 3. Find the Listing
            Listing listing = em.find(Listing.class, listingId);
            
            if(listing != null) {
                // 4. Update the Admin Status
                listing.setAdminStatus(action); 
                
                // Optional: If rejected, you might want to auto-archive it or add a note
                // For MVP, just changing status is enough.
            }
            
            tx.commit();
            
            // 5. Success -> Back to Dashboard
            response.sendRedirect("../admin_dashboard.jsp");
            
        } catch(Exception e) {
            if(tx.isActive()) tx.rollback();
            e.printStackTrace();
            // Optional: Add error handling/alert here
            response.sendRedirect("../admin_dashboard.jsp");
        } finally {
            em.close();
        }
    } else {
        response.sendRedirect("../admin_dashboard.jsp");
    }
%>