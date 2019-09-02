<!DOCTYPE html>
<%@page import="java.sql.*"%>
<%@page import="db.*"%>
<%
    String check = (String)session.getAttribute("signedIn");
    if(check==null || !check.equals("admin")){
        session.setAttribute("temp","display");
        response.sendRedirect("admin_login.jsp");
    }
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
<script type="text/javascript">
      function validate(){
        var bid=document.forms["form1"]["bid"].value;
        var num = document.forms["form1"]["num"].value;
        if(bid<=0){
          alert("Invalid Book ID");
          return false;
        }
        if(num<=0){
            alert("Invalid Quantity");
            return false;
        }
        if(confirm("Are you sure you want to submit?")){ return true; }
        else{ return false; }
      }
    </script>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="index.jsp"><img src="logo.jpg" class="logo"> UKP</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp" style="font-size: 30px;"> Welcome to Umeedo Ke Pankh,Admin</a>
      </li>
    </ul>
    <a href="logout.jsp" class="btn btn-warning" role="button">Log Out</a>
  </div>
</nav>
<div class="container-fluid adminwindowback">
	<div class="container-fluid adminwindowblack">
        <div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
                        <% DbConnect db = (DbConnect)application.getAttribute("dbc"); %>
			<div class="col-sm-8 adminwindowcol">
                            <% try{
                                    
                                    ResultSet rs = db.admintotal();
                                    rs.next();
                            %>
				<h1>Total Count: <%=rs.getInt(1)%></h1>
                                <% }
                                    catch(Exception ex){
                                        ex.printStackTrace();
                                     }%>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
			<div class="col-sm-8 adminwindowcol">
				<h1>Subject wise count</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-2 adminwindowcol"></div>
			<div class="col-sm-8 adminwindowcol">
                            <%
                                            try{
                                                
                                                ResultSet rs = db.adminall();
                                                
                                        %>
				<table class="contenttable">
					<tr>
						<th>BOOk ID</th>
						<th>Book Name</th>
						<th>In Stock</th>
					</tr>
                                        <%
                                            while(rs.next())
                                                {
                                        %>
                                        <tr>
                                            <td><%=rs.getInt("BID")%></td>
                                            <td><%=rs.getString("BOOKNAME")%></td>
                                            <td><%=rs.getInt("STOCK")%></td>
                                        </tr>
                                        <%
                                                }
                                            }
                                            catch(Exception ex){
                                                ex.printStackTrace();
                                            }
                                        %>
				</table>
			</div>
		</div>
      <center>
          <h3 style=" color: burlywood"><Strong> Distribute </strong></h3><br>
          <%
              String temp = (String)session.getAttribute("updateStock");
              if(temp!=null){
                  
          %>
          <h4 style=" color: red"><Strong> <%= temp%> </strong></h3><br>
          <%
              session.setAttribute("updateStock", null);
              }
          %>
      <form action="Distribute" name="form1" method="post" >
        <input type="number" name="bid" placeholder="BOOK ID" >
        <input type="number" name="num" placeholder="Quantity"><br><br>
        <input type="submit" value="Update" style="background: #3fc73ffa;border: green;padding: 5px;border-radius: 20%;" onclick="return validate()">
        <input type="reset" value="Clear" style="background: #fd1515fa;border: red;padding: 5px;border-radius: 20%;"><br><br>	
      </form>
      </center>
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