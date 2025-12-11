<%@page import="com.info.jainzbitesproject.entity.Order"%>
<%@page import="com.info.jainzbitesproject.dao.OrderDAO"%>

<%
   int cid = Integer.parseInt(request.getParameter("cid"));
	Order order = OrderDAO.getById(cid);
	order.setId(cid);
   OrderDAO.placeOrder(order);

   response.sendRedirect("../order_success.jsp");
%>
