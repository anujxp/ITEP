<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.entity.Partner"%>

<%
    // 1. SECURITY CHECK: Ensure a Partner is logged in
    Partner p = (Partner) session.getAttribute("currentPartner");
    
    // If not logged in, kick them to login page
    if(p == null) {
        response.sendRedirect("../partner_login.jsp");
        return;
    }

    // 2. Get the Listing ID from the form
    String idStr = request.getParameter("id");

    if(idStr != null && !idStr.trim().isEmpty()) {
        try {
            int listingId = Integer.parseInt(idStr);
            
            // 3. Delete using DAO
            ListingDAO dao = new ListingDAO();
            boolean isDeleted = dao.deleteListing(listingId);
            
            if(isDeleted) {
                // --- SUCCESS: Alert and Refresh Dashboard ---
%>
                <script type="text/javascript">
                    alert("Listing deleted successfully.");
                    window.location.href = "../partner_dashboard.jsp"; 
                </script>
<%
            } else {
                // --- FAILURE ---
%>
                <script type="text/javascript">
                    alert("Failed to delete listing. It might already be gone.");
                    window.location.href = "../partner_dashboard.jsp";
                </script>
<%
            }
            
        } catch(Exception e) {
            e.printStackTrace();
%>
            <script type="text/javascript">
                alert("Error: <%= e.getMessage() %>");
                window.location.href = "../partner_dashboard.jsp";
            </script>
<%
        }
    } else {
        // If ID is missing, just go back to dashboard
        response.sendRedirect("../partner_dashboard.jsp");
    }
%>