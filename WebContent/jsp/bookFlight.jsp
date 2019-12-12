<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import = "com.flighttracker.Flight" %>


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
		alert ( "Booking Successful");
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
<form action="${pageContext.servletContext.contextPath}/bookFlight" method="POST" onSubmit="return validUpdate();">
		
		
		<table class="table" style="width: 60%">
			<thead>
				<tr>
					<th scope="col">Field</th>
					<th scope="col">Current Information</th>
				</tr>
			</thead>
			<tbody>
				<% System.out.println("try");
					System.out.println(request.getAttribute("flight"));
					Flight flight = (Flight)request.getAttribute("flight");
					System.out.println(flight.getFlightNumber());
					//for (Field f : fields){
					//	System.out.println(f);
					//}
				%>
				
				<input type="hidden" name="usernameToReserve" value=<%=request.getAttribute("usernameToReserve")%> >
				
				<tr>
					<th scope="row">Flight Number</th>
					<td class = "flight_number"><%=flight.getFlightNumber()%></td>
					<input type="hidden" name="flight_number" value= <%=flight.getFlightNumber()%> ></td>
				</tr>
				<tr>
					<th scope="row">Airline ID</th>
					<td class = "airline_id"><%=flight.getAirlineId()%></td>
					<input type="hidden" name="airline_id" value= <%=flight.getAirlineId()%> ></td>
				</tr>
				<tr>
					<th scope="row">Aircraft ID</th>
					<td class = "aircarft_id"><%=flight.getAircraftId()%></td>
					<input type="hidden" name="aircraft_id" value= <%=flight.getAircraftId()%> ></td>
				</tr>
				<tr>
					<th scope="row">Depart Time:</th>
					<td class = "depart_date"><%=flight.getDepartDate()%> at <%=flight.getDepartTime()%> from <%=flight.getDepartAirportId()%> </td>
					<input type="hidden" name="depart_date" value= <%=flight.getDepartDate()%> ></td>
					<input type="hidden" name="depart_time" value= <%=flight.getDepartTime()%> ></td>
					<input type="hidden" name="depart_airport_id" value= <%=flight.getDepartAirportId()%> ></td>
				</tr>
				<tr>
					<th scope="row">Arrival Date</th>
					<td class = "arrive_date"><%=flight.getArriveDate()%> at <%=flight.getArriveTime()%> at <%=flight.getArriveAirportId()%></td>
					<input type="hidden" name="arrive_date" value= <%=flight.getArriveDate()%> ></td>
					<input type="hidden" name="arrive_time" value= <%=flight.getArriveTime()%> ></td>
					<input type="hidden" name="arrive_airport_id" value= <%=flight.getArriveAirportId()%> ></td>
				</tr>

		</table>
<div style="float:left">
	Select a travel class:</br>
	<input type="radio" class="toggle" name="class" value="Economy,<%=flight.getFareEconomy()%>"  required>  Economy: <%=flight.getFareEconomy()%> 
	<%if (flight.getAvailableSeatsEconomy()==0){%>
	(Add to Wait List)
	<%}%> <br>
	<input type="radio" class="toggle" name ="class" value="Business,<%=flight.getFareBusiness()%>" required >  Business: <%=flight.getFareBusiness()%> 
	<%if (flight.getAvailableSeatsBusiness()==0){%>
	(Add to Wait List)
	<%}%> <br> 
	<input type="radio" class="toggle" name="class" value="First,<%=flight.getFareFirst()%>"  required>  First: <%=flight.getFareFirst()%>  
	<%if (flight.getAvailableSeatsFirst()==0){%>
	(Add to Wait List)
	<%}%> <br>
</div>
<div style="float:left; margin-left: 30px"">
	Would you like a meal:</br>
	<input type="radio" class="toggle" name="meal" value="1">  Yes  <br>
</div>
<input type='submit' class="btn btn-primary" value="Book Ticket!"/>
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