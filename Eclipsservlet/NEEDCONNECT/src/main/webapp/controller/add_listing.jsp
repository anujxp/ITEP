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
            // 2. Security Check: Ensure a Partner is logged in
            Partner currentPartner = (Partner) session.getAttribute("currentPartner");
            if(currentPartner == null) {
                response.sendRedirect("../partner_login.jsp");
                return;
            }

            // 3. Retrieve the Category ID manually (since it's a relationship, not a direct field)
            String catIdStr = request.getParameter("categoryId");
            int catId = Integer.parseInt(catIdStr);
            
            // 4. Fetch the full Category Object from Database
            CategoryDAO catDao = new CategoryDAO();
            Category selectedCategory = catDao.getCategoryById(catId);

            // 5. Set the Relationships (Partner & Category) in the Listing object
            listing.setPartner(currentPartner);
            listing.setCategory(selectedCategory);
            
            // 6. Save the Listing using DAO
            ListingDAO dao = new ListingDAO();
            boolean isSaved = dao.saveListing(listing);
            
            if(isSaved){
                // --- SUCCESS ---
                // Shows alert and reloads the same page to allow adding another room
%>
<script type="text/javascript">
                    alert("Room posted successfully! It is now Pending Admin Approval.");
                    window.location.href = "../add_listing.jsp"; 
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
        // If accessed directly via GET, send them back to the form
        response.sendRedirect("../add_listing.jsp");
    }
%>