<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<title>Create New Account!</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="css/register.css">
</head>

<body>
	<div class="jumbotron text-center">
		<h1 style='text-align:center'>Create New Account</h1>  		
	</div>
	<form action="${pageContext.servletContext.contextPath}/register" method="POST">
		<div class="form-group">
			<label for="firstName">First Name:</label> 
			<input type="text" class="form-control" placeholder="Enter First Name" name="firstName" required>
		</div>
		
		<div class="form-group">
			<label for="lastName">Last Name:</label> 
			<input type="text" class="form-control" placeholder="Enter Last Name" name="lastName" required>
		</div>
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="username" class="form-control" placeholder="Enter Username" name="username" required>
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" name="password" required>
		</div>
		<div class="form-group">
			<label for="confirmPwd">Confirm Password:</label> 
			<input type="password" class="form-control" placeholder="Confirm password" name="confirmPassword" required>
		</div>
		
		<input type='submit' class="btn btn-primary" value="Create Account!"/>
		
		<div class="container" style="background-color: #f1f1f1">
			<a href='${pageContext.servletContext.contextPath}/login'>Back To Login</a>
		</div>
	</form>


</body>
</html>