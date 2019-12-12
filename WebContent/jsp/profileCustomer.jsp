<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Profile Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
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
	<form action="${pageContext.servletContext.contextPath}/profileCustomer" method ="POST">
		<h2>Your Profile Page</h2>
		
		<div class="container">
			<label for="sel1">First Name:</label> 
			<input type="text" placeholder="<%= request.getAttribute("fName") %>" name="firstName">
			
			<br>
			
			<label for="sel1">Last Name:</label> 
			<input type="text" placeholder="<%= request.getAttribute("lName") %>" name="lastName">
			
			<br>
			<label for="usr">Username:</label>
		 	 <input type="text" placeholder="<%= request.getAttribute("uName") %>" name ="username">
		  
		  	<br>
		  	
		  	<label for="pwd">Password:</label>
		  	<input type="password" placeholder="<%= request.getAttribute("pwd") %>" name ="password">
		  	
		  	<br>
		  	<input type="submit" class="btn btn-success" value="Submit"/>
			<br>
			<a href= "${pageContext.servletContext.contextPath}/viewAllReservationsCustomer">View Your Reservations</a>
		</div>
	</form>	
</div>
  
	<%
    if ((session.getAttribute("user") == null)) {
%>
You are not logged in<br/>
<a href="login.jsp">Please Login</a>
<%}
%>


</body>
</html>
<!--  
<script>
$(document).ready(function() {
    $('#firstName').blur(function(event) {
            var name = $('#firstName').val();
            $.get('GetUserServlet', {
                    firstName : name
            }, function(responseText) {
                    $('#fName').text(responseText);
            });
    });
});
</script> 
-->