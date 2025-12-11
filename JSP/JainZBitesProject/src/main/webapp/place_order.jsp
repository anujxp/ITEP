<%@page import="java.util.*"%>
<%@page import="com.info.jainzbitesproject.entity.*"%>
<%@page import="com.info.jainzbitesproject.dao.*"%>

<%
    Customer customer = (Customer) session.getAttribute("customer");
    List<Cart> cartItems = CartDAO.getByCustomer(customer.getId());

    if(cartItems.size() == 0){
        out.print("Cart is empty!");
        return;
    }

    Order order = new Order();
    order.setCustomer(customer);
    order.setStatus("Placed");
    order.setOrderDate(new java.util.Date().toString());

    double total = 0;
    List<OrderItem> items = new ArrayList<>();

    for (Cart c : cartItems) {
        OrderItem oi = new OrderItem();
        oi.setOrder(order);
        oi.setFood(c.getFood());
        oi.setQuantity(c.getQuantity());
        oi.setPrice(c.getPrice());

        items.add(oi);

        total += c.getPrice() * c.getQuantity();
    }

    order.setItems(items);
    order.setTotal_amt(total);

    OrderDAO.placeOrder(order);

    // clear cart
    for (Cart c : cartItems) {
        CartDAO.delete(c.getId());
    }

    response.sendRedirect("order_success.jsp?id=" + order.getId());
%>
