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
				<a class="navbar-brand" href="${pageContext.servletContext.contextPath}/jsp/homeCustomerrep.jsp">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.servletContext.contextPath}/profileCustomerRep"><span class="glyphicon glyphicon-user"></span>
							Customer Representative Account </a></li>
					<li><a href='${pageContext.servletContext.contextPath}/logout'><span
							class="glyphicon glyphicon-log-in"></span> Logout </a></li>
				</ul>
			</div>
		</div>
	</nav>
<form action="${pageContext.servletContext.contextPath}/CRUpdateReservation" method="POST" onSubmit="return validUpdate();">
		<table class="table" style="width: 60%">
			<thead>
				<tr>
					<th scope="col">Field</th>
					<th scope="col">Current Information</th>
					<th scope="col">Enter New Information</th>
				</tr>
			</thead>
			<tbody>
				<% System.out.println("try");
					System.out.println(request.getAttribute("ticket"));
					TicketObject info = (TicketObject)request.getAttribute("ticket");
					System.out.println("2");
					System.out.println(info.getNumber());
				%>
				<tr>
					<th scope="row">Username</th>
					<td><%=info.getUsername()%></td>
					<td>Cannot Change This Field
					<input type="hidden" name="username" value= <%=info.getUsername()%> ></td>
				</tr>
				<tr>
					<th scope="row">Ticket Number</th>
					<td class = "ticket_number"><%=info.getNumber()%></td>
					<td><input type="ticket_number" class="form-control" placeholder="Enter New Ticket Number" name="ticket_numberUpdate">
					<input type="hidden" name="ticket_number" value= <%=info.getNumber()%> ></td>
				</tr>
				<tr>
					<th scope="row">Round Trip</th>
					<td><%=info.getRound_trip()%></td>
					<td><input class="form-control" placeholder="Enter Round Trip" name="round_tripUpdate">
					<input type="hidden" name="round_trip" value= <%=info.getRound_trip()%> ></td>
				</tr>
				<tr>
					<th scope="row">Booking Fee</th>
					<td><%=info.getBooking_fee()%></td>
					<td><input class="form-control" placeholder="Enter Booking Fee" name="booking_feeUpdate">
					<input type="hidden" name="booking_fee" value= <%=info.getBooking_fee()%> ></td>
				</tr>
				<tr>
					<th scope="row">Issue Date</th>
					<td><%=info.getIssue_date()%></td>
					<td><input class="form-control" placeholder="Enter Issue Date" name="issue_dateUpdate">
					<input type="hidden" name="issue_date" value= <%=info.getIssue_date()%> ></td>
				</tr>
				<tr>
					<th scope="row">Total Fare</th>
					<td><%=info.getTotal_fare()%></td>
					<td><input class="form-control" placeholder="Enter Total Fare" name="total_fareUpdate">
					<input type="hidden" name="total_fare" value= <%=info.getTotal_fare()%> ></td>
				</tr>
				<tr>
					<th scope="row">Cancel Fee</th>
					<td><%=info.getCancel_fee()%></td>
					<td><input class="form-control" placeholder="Enter Cancellation Fee" name="cancel_feeUpdate">
					<input type="hidden" name="cancel_fee" value= <%=info.getCancel_fee()%> ></td>
				</tr>
				<tr>
					<th scope="row">Meal</th>
					<td><%=info.getMeal()%></td>
					<td><input class="form-control" placeholder="Enter Meal" name="mealUpdate">
					<input type="hidden" name="meal" value= <%=info.getMeal()%> ></td>
				</tr>
				<tr>
					<th scope="row">Waitlist Number</th>
					<td><%=info.getWaitlist_number()%></td>
					<td><input class="form-control" placeholder="Enter Waitlist Position" name="waitlist_numberUpdate">
					<input type="hidden" name="waitlist_number" value= <%=info.getWaitlist_number()%> ></td>
				</tr>
				<tr>
					<th scope="row">Flight Number</th>
					<td><%=info.getFlight_number()%></td>
					<td><input class="form-control" placeholder="Enter Flight Number" name="flight_numberUpdate">
					<input type="hidden" name="flight_number" value= <%=info.getFlight_number()%> ></td>
				</tr>
				<tr>
					<th scope="row">Airline ID</th>
					<td><%=info.getAirline_id()%></td>
					<td><input class="form-control" placeholder="Enter Airline ID" name="airline_idUpdate">
					<input type="hidden" name="airline_id" value= <%=info.getAirline_id()%> ></td>
				</tr>
				<tr>
					<th scope="row">Seat Number</th>
					<td><%=info.getSeat_number()%></td>
					<td><input class="form-control" placeholder="Enter Seat Number" name="seat_numberUpdate">
					<input type="hidden" name="seat_number" value= <%=info.getSeat_number()%> ></td>
				</tr>
				<tr>
					<th scope="row">Class</th>
					<td><%=info.getClassType()%></td>
					<td>Cannot Change This Field </td>
					<input type="hidden" name="class_type" value= <%=info.getClassType()%> ></td>
				</tr>
		</table>
<input type='submit' class="btn btn-primary" value="Update Ticket Information!"/>
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