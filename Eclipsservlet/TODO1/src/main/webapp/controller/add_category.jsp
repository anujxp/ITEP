<%@page import="dao.CategoryDAO"%>
<jsp:useBean id="category" class="com.info.todo.entity.Category"></jsp:useBean>
<jsp:setProperty property="*" name="category"></jsp:setProperty>

<%
if (request.getMethod().equalsIgnoreCase("post")) {
	try {
		if(CategoryDAO.save(category)){%>
		<script>
           window.alert("Category saved successfully...");
           window.location.href="../add_category.jsp";
       </script>

<%}

	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException(e.getMessage());
	}
} else
	response.sendRedirect("../error.jsp");
%>