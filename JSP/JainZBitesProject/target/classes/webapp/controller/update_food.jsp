<%@page import="com.info.jainzbitesproject.dao.FoodDAO"%>
<%@page import="com.info.jainzbitesproject.entity.Food"%>
<%@page import="com.info.jainzbitesproject.entity.Category"%>

<jsp:useBean id="food" class="com.info.jainzbitesproject.entity.Food"/>
<jsp:setProperty name="food" property="*"/>

<%
    int catId = Integer.parseInt(request.getParameter("categoryId"));
    Category c = new Category();
    c.setId(catId);

    food.setCategory(c);

    FoodDAO.update(food);

    response.sendRedirect("../dashboard.jsp?msg=Food Updated");
%>
