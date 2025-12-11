<%@page import="java.util.List"%>
<%@page import="com.info.jainzbitesproject.entity.Cart"%>
<%@page import="com.info.jainzbitesproject.dao.CartDAO"%>
<%@page import="com.info.jainzbitesproject.entity.Customer"%>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    List<Cart> cartList = CartDAO.getByCustomer(customer.getId());

    double total = 0;
%>

<h2>Your Cart</h2>

<table border="1">
    <tr>
        <th>Food</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Remove</th>
    </tr>

    <%
        for (Cart c : cartList) {
            total += c.getPrice() * c.getQuantity();
    %>
    <tr>
        <td><%= c.getFood().getName() %></td>
        <td><%= c.getQuantity() %></td>
        <td><%= c.getPrice() %></td>
        <td><a href="remove_cart.jsp?id=<%= c.getId() %>">X</a></td>
    </tr>
    <% } %>
</table>

<h3>Total Amount: ₹ <%= total %></h3>

<a href="place_order.jsp">Place Order</a>
