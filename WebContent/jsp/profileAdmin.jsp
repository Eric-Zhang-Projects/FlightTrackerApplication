<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Profile Page</title>
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
	
<div class="list-group">
	<form action="${pageContext.servletContext.contextPath}/profileAdmin" method ="POST">
		<h2>Add / Edit / Delete Customer Representative or Customer Info</h2>
		
		<div class="container">
			<label for="sel1">Enter the username of the account information you'd like to change:</label>
			<br>
			<label for="username">Username:</label>
			<input type="text" placeholder="Enter Username" name="username">
			
			<div>
			<label><input type="radio" name="edit" value="edit" required>Edit</label>
			<label><input type="radio" name="edit" value = "add" required>Add</label>
			<label><input type="radio" name="edit" value = "delete" required>Delete</label>
			</div>
			<br>
			
			<label for="usr">Only Enter for Edit or Add</label>
			<br>
			<label for="usr">First Name:</label>
		 	 <input type="text" placeholder="Enter First Name " name ="firstName">
			<br>
			<label for="sel1">Last Name:</label> 
			<input type="text" placeholder="Enter Last Name" name="lastName">
		
		  	<br>
		  	<label for="pwd">Password:</label>
		  	<input type="password" placeholder="Enter Password" name ="password">
		  	<br>
		  	<br><br>
		  	<label>Mark if Adding New Customer Representative Account</label>
			<label><input type="radio" name="customerType" value = "Customer_rep">Customer Representative</label>
		  	<br><br>
		  	<input type="submit" class="btn btn-success" value="Submit"/>
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
</body>
</html>