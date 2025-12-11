<%@ include file="__header.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.info.jainzbitesproject.dao.CategoryDAO"%>

<%
 List<com.info.jainzbitesproject.entity.Category> cats = CategoryDAO.getAll();
%>

<div class="container">
  <h3 style="color:#C1121F">Add Food</h3>
  <form action="controller/add_food.jsp" method="post" enctype="multipart/form-data" style="margin-top:12px">
    <div><label>Name</label><br><input type="text" name="name" required></div>
    <div><label>Price</label><br><input type="number" step="0.01" name="price" required></div>
    <div><label>Description</label><br><textarea name="description" rows="3"></textarea></div>
    <div><label>Image URL</label><br><input type="text" name="image" alt="image error"></div>
    <div><label>Category</label><br>
      <select name="categoryId" required>
        <% for(var c: cats){ %>
          <option value="<%=c.getId()%>"><%=c.getName()%></option>
        <% } %>
      </select>
    </div>
    <div style="margin-top:10px"><button class="btn-primary">Add Food</button></div>
  </form>
</div>
<%@ include file="__footer.jsp" %>
