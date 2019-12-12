<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Airport</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

<style>
form {
	text-align: center;
	
}
label {
	margin-right: 20px;
}
div span{
    position:absolute;
    right:10;
}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/jsp/homeCustomerrep.jsp">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="${pageContext.servletContext.contextPath}/profileCustomerRep"><span class="glyphicon glyphicon-user"></span>
							Customer Representative Account 
						</a>
					</li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> 
							Logout 
						</a>	
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
<div class="list-group" style="width: 75%;">
	<form action="${pageContext.servletContext.contextPath}/updateAirport" method ="POST">
		<h2>Update Airport Information:</h2>
		
		<div class="container">
			<label for="sel1">Enter the Airport name you want to change:</label>
			<input type="text" placeholder="Enter Airport name" name="airport_name">
			
			<br>
			<label for="usr">Enter the new Airport name:</label>
		 	 <input type="text" placeholder="Enter new Airport name" name ="newairport_name">
			
		  	<br>
		  	<input type="submit" class="btn btn-success" value="Submit"/>
			
		</div>
	</form>	
</div>
  
	<%
    if ((session.getAttribute("user") == null)) {
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%}
%>


</body>
</html>
</body>
</html>