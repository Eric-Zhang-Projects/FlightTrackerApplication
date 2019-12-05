<html>
<head>
<title>Flight Tracker</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/login.css">
</head>
<body>
	<div class="jumbotron text-center">
		<h1 style='text-align:center'>Flight-Tracker Login</h1>  		
	</div>
	
	<% if(request.getAttribute("error") != null) { %>
	${error}
	<% } %>

	<form action="${pageContext.servletContext.contextPath}/login" method="POST">
		<div class="imgcontainer">
			<img src="https://i.ytimg.com/vi/UJ6GmaZVFkU/maxresdefault.jpg" alt="Avatar" class="avatar">
		</div>

		
		<div class="container">
			<label for="uname"><b></b> </label> 
			<input type="text" placeholder="Enter Username" name="username" required> 
			
			<br> 
			<label for="psw"><b></b> </label> 
			<input type="password" placeholder="Enter Password" name="password" required>
			
			<input type='submit' class="btn btn-primary" value="Submit"/>
			
		</div>

		<div class="container" style="background-color: #f1f1f1">
		
			<a href='${pageContext.servletContext.contextPath}/register'>Create New Account</a>
			<label> <b> | </b> </label> 
			<a href=#>Forgot Password</a>
			
		</div>
	</form>

</body>
</html>