<%@page import="com.needconnect.dao.UserDAO"%>
<%@page import="com.needconnect.entity.User"%>

<jsp:useBean id="user" class="com.needconnect.entity.User"></jsp:useBean>
<jsp:setProperty name="user" property="*"></jsp:setProperty>

<%
    String methodType = request.getMethod();

    if (methodType.equalsIgnoreCase("POST")) {
        try {
            UserDAO dao = new UserDAO();
            User loggedInUser = dao.loginUser(user.getEmail(), user.getPassword());
            
            if (loggedInUser != null) {
                // SUCCESS
                session.setAttribute("currentUser", loggedInUser);
                session.setAttribute("isLoggedIn", true);
                
                // Redirect to Home (Go UP one folder)
                response.sendRedirect("../index.jsp"); 
            } else {
                // FAILURE
                session.setAttribute("errorMessage", "Invalid Email or Password");
                
                // Redirect back to Login (Go UP one folder)
                response.sendRedirect("../login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMessage", "System Error: " + e.getMessage());
            response.sendRedirect("../login.jsp");
        }
    } else {
        response.sendRedirect("../login.jsp");
    }
%>