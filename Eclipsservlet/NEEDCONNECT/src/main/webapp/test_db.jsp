<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.needconnect.util.Util" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Database Connection Test</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="container mt-5">
    <div class="card shadow">
        <div class="card-header bg-dark text-white">
            <h3>Database Connection Status</h3>
        </div>
        <div class="card-body">
            <%
                EntityManager em = null;
                try {
                    // 1. Attempt to get the Entity Manager
                    // This triggers the static block in Util.java, which starts Hibernate
                    out.println("<p class='text-primary'>1. Attempting to start Hibernate...</p>");
                    
                    em = Util.getEntityManager();
                    
                    if(em != null && em.isOpen()) {
            %>
                        <div class="alert alert-success">
                            <h4><i class="fa fa-check"></i> Connection Successful!</h4>
                            <p>Hibernate has started. Check your MySQL Workbench/Client now.</p>
                            <p>You should see the following tables:</p>
                            <ul>
                                <li><strong>users</strong></li>
                                <li><strong>partners</strong></li>
                                <li><strong>categories</strong></li>
                                <li><strong>listings</strong></li>
                                <li><strong>bookings</strong></li>
                            </ul>
                        </div>
            <%
                    } else {
                        out.println("<div class='alert alert-warning'>EntityManager is null but no exception thrown.</div>");
                    }
                    
                } catch (Exception e) {
            %>
                    <div class="alert alert-danger">
                        <h4>Connection Failed!</h4>
                        <p><strong>Error:</strong> <%= e.getMessage() %></p>
                        <hr>
                        <pre><%= e.toString() %></pre>
                    </div>
            <%
                    e.printStackTrace(); // Print to Eclipse console as well
                } finally {
                    if(em != null) {
                        em.close();
                    }
                }
            %>
            
            <a href="index.jsp" class="btn btn-primary mt-3">Go to Home</a>
        </div>
    </div>
</div>

</body>
</html>