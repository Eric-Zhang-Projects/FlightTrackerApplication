<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="com.flighttracker.Flight"%> 
<!DOCTYPE html>
<html>
<head>
<title>Profile Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>
<script src="https://www.w3schools.com/lib/w3.js"></script>

<script>
	//make each row click-able
	$(document).ready(function($) {
	    $(".item").click(function() {
	        window.document.location = $(this).data("href");
	    });
	});
</script>
<script>
$(document).ready(function(){
	  $("#myInput").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#miTable tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	});
</script>
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
	#myTable tr th{
	  background-color: #eee;
	}
	.item{
		cursor:pointer;
		border: 1px solid #ddd;
		margin-top: -1px; /* Prevent double borders */
		background-color: #f6f6f6;
		text-decoration: none;
		color: black;
	}
	.item:hover:not(.header){
		background-color: #bbb;
	}
</style>
</head>

<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/home">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.servletContext.contextPath}/profileCustomer"><span class="glyphicon glyphicon-user"></span>
							Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="list-group" style="width: 75%;">
	<h2>Flights Found!</h2>
	<label> Type of flight you are searching for: 
	
	<textarea cols="20" rows="1" name="isRoundTrip"><%=request.getAttribute("flightType")%></textarea>
	<textarea cols="20" rows="1" name="isRoundTrip"><%=request.getAttribute("flexibility")%></textarea>
	
	</label>
	
	<hr>
	
	<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for names.." title="Type in a name">	
	<table id="myTable" class="table" style="width: 75%">
		<tr>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(1)')" style="cursor:pointer">Airline</th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(2)')" style="cursor:pointer">Flight Number</th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(3)')" style="cursor:pointer">Number of stops</th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(4)')" style="cursor:pointer">Departure Time</th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(5)')" style="cursor:pointer">Arrival Time</th>
			<th onclick="w3.sortHTML('#myTable', '.item', 'td:nth-child(6)')" style="cursor:pointer">Price starting from:</th>
		</tr>
		<tbody id ="miTable">
		<%ArrayList<Flight> flights = (ArrayList<Flight>)request.getAttribute("flightList");
		
		for(Flight f:flights) { %>
				<tr class="item" data-href="${pageContext.servletContext.contextPath}/bookFlight?flight_number=<%=f.getFlightNumber()%>&usernameToReserve=<%=request.getAttribute("usernameToReserve") %>">
					<td><%= f.getAirlineId() %></td>
					<td><%= f.getFlightNumber() %></td>
					<td><%= f.getStops() %></td>
					<td><%= f.getDepartTime() %></td>
					<td><%= f.getArriveTime() %></td>
					<td><%= f.getFareEconomy() %></td>
				</tr>
		<% } %>
		</tbody>
	</table>
	</div>
</body>
</html>