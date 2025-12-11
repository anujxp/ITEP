<%@ include file="__header.jsp" %>
<%@page import="com.info.jainzbitesproject.dao.FoodDAO"%>
<%@page import="java.util.List"%>

<%
 List<com.info.jainzbitesproject.entity.Food> foods = FoodDAO.getAll();
%>

<div class="container">
  <div style="display:flex;justify-content:space-between;align-items:center">
    <h2 style="color:#C1121F">Welcome to JainZbites</h2>
    <div>
      <a class="btn-primary" href="view_cart.jsp">My Cart</a>
    </div>
  </div>

  <h4 style="margin-top:18px">Menu</h4>
  <div class="card-grid" style="margin-top:12px">
    <% for(var f: foods){ %>
      <div class="food-card">
      <!--    <img src="<%= (f.getImage()!=null?f.getImage():'assets/placeholder.jpg') %>" alt="img missing"/> -->
        <h4 style="margin:8px 0"><%= f.getName() %></h4>
        <div class="small-muted">₹ <%= f.getPrice() %></div>
        <div style="margin-top:10px">
          <form action="controller/customer/add_to_cart_action.jsp" method="post">
            <input type="hidden" name="pid" value="<%=f.getId()%>" />
            <input type="number" name="qty" value="1" min="1" style="width:70px;padding:6px;border-radius:6px;border:1px solid #ddd"/>
            <button class="btn-primary" style="margin-left:8px;">Add to Cart</button>
          </form>
        </div>
      </div>
    <% } %>
  </div>
</div>

<%@ include file="__footer.jsp" %>
