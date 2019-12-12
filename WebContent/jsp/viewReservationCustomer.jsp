<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "com.flighttracker.TicketObject" %>


<!DOCTYPE html>
<html>
<head>
<title>Search Flights</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<script>
function validUpdate(){
	alert ( "Update Successful");
	return;
}
</script>

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
	<div class="container" style="background-color: #f1f1f1 width: 30%" align="center">
			<a href ="${pageContext.servletContext.contextPath}/viewAllReservationsCustomer">Back</a>
	</div>
<form class="form-inline" action="${pageContext.servletContext.contextPath}/viewReservationCustomer" method="POST">
	
	<table class="table" style="width: 60%">
		<thead>
			<tr>
				<th scope="col">Field</th>
				<th scope="col">Current Information</th>
			</tr>
		</thead>
		<tbody>
		<!-- Use Eric's Edit Customer Rep JSp to create this page -->
			<% TicketObject info = (TicketObject)request.getAttribute("ticket");%>
			<tr>
				<th scope="row">Username</th>
				<td><%=info.getUsername()%></td>
			</tr>
			<tr>
				<th scope="row">Ticket Number</th>
				<td class = "ticket_number"><%=info.getNumber()%></td>
				<td><input type= "hidden" name ="ticket_number" value = <%=info.getNumber()%>></td>
			</tr>
			<tr>
				<th scope="row">Round Trip</th>
				<td><%=info.getRound_trip()%></td>
			</tr>
			<tr>
				<th scope="row">Booking Fee</th>
				<td><%=info.getBooking_fee()%></td>
			</tr>
			<tr>
				<th scope="row">Issue Date</th>
				<td><%=info.getIssue_date()%></td>
			</tr>
			<tr>
				<th scope="row">Total Fare</th>
				<td><%=info.getTotal_fare()%></td>
			</tr>
			<tr>
				<th scope="row">Cancel Fee</th>
				<td><%=info.getCancel_fee()%></td>
			</tr>
			<tr>
				<th scope="row">Meal</th>
				<td><%=info.getMeal()%></td>
			</tr>
			<tr>
				<th scope="row">Waitlist Number</th>
				<td><%=info.getWaitlist_number()%></td>
			</tr>
			<tr>
				<th scope="row">Flight Number</th>
				<td><%=info.getFlight_number()%></td>
			</tr>
			<tr>
				<th scope="row">Airline ID</th>
				<td><%=info.getAirline_id()%></td>
			</tr>
			<tr>
				<th scope="row">Seat Number</th>
				<td><%=info.getSeat_number()%></td>
			</tr>
			<tr>
				<th scope="row">Class</th>
				<td><%=info.getClassType()%></td>
			</tr>
	</table>
	<input type='submit' class="btn btn-primary" value="Delete this Reservation"/>
</form>
</body>
</html>