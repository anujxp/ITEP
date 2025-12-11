<%@page import="com.info.jainzbitesproject.dao.CustomerDAO"%>
<%
  String email = request.getParameter("email");
  String pwd = request.getParameter("password");
  com.info.jainzbitesproject.entity.Customer c = CustomerDAO.authenticate(new com.info.jainzbitesproject.entity.Customer(){{
      setEmail(email); setPassword(pwd);
  }});

  if(c!=null){
     session.setAttribute("customer", c);
     response.sendRedirect("../customer_dashboard.jsp");
  } else {
     response.sendRedirect("../customer_login.jsp?err=invalid");
  }
%>
