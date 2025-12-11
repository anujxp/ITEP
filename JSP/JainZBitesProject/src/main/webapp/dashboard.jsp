<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.info.jainzbitesproject.dao.FoodDAO" %>
<%@ page import="com.info.jainzbitesproject.entity.Food" %>

<%
    // Admin Auth Check (optional)
    // if (session.getAttribute("admin") == null) {
    //     response.sendRedirect("admin_login.jsp");
    //     return;
    // }

    List<Food> foods = FoodDAO.getAll();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - JainZBites</title>

    <!-- Bootstrap CSS (make sure it's included) -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <style>
        body {
            background: #f8f9fa;
        }
        .card img {
            height: 160px;
            object-fit: cover;
        }
        .header-actions a {
            margin-left: 8px;
        }
    </style>
</head>

<body>

    <%@ include file="__header.jsp" %>

    <div class="container mt-4">

        <div class="d-flex justify-content-between align-items-center">
            <h2 class="text-danger">Admin Dashboard</h2>

            <div class="header-actions">
                <a class="btn btn-primary" href="add_category.jsp">Add Category</a>
                <a class="btn btn-primary" href="add_food.jsp">Add Food</a>
                <a class="btn btn-primary" href="view_customers.jsp">Customers</a>
                <a class="btn btn-primary" href="admin_orders.jsp">Orders</a>
            </div>
        </div>

        <h4 class="mt-4 text-secondary">Sample Menu</h4>

        <div class="row mt-3">
            <% for (Food f : foods) { %>
                <div class="col-md-3 mb-4">
                    <div class="card shadow-sm">

                        <img class="card-img-top"
                             src="<%= (f.getImage() != null && !f.getImage().trim().isEmpty()) ? f.getImage() : "assets/placeholder.jpg" %>"
                             alt="Food Image">

                        <div class="card-body">
                            <h5 class="card-title"></h5>

                            <p class="text-muted mb-2">₹ <%= f.getPrice() %></p>

                            <div class="d-flex">
                                <a class="btn btn-danger btn-sm mr-2"
                                   href="controller/admin/delete_food_action.jsp?id=<%= f.getId() %>">
                                    Delete
                                </a>
                                <a class="btn btn-warning btn-sm"
                                   href="edit_food.jsp?id=<%= f.getId() %>">
                                    Edit
                                </a>
                            </div>
                        </div>

                    </div>
                </div>
            <% } %>
        </div>

    </div>

    <%@ include file="__footer.jsp" %>

    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
