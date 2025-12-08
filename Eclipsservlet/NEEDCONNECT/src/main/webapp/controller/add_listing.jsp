<%@page import="com.needconnect.dao.ListingDAO"%>
<%@page import="com.needconnect.dao.CategoryDAO"%>
<%@page import="com.needconnect.entity.Listing"%>
<%@page import="com.needconnect.entity.Partner"%>
<%@page import="com.needconnect.entity.Category"%>

<jsp:useBean id="listing" class="com.needconnect.entity.Listing"></jsp:useBean>
<jsp:setProperty name="listing" property="*"></jsp:setProperty>

<%
    String method = request.getMethod();

    if(method.equalsIgnoreCase("POST")){
        try {
          
            Partner currentPartner = (Partner) session.getAttribute("currentPartner");
            if(currentPartner == null) {
                response.sendRedirect("../partner_login.jsp");
                return;
            }

            
            String catIdStr = request.getParameter("categoryId");
            int catId = Integer.parseInt(catIdStr);
            
           
            CategoryDAO catDao = new CategoryDAO();
            Category selectedCategory = catDao.getCategoryById(catId);

            
            listing.setPartner(currentPartner);
            listing.setCategory(selectedCategory);
            ListingDAO dao = new ListingDAO();
            boolean isSaved = dao.saveListing(listing);
            
            if(isSaved){
%>
                <script type="text/javascript">
                    alert("Room posted successfully! Read
                    		y for the next one.");
                    window.location.href = "../add_listing.jsp"; // Redirects back to the form
                </script>
<%
            } else {
                // --- FAILURE ---
%>
                <script type="text/javascript">
                    alert("Failed to save listing. Please try again.");
                    window.history.back(); 
                </script>
<%
            }
            
        } catch(Exception e){
            e.printStackTrace();
%>
            <script type="text/javascript">
                alert("Error: <%= e.getMessage() %>");
                window.history.back();
            </script>
<%
        }
    } else {
        response.sendRedirect("../add_listing.jsp");
    }
%>