<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Checker</title>
</head>
<body>
<%@ page import ="java.sql.*" %>

<%

String airport_id = request.getParameter("airport_id");
String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
String userid = "cs336";
String password = "admin123";
try {
Class.forName(driver);
} catch (ClassNotFoundException e){
	System.out.println("No driver found");
	e.printStackTrace();
	return;
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<%
try{
connection = DriverManager.getConnection(connectionUrl, userid, password);
statement=connection.createStatement();
String sql ="SELECT * from Airports WHERE airport_id = '" + airport_id + "' ";
resultSet = statement.executeQuery(sql);
while(resultSet.next()){
%>
<body>
<h1>Update data from database in jsp</h1>
<form method="post" action="">
<input type="hidden" name="id" value="<%=resultSet.getString("id") %>">
<input type="text" name="id" value="<%=resultSet.getString("id") %>">
<br>
Airline ID:<br>
<input type="text" name="airline_id" value="<%=resultSet.getString("airport_id") %>">
<br>
<input type="submit" value="submit">
</form>
</body>
<%
}
connection.close();
} catch (SQLException e){
	System.out.println("connection failed");
	e.printStackTrace();
}
%>
