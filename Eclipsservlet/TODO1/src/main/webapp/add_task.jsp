<%@page import="dao.CategoryDAO"%>
<%@page import="com.info.todo.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="__bootstrap_link.jsp"%>
</head>
<body>
	<%@ include file="__header.jsp"%>
	<%
	List<Category> categoryList = CategoryDAO.getList();
	%>
	<div class="container mt-3">
		<form action="controller/add_task.jsp">
			<div class="form-group">
				<select class="form-control" name="categoryId">
					<option value="0">select</option>
					<%
					for (Category c : categoryList) {
					%>
					<option value="<%=c.getId()%>"><%=c.getCategoryName()%></option>
					<%
					}
					%>
				</select>
			</div>
			<div class="form-group">
				<textarea placeholder="Enter task description" class="form-control"
					name="description"></textarea>
			</div>
			<div class="form-group">
				<select class="form-control" name="priority">
					<option value="High">High</option>
					<option value="Medium">Medium</option>
					<option value="Low">Low</option>
				</select>
			</div>
			<div class="form-group">
				<input name="endDate" type="date" class="form-control" />
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-outline-success">Add task</button>
			</div>
		</form>
	</div>
</body>
</html>