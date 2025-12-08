<%@page import="com.needconnect.dao.PartnerDAO"%>
<%@page import="com.needconnect.entity.Partner"%>

<jsp:useBean id="partner" class="com.needconnect.entity.Partner"></jsp:useBean>

<jsp:setProperty name="partner" property="*"></jsp:setProperty>

<%
String method = request.getMethod();

// Only process POST requests
if (method.equalsIgnoreCase("POST")) {
	try {
		// 3. Initialize DAO
		PartnerDAO dao = new PartnerDAO();

		// 4. Save to Database
		boolean isSaved = dao.savePartner(partner);

		if (isSaved) {
	// --- Success ---
	session.setAttribute("msg", "Registration Successful! Please Login.");
	
	response.sendRedirect("../partner_login.jsp");
		} else {
	// --- Failure (e.g., Duplicate Email) ---
	session.setAttribute("errorMessage", "Registration Failed. Email might be already in use.");
	response.sendRedirect("../partner_register.jsp");
		}
	} catch (Exception e) {
		// --- Error Handling ---
		e.printStackTrace();
		session.setAttribute("errorMessage", "System Error: " + e.getMessage());
		response.sendRedirect("../partner_register.jsp");
	}
} else {
	// Redirect if someone tries to access this page via GET
	response.sendRedirect("../partner_register.jsp");
}
%>