<jsp:useBean id "admin" class = "com.info.todoapp.Admin"></jsp:useBean>
<jsp:setProperty name  = "admin" property"*"></jsp:setProperty>
<%
String methodType = request.getMethod();

if(methodType.equalsIgnoreCase("POST")){
try{
admin != null)
 response.sendRedirect("../dashboard.jsp")
 if(admin != null)
 response.sendRedirect("../dashboard.jsp");
 else
 {
 out.print("Login failed ....");
 }
 catch( Exception e ){
 e.printStacktrace();

 }
 else
 out.print("caan not handle GET");
 %>
