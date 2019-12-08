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
				<a class="navbar-brand" href="profileAdmin.jsp">Flight Tracker</a>
				<ul class="nav navbar-nav navbar-right">
					<li>
						<a href="profileAdmin.jsp"><span class="glyphicon glyphicon-user"></span>
							Administrative Account 
						</a>
					</li>
					<li><a href='logout.jsp'><span
							class="glyphicon glyphicon-log-in"></span> 
							Logout 
						</a>	
					</li>
				</ul>
			</div>
		</div>
	</nav>
	
<div class="list-group" style="width: 75%;" >
	<form>
		<h2>Your Profile Page</h2>
		
		<div class="form-group">
			<label for="sel1">First Name:</label> 
			<input type="text" class="form-control" placeholder="Dynamically" id="firstName">
		</div>
		
		<div class="form-group">
			<label for="sel1">Last Name:</label> 
			<input type="text" class="form-control" placeholder="Change" id="lastName">
		</div>
		
		<div class="form-group">
		  <label for="usr">Username:</label>
		  <input type="text" class="form-control" placeholder="These" id="username">
		</div>
		
		<div class="form-group">
		  <label for="pwd">Password:</label>
		  <input type="password" class="form-control" placeholder="Fields" id="password">
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