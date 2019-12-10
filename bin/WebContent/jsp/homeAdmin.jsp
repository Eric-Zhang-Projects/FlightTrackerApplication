<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Account</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

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
				<a class="navbar-brand" href="#">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.servletContext.contextPath}/profileAdmin"><span class="glyphicon glyphicon-user"></span>
							Administrative Account </a></li>
					<li><a href='logout.jsp'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>
	
<div class="list-group" style="width: 75%;" >
	<a href="${pageContext.servletContext.contextPath}/jsp/CRMakeReservation.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>Make Flight Reservation For Customer</h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/jsp/CREditReservation.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>Edit Flight Reservation For Customer</h5>
			</div>			
	</a>
	<a href="jsp/CREditFlights.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>Edit Information for Aircrafts, Airports, and Flights</h5>
			</div>
	</a>
	<a href="jsp/CRWaitlist.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>Get Flight Waiting Lists</h5>
			</div>			
	</a>
		
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