
<%
    session.invalidate();
    session = request.getSession(true);
    session.setAttribute("msg", "You have successfully logged out.");
    response.sendRedirect("../login.jsp");
%>