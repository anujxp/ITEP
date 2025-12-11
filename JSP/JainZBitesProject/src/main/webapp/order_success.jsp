<%@ include file="__header.jsp" %>
<%
  String id = request.getParameter("id");
%>
<div class="container center">
  <h2 style="color:#C1121F">Order Placed!</h2>
  <p>Your order id: <strong><%= id %></strong></p>
  <a class="btn-primary" href="order_list.jsp">View Orders</a>
</div>
<%@ include file="__footer.jsp" %>
