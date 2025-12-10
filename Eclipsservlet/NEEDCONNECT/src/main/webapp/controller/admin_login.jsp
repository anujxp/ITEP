<%@page import="com.needconnect.dao.AdminDAO"%>
<%@page import="com.needconnect.entity.Admin"%>

<jsp:useBean id="admin" class="com.needconnect.entity.Admin"></jsp:useBean>
<jsp:setProperty name="admin" property="*"></jsp:setProperty>

<%
    if (request.getMethod().equalsIgnoreCase("POST")) {
        AdminDAO dao = new AdminDAO();
        Admin loggedInAdmin = dao.loginAdmin(admin.getEmail(), admin.getPassword());
        
        if (loggedInAdmin != null) {
            session.setAttribute("currentAdmin", loggedInAdmin);
            response.sendRedirect("../admin_dashboard.jsp"); // We will create this next
        } else {
            session.setAttribute("errorMessage", "Invalid Admin Credentials");
            response.sendRedirect("../admin_login.jsp");
        }
    }
%>