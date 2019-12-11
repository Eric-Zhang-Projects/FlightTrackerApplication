<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import= "java.util.HashMap"%>
<%@page import="com.flighttracker.Flight"%> 


<!DOCTYPE html>
<html>
<head>
<title>Search Flights</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://www.w3schools.com/lib/w3.js"></script>

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
				<a class="navbar-brand" href=jsp/homeAdmin.jsp>Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href=jsp/homeAdmin.jsp><span class="glyphicon glyphicon-user"></span>
							Administrative Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<table id="myTable" class="table" style="width: 60%">
		<tr>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(1)')" style="cursor:pointer"><%= request.getAttribute("radioType") %></th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(2)')" style="cursor:pointer">Revenue</th>
		</tr>
		<% HashMap<Integer, Integer> flights = (HashMap<Integer, Integer>)request.getAttribute("flights");%>
		<% for(HashMap.Entry<Integer,Integer> f: flights.entrySet()){ %>
		<tr class = "item">
			<td><%= f.getKey() %></td>
			<td><%= f.getValue() %></td>
		</tr>
		<% } %>
		<tr>
			<th>Total:</th>
			<td><%= request.getAttribute("totalFare")%></td>
		</tr>
	</table>
	<a href="">Back</a>


<%if ((session.getAttribute("user") == null)) {%>
	You are not logged in<br/>
	<a href="index.jsp">Please Login</a>
<%}%>

</body>
</html>