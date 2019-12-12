<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

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




<meta charset="ISO-8859-1">
<title>Add Information</title>
</head>
<body>
<form method="post" action="AddAircraftProcess.jsp">
Aircraft ID:<br>
<input type="text" name="aircraft_id">
<br>
Airline ID:<br>
<input type="text" name="airline_id">
<br>
Total Number of Economy Seats:<br>
<input type="text" name="total_seats_economy">
<br>
Total Number of First Class Seats:<br>
<input type="text" name="total_seats_first">
<br>
Total Number of Business Class Seats:<br>
<input type="text" name="total_seats_business">
<br><br>
<input type="submit" value="submit">
</form>
</body>
</html>