<!DOCTYPE html>
<% String signedIn = (String)session.getAttribute("signedIn");
    if(signedIn!=null && signedIn.equals("admin"))
        response.sendRedirect("adminwindow.jsp");
%>
<html>
<head>
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="style.css">
	<link rel="stylesheet" type="text/css" href="css/font-awesome.css">
	<meta name="viewport" content="width=device-width,inital-scale=1.0">
	<script src="js/jquery-3.3.1.slim.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp"><img src="logo.jpg" class="logo"> UKP</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="admin_login.jsp">Admin</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="student_login.jsp">Student</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>
  </div>
</nav>
<div class="container-fluid">
	<div class="row loginbox">
    <div class="col-sm-4"></div>
		<div class="col-sm-4"> 
      <h2 class="logintop"><i class="fa fa-sign-in" aria-hidden="true"><Strong>Admin Log In </strong></i></h2>
      <% String s1 = (String)session.getAttribute("signedIn");
         String s2 = (String)session.getAttribute("temp");
        if((s1==null || !s1.equals("admin")) && s2!=null)
       { %>
      <h2><p style="color:red"><Strong>Please Login First</strong></p></h2>
      <%
          session.setAttribute("temp", null);
          }
      %>
      
       <% String s = (String)session.getAttribute("LoginIn");
        if(s!=null)
       { %>
      <h2><p style="color:red"><Strong><%= s %></strong></p></h2>
      <%
          session.setAttribute("LoginIn",null);
          }
      %>
      <form action="Login" name="form" method="post" class="logininput">
          <input type="hidden" name="whocalling" value="admin_login">
          <input type="email" name="email" placeholder="Email" class="logininputbox"><br>
      <input type="password" name="password" placeholder="Password" class="logininputbox"><br>
	    <input type="submit" value="Login" style="background: #3fc73ffa;border: green;padding: 5px;border-radius: 20%;"><br><br>
		</form></div>
    <div class="col-sm-4"></div>
	</div>
</div>
      <footer style="background-color: burlywood">
	<div class="row foot">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<center><h3>Follow Us :
		<i class="fa fa-facebook-official" aria-hidden="true"></i>
		<i class="fa fa-twitter" aria-hidden="true"></i>
		<i class="fa fa-instagram" aria-hidden="true"></i></h3></center> 
		</div>
 		<div class="col-sm-4"></div>
	</div>
	<div class="row foot">
		<div class="col-sm-4"></div>
		<div class="col-sm-4">
			<center> <h6>Copyright<i class="fa fa-copyright" aria-hidden="true"></i> 2019 UKP.All Rights Reserved</h6> </center> 
		</div>
 		<div class="col-sm-4"></div>
	</div>
</footer>      
</body>
</html>