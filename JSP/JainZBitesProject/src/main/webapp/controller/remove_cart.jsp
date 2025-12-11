<%@page import="com.info.jainzbitesproject.dao.CartDAO"%>

<%
   int id = Integer.parseInt(request.getParameter("id"));
   int cid = Integer.parseInt(request.getParameter("cid"));

  //CartDAO.remove(id);

   response.sendRedirect("../view_cart.jsp?cid=" + cid);
%>
