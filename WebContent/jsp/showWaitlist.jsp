<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import = "java.util.*" %>
<%@ page import = "com.flighttracker.WaitlistObject" %>

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

		<table class="table" style="width: 60%"  >
			<thead>
				<tr>
					<th scope="col">Wait List Queue:</th>
				</tr>
			</thead>
			<tbody>
			<% System.out.println("try");
					System.out.println(request.getAttribute("list"));
			ArrayList<WaitlistObject> list = (ArrayList<WaitlistObject>)request.getAttribute("list");
					System.out.println("2");
					for(WaitlistObject w: list) {
					//request.setAttribute("ticket_number", t.getNumber());
					//System.out.println("TICKET NUMBER: " + request.getAttribute("ticket_number"));%>
				<tr>
					<th scope="row">User: <%=w.getUsername()%><p>
					Flight Number: <%=w.getFlight_number()%><p>
					Wait List Position: <%=w.getWaitlist_number()%> in <%=w.getClassType()%> Class
					</th>
				
			
				</tr>
				<% } %>

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