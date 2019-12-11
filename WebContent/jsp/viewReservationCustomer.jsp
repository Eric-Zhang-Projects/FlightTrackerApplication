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
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/jsp/home.jsp">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.servletContext.contextPath}/jsp/profileCustomer.jsp"><span class="glyphicon glyphicon-user"></span>
							Customer Representative Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>
	<table class="table" style="width: 60%">
		<thead>
			<tr>
				<th scope="col">Field</th>
				<th scope="col">Current Information</th>
			</tr>
		</thead>
		<tbody>
		<!-- Use Eric's Edit Customer Rep JSp to create this page -->
			<% System.out.println("try");
				System.out.println(request.getAttribute("ticket"));
				TicketObject info = (TicketObject)request.getAttribute("ticket");
				System.out.println("2");
				System.out.println(info.getNumber());
			%>
			<tr>
				<th scope="row">Username</th>
				<td><%=info.getUsername()%></td>
			</tr>
			<tr>
				<th scope="row">Ticket Number</th>
				<td class = "ticket_number"><%=info.getNumber()%></td>
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
	<a herf ="${pageContext.servletContext.contextPath}/jsp/viewAllReservationsCustomer.jsp">Back</a>
		


	<%
    if ((session.getAttribute("user") == null)) {
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%}
%>

</body>
</html>