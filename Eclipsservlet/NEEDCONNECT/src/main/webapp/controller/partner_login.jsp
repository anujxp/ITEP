<%@page import="com.needconnect.dao.PartnerDAO"%>
<%@page import="com.needconnect.entity.Partner"%>

<jsp:useBean id="partner" class="com.needconnect.entity.Partner"></jsp:useBean>
<jsp:setProperty name="partner" property="*"></jsp:setProperty>

<%
    String methodType = request.getMethod();

    if (methodType.equalsIgnoreCase("POST")) {
        try {
            // 2. Use the DAO
            PartnerDAO dao = new PartnerDAO();
            
            // 3. Check credentials (using the email/password from the bean)
            Partner loggedInPartner = dao.loginPartner(partner.getEmail(), partner.getPassword());

            if (loggedInPartner != null) {
                // --- SUCCESS ---
                
                // Store the partner object in session
                session.setAttribute("currentPartner", loggedInPartner);
                
                // Set a flag for the UI (header) to know someone is logged in
                session.setAttribute("isPartnerLoggedIn", true);
                
                // Redirect to Home or a Partner Dashboard
                response.sendRedirect("../index.jsp"); 
            } else {
                // --- FAILURE ---
                session.setAttribute("errorMessage", "Invalid Email or Password. Please try again.");
                response.sendRedirect("../partner_login.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "System Error: " + e.getMessage());
            response.sendRedirect("../partner_login.jsp");
        }
    } else {
        response.sendRedirect("../partner_login.jsp");
    }
%>