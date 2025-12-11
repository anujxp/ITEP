<%@ include file="__header.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.info.jainzbitesproject.dao.CustomerDAO"%>

<%
 List<com.info.jainzbitesproject.entity.Customer> customers = CustomerDAO.getAll();
%>

<div class="container">
  <h3 style="color:#C1121F">Customers</h3>
  <table class="table" style="margin-top:12px">
    <tr><th>ID</th><th>Name</th><th>Email</th><th>Phone</th></tr>
    <% for(var c: customers){ %>
      <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getName() %></td>
        <td><%= c.getEmail() %></td>
        <td><%= c.getPhone() %></td>
      </tr>
    <% } %>
  </table>
</div>
<%@ include file="__footer.jsp" %>
