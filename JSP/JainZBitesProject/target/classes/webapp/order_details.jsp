<%@page import="com.info.jainzbitesproject.entity.*"%>
<%@page import="com.info.jainzbitesproject.dao.*"%>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    // Order order = OrderDAO.getById(id);
    Order order = new Order();
%>

<h2>Order Details - ID: <%= %></h2>

<table border="1">
<tr>
    <th>Food</th>
    <th>Qty</th>
    <th>Price</th>
</tr>

<%
for(OrderItem oi : order.getItems()){
%>
<tr>
    <td><%= oi.getFood().getName() %></td>
    <td><%= oi.getQuantity() %></td>
    <td><%= oi.getPrice() %></td>
</tr>
<% } %>
</table>

<h3>Total: ₹ <%= order.getTotal_amt() %></h3>
