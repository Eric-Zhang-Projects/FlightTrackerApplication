<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.*" %>

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
<script src="https://code.jquery.com/jquery-1.10.2.js" type="text/javascript"></script>

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

		<table class="table" style="width: 60%"  >
			<thead>
				<tr>
				<%HashMap<String, String[]> tab = (HashMap<String, String[]>)request.getAttribute("table"); %>
				<th scope="col">Sales Report for: <%=tab.get("month")[0] %></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">Total Tickets Sold:<p>
					 <%=tab.get("total_tickets")[0]%> Tickets
					</th>
				</tr>
				<tr>
					<th scope="row">Total Flights Operated:<p>
					 <%=tab.get("total_flights")[0]%> Flights
					</th>
				</tr>
				<tr>
					<th scope="row">Total Revenue Generated:<p>
					 $<%=tab.get("total_revenue")[0]%>
					</th>
				</tr>
				<tr>
					<th scope="row">Airline That Produced the Most Revenue:<p>
					 Airline: <%=tab.get("airline_id")[0]%> with $ <%=tab.get("airline_id")[1]%> in Total Revenue
					</th>
				</tr>	
				<tr>
					<th scope="row">Most Tickets Bought for Flight Number:<p>
					 Flight Number: <%=tab.get("flight_number")[0]%> With <%=tab.get("flight_number")[1]%> Tickets Bought
					</th>
				</tr>							 
		</table>

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