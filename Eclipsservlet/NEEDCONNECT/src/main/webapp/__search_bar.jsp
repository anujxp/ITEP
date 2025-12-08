<%@page import="com.needconnect.dao.CategoryDAO"%>
<%@page import="com.needconnect.entity.Category"%>
<%@page import="java.util.List"%>

<%
    // Fetch categories for the dropdown
    CategoryDAO searchCatDao = new CategoryDAO();
    List<Category> searchCats = searchCatDao.getAllCategories();
    
    // Check if a search was already done (to keep the value selected)
    String selectedCity = request.getParameter("city");
    String selectedCatId = request.getParameter("category");
    if(selectedCity == null) selectedCity = "";
%>

<form action="view_listings.jsp" method="get">
    <div class="form-row align-items-end">
        
        <div class="col-md-5 mb-3 mb-md-0">
            <label class="font-weight-bold small text-muted text-uppercase">City / Area</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text bg-white"><i class="fa-solid fa-location-dot text-danger"></i></span>
                </div>
                <input type="text" name="city" class="form-control" placeholder="e.g. Indore" value="<%= selectedCity %>">
            </div>
        </div>

        <div class="col-md-4 mb-3 mb-md-0">
            <label class="font-weight-bold small text-muted text-uppercase">Type</label>
            <select name="category" class="form-control">
                <option value="">All Types</option>
                <%
                    if(searchCats != null){
                        for(Category c : searchCats){
                            // Check if this category was selected previously
                            String isSelected = (selectedCatId != null && selectedCatId.equals(String.valueOf(c.getId()))) ? "selected" : "";
                %>
                    <option value="<%= c.getId() %>" <%= isSelected %>><%= c.getName() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div class="col-md-3">
            <button type="submit" class="btn btn-warning btn-block font-weight-bold shadow-sm" style="height: 38px;">
                Search
            </button>
        </div>
    </div>
</form>