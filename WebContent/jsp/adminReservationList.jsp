<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Profile Page</title>
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
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/jsp/homeAdmin.jsp">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.servletContext.contextPath}/jsp/profileAdmin.jsp"><span class="glyphicon glyphicon-user"></span>
							Administrative Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>
	
<div class="list-group" style="width: 75%;">
	<form action="${pageContext.servletContext.contextPath}/adminReservationList" method ="POST">
		<h2>Find Reservations</h2>
		
		<div class="container">
			<label for="sel1">Produce a list of reservation by:</label><br>
			<input type="text" placeholder="Enter Information" name="type" required><br>
			<input type="radio" name="radioType" id="typeF" value="Flight" required/>Flight Number
			<input type="radio" name="radioType" id="typeC" value="Customer" required/>Username
		  	<br>
		  	<input type="submit" class="btn btn-success" value="Submit"/>
			
		</div>
	</form>	
</div>
  
</body>
</html>
</body>
</html>