<%@page import="com.info.jainzbitesproject.entity.Customer"%>
<%@page import="com.info.jainzbitesproject.entity.Food"%>
<%@page import="com.info.jainzbitesproject.entity.Cart"%>
<%@page import="com.info.jainzbitesproject.dao.FoodDAO"%>
<%@page import="com.info.jainzbitesproject.dao.CartDAO"%>

<%
    int foodId = Integer.parseInt(request.getParameter("foodId"));
    Customer customer = (Customer) session.getAttribute("customer");

    Food food = FoodDAO.getById(foodId);

    Cart cart = new Cart();
    cart.setCustomer(customer);
    cart.setFood(food);
    cart.setQuantity(1);
    cart.setPrice(food.getPrice());

    CartDAO.add(cart);

    response.sendRedirect("view_cart.jsp");
%>
