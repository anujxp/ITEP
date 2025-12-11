<%@page import="com.info.jainzbitesproject.dao.CustomerDAO"%>
<%@page import="com.info.jainzbitesproject.entity.Customer"%>

<jsp:useBean id="cust" class="com.info.jainzbitesproject.entity.Customer"/>
<jsp:setProperty name="cust" property="*"/>

<%
   CustomerDAO.authenticate(cust);
   response.sendRedirect("../customer_list.jsp");
%>
