<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Aircraft</title>
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
	<form action="${pageContext.servletContext.contextPath}/updateAircraft" method ="POST">
		<h2>Update Aircraft Information:</h2>
		
		<div class="container">
			<label for="sel1">Enter the Aircraft ID you want to change:</label>
			<input type="text" placeholder="Enter Aircraft ID" name="aircraft_id">
			
			<br>
			<label for="usr">Airline ID:</label>
		 	 <input type="text" placeholder="Enter Airline ID " name ="airline_id">
		  	
			<br>
			<label for="sel1">Number of Economy Seats:</label> 
			<input type="text" placeholder="Enter Number of Economy Seats" name="total_seats_economy">
		
		  	<br>
		  	<label for="pwd">Number of First Seats:</label>
		  	<input type="text" placeholder="Enter Number of First Class Seats" name ="total_seats_first">
		  	
		  	<br>
		  	<label for="pwd">Number of Business Seats:</label>
		  	<input type="text" placeholder="Enter Number of Business Class Seats" name ="total_seats_business">
		  	
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