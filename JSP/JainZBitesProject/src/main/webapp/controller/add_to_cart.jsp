<%@page import="com.info.jainzbitesproject.dao.CartDAO"%>

<%
   int cid = Integer.parseInt(request.getParameter("cid"));
   int pid = Integer.parseInt(request.getParameter("pid"));
   int qty = Integer.parseInt(request.getParameter("qty"));

   CartDAO.addToCart(cid, pid, qty);

   response.sendRedirect("../view_cart.jsp?cid=" + cid);
%>
