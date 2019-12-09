<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="com.flighttracker.Flight"%> 
<!DOCTYPE html>
<html>
<head>
<title>Profile Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>

<style>
form {
	text-align: center;
}

label {
	margin-right: 20px;
}

div span {
	position: absolute;
	right: 10;
}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand"
					href="${pageContext.servletContext.contextPath}/home">Flight
					Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a
						href="${pageContext.servletContext.contextPath}/profileCustomer"><span
							class="glyphicon glyphicon-user"></span> Customer Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="list-group" style="width: 75%;">
		Delivered

		<!-- Have to dynamically display flights -->
		<%ArrayList<Flight> flights = (ArrayList<Flight>)request.getAttribute("flightList"); %>
 		
		<div class="list-group" style="width: 75%;">
			<h2>Flights Found!</h2>
			<% for(Flight f:flights) { %>
			<a href="#" class="list-group-item list-group-item-action">
				<div>
					<h5><%= f.getDepartAirportId() %> - <%= f.getArriveAirportId() %> </h5>
				</div>
				<div>
					<h5><%= f.getDepartTime() %> - <%= f.getArriveTime() %> </h5>
				</div>
				<p><%=f.getAirlineId() %></p>
			</a> 
			<% } %>

		</div>
	</div>

	<%
		if ((session.getAttribute("user") == null)) {
	%>
	You are not logged in
	<br />
	<a href="login.jsp">Please Login</a>
	<%
		}
	%>


</body>
</html>