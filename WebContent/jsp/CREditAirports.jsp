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

<html>
<body>
<h1>Update Airports Information:</h1>
<table border="1">
<tr>
<td>Airport ID</td>
<td>Action</td>
</tr>

<%  
    try{
    Class.forName("com.mysql.jdbc.Driver"); 
    System.out.println("driver found");
    } catch (ClassNotFoundException e){
    	System.out.println("No driver found");
    	e.printStackTrace();
    	return;
    }
 %>
 <%    
    String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
    try{
    Connection con = DriverManager.getConnection(url, "cs336", "admin123");
    Statement st = con.createStatement();
    ResultSet rs;
    rs = st.executeQuery("SELECT * FROM Airports");
    
    while (rs.next()) {
    %>
    <tr>
		<td><%=rs.getString("airport_id") %></td>
		<td><a href="CRUpdateAirport.jsp?id=<%=rs.getString("airport_id")%>">update</a></td>
	</tr>
	<% 
    }
    con.close();
    }catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
	%>
	