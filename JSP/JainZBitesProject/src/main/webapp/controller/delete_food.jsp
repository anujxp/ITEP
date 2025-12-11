<%@page import="com.info.jainzbitesproject.dao.FoodDAO"%>

<%
    int foodId = Integer.parseInt(request.getParameter("id"));

    FoodDAO.delete(foodId);

    response.sendRedirect("../dashboard.jsp?msg=Food Deleted");
%>
