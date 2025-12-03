<jsp:useBean id="task" class = "com.info.todo.entity.Task"></jsp:useBean>
<jsp:setProperty property="*" name="task"></jsp:setProperty>
<%
if(request.getMethod())