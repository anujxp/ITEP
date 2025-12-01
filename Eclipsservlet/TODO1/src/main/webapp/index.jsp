


<%@page import="com.info.todo.util.JPAUtil"%>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

  <style>
     .form-container{
       width: 50%;
       min-height: 30vh;
       box-shadow: 10px 10px 10px 10px grey;
     }
     .form-header{
       width:100%;
       height: 50px;
     }
  </style>
</head>
<%JPAUtil.getFactory(); %>

<body>
     <div class="container d-flex justify-content-center align-items-center" style="height:100vh !important;">
        <div class="form-container">
          <div class="form-header bg-danger d-flex align-items-center justify-content-center">
              <h4 class="text-white text-center">Sign In</h4>
          </div>
          <form action="controller/admin_login.jsp" method="post" class="p-3">
              <div class="form-group">
                <input type="email" name="email" class="form-control" placeholder="Enter email id"/>
              </div>
              <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="Enter password"/>
              </div>                        
              <div class="mt-2">
                <button type="submit" class="btn btn-secondary text-white">Submit</button>
              </div>
          </form>
        </div>
     </div>
</body>
</html>