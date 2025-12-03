<%@page import="dao.AdminDAO"%>
<%@page import="com.info.todo.entity.Admin"%>
<jsp:useBean id="admin" class  = "com.info.todo.entity.Admin"></jsp:useBean>
<jsp:setProperty property="*" name="admin"></jsp:setProperty>
<%
String methodType = request.getMethod();
if( methodType.equalsIgnoreCase("POST")){
	try{
		admin = AdminDAO.authenticate(admin);
		if (admin != null )
			response.sendRedirect("../dashboard.jsp");
		else 
			out.print("Login failed ....");
	}
	catch(Exception e ){
		e.printStackTrace();
		
	}
}else
	out.print("can not handle get");
%>