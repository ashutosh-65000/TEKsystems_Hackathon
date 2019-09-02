<!DOCTYPE html>
<%@page import="java.sql.*"%>
<%@page import="java.util.HashMap"%>
<%@page import="db.DbConnect"%>
<%
        String check = (String)session.getAttribute("signedIn");
        if(check==null || !check.equals("student"))
        {
            session.setAttribute("temp", "display");
            response.sendRedirect("student_login.jsp");
        }
        else{
    %>
<html>
<head>
	<title>Welcome Admin</title>
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
  <% 
      DbConnect db = (DbConnect)application.getAttribute("dbc");
          HashMap hm = (HashMap)session.getAttribute("UserDetails");
    
  %>
  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp" style="font-size: 30px;"> Welcome to Umeedo Ke Pankh,<%=hm.get("name")%></a>
      </li>
    </ul>
    <a href="logout.jsp" class="btn btn-warning" role="button">Log Out</a>
  </div>
</nav>
<div class="container-fluid adminwindowback">
	<div class="container-fluid adminwindowblack"><br><br>
        <center><img src="GetPhoto?uid=<%=hm.get("uid")%>" alt="pic not found" width="120" height="150"></center>
            <div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
			<div class="col-sm-8 adminwindowcol">
                            <%try{
                                ResultSet rs = db.totalall((Integer)hm.get("uid"));
                                if(rs.next()){
                            %>
				<h1>Total Books Submitted: <%=rs.getInt(1)%></h1>
                            <%
                                }
                            }catch(Exception ex){ex.printStackTrace();}%>
                            <%  
                                String s = (String)session.getAttribute("donate");
                                if(s!=null){
                            %>
                            <marquee><strong><center><%=s%></center></strong></marquee>
                            <%}
                                session.setAttribute("donate",null);
                            %>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
			<div class="col-sm-8 adminwindowcol">
				<h3>Donate Book</h3>
				<form action="BookInsert">
                                    <h5>Enter name of the book:</h5>
                                    <input type="text" name="name" placeholder="Book Name"><br><br>
                                    <input type="number" name="count" placeholder="Count"><br><br>
                                    <input type="submit" name="submit" value="Submit" style="background: #3fc73ffa;border: green;padding: 5px;border-radius:20%;">
                                    <input type="reset" name="reset" value="Reset" style="background: #fd1515fa;border: red;padding: 5px;border-radius: 20%;">
				</form>
			</div>
		</div>
                
		<div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
			<div class="col-sm-8 adminwindowcol">
				<h3>Previous Donations</h3>
				<table class="contenttable">
					<tr>
						<th>S.no</th>
						<th>Book Name</th>
						<th>Count</th>
					</tr>
                                        <%
                                            try{
                                                ResultSet rs = db.totalwise((Integer)hm.get("uid"));
                                                int i = 0;
                                                while(rs.next())
                                                {
                                                    i++;
                                        %>
					<tr>
						<td><%=i%></td>
						<td><%=rs.getString(1)%></td>
						<td><%=rs.getInt(2)%></td>
					</tr>
                                        <%
                                                }
                                            }
                                            catch(Exception ex){ex.printStackTrace();}
                                        %>
				</table>
			</div>
		</div>
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
<% 
    }
%>