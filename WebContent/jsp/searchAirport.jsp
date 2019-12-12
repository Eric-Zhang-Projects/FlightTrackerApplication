<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<title>Search Flights</title>
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

	<!-- <form class="form-inline" action="${pageContext.servletContext.contextPath}/CREditReservation" method="POST">-->
	<!-- ${pageContext.servletContext.contextPath}/CREditReservation"-->
	<form class="form-inline" action="${pageContext.servletContext.contextPath}/allFlightsAirport" method="POST">
		<h2>Search For Flights by Airport ID</h2>
		<hr>
		<div class="form-group">
			<input type="text" placeholder="Enter Airport ID" name="airport_id" required> 
			
		</div>
		
		<input type='submit' class="btn btn-success" value="Search!"/>

	</form>
		


	<%
    if ((session.getAttribute("user") == null)) {
%>
You are not logged in<br/>
<a href="index.jsp">Please Login</a>
<%}
%>

</body>
</html>