<%@ include file="__header.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.info.jainzbitesproject.dao.OrderDAO"%>

<%
 List<com.info.jainzbitesproject.entity.Order> orders = OrderDAO.getAll();
%>

<div class="container">
  <h3 style="color:#C1121F">All Orders</h3>
  <table class="table" style="margin-top:12px">
    <tr><th>ID</th><th>Customer</th><th>Date</th><th>Total</th><th>Status</th><th>Action</th></tr>
    <% for(var o: orders){ %>
      <tr>
        <td><%= o.getId() %></td>
        <td><%= o.getCustomer().getName() %></td>
        <td><%= o.getOrderDate() %></td>
        <td>₹ <%= o.getTotal_amt() %></td>
        <td><%= o.getStatus() %></td>
        <td><a class="btn-primary" href="order_details.jsp?id=<%=o.getId()%>">View</a></td>
      </tr>
    <% } %>
  </table>
</div>
<%@ include file="__footer.jsp" %>
