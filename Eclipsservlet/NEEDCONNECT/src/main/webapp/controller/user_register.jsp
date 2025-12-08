<%@page import="com.needconnect.dao.UserDAO"%>
<%@page import="com.needconnect.entity.User"%>

<jsp:useBean id="user" class="com.needconnect.entity.User"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>

<%
String method = request.getMethod();

if (method.equalsIgnoreCase("POST")) {
	try {
		UserDAO dao = new UserDAO();

		if (dao.checkEmailExists(user.getEmail())) {
	session.setAttribute("errorMessage", "Email is already registered! Please login.");

	response.sendRedirect("../user_register.jsp");
	return;
		}

		boolean isSaved = UserDAO.saveUser(user);
		if (isSaved) {
	session.setAttribute("msg", "Registration Successful! You can now login.");
	response.sendRedirect("../login.jsp");
		} else {

	session.setAttribute("errorMessage", "Registration failed due to a server error.");

	response.sendRedirect("../user_register.jsp");
		}

	} catch (Exception e) {
		e.printStackTrace();
		session.setAttribute("errorMessage", "Error: " + e.getMessage());
		response.sendRedirect("../user_register.jsp");
	}
} else {
	response.sendRedirect("../user_register.jsp");
}
%>