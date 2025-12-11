<%@page import="com.info.jainzbitesproject.dao.AdminDAO"%>
<%@page import="com.info.jainzbitesproject.entity.Admin"%>

<%
String methodType = request.getMethod();

if(methodType.equalsIgnoreCase("POST")) {


    String email =request.getParameter("email");
    String password = request.getParameter("password");

  
    Admin admin = new Admin();
    admin.setEmail(email);
   admin.setPassword(password);

    try {
     
       Admin loggedInAdmin = AdminDAO.authenticate(admin);

        if (loggedInAdmin != null) {
            response.sendRedirect("../dashboard.jsp");
        } else {
            out.print("Login failed...");
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

} else {
    out.print("Can not handle GET");
}
%>
