<%@page import="com.info.jainzbitesproject.dao.CustomerDAO"%>
<%@page import="com.info.jainzbitesproject.entity.Customer"%>

<jsp:useBean id="c" class="com.info.jainzbitesproject.entity.Customer"/>
<jsp:setProperty name="c" property="*" />

<%
  // email check (uses CustomerDAO.emailExists or similar)
  if(CustomerDAO.isEmailExists(c.getEmail())){
     request.setAttribute("msg","Email already registered");
     request.getRequestDispatcher("../../register.jsp").forward(request,response);
     return;
  }

       CustomerDAO.authenticate(c);
  response.sendRedirect("../../customer_login.jsp?msg=registered");
%>
