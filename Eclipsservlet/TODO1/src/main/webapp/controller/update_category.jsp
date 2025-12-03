<%@page import="dao.CategoryDAO"%>

<jsp:useBean id="category" class="com.info.todo.entity.Category"></jsp:useBean>
<jsp:setProperty name="category" property="*"></jsp:setProperty>
<%
  if(CategoryDAO.update(category)){%>
	 <script>
	   window.alert("Category updated successfully..");
	   window.location.href="../view_category.jsp";
	 </script>  
  <%}
%>