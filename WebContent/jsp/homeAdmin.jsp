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
	
<div class="list-group" style="width: 75%;" >
	<a href="${pageContext.servletContext.contextPath}/profileAdmin" class="list-group-item list-group-item-action">
			<div>
				<h5>Add / Edit / Delete Customer Representative or Customer Info</h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/revenueSummary" class="list-group-item list-group-item-action">
			<div>
				<h5>View Revenue Summaries</h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/customerMostRevenue" class="list-group-item list-group-item-action">
			<div>
				<h5>Customer generated most total revenue</h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/flightMostSold" class="list-group-item list-group-item-action">
			<div>
				<h5>List of most active flights (most tickets sold)</h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/jsp/searchAirport.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>List of all flights for a given airport </h5>
			</div>
	</a>
	<a href="${pageContext.servletContext.contextPath}/jsp/searchMonth.jsp" class="list-group-item list-group-item-action">
			<div>
				<h5>Sales Report for each month</h5>
			</div>
	</a>	
	<a href="${pageContext.servletContext.contextPath}/adminReservationList" class="list-group-item list-group-item-action">
			<div>
				<h5>List of reservations by Flight Number or Customer Name </h5>
			</div>
	</a>
</div>


</body>
</html>