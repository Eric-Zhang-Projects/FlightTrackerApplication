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
<h1>Update Aircraft Information:</h1>
<table border="1">
<tr>
<td>Flight #</td>
<td>Depart Date </td>
<td>Arrive Date </td>
<td>Depart Time </td>
<td>Arrive Time</td>
<td>First Class Fare</td>
<td>Economy Fare</td>
<td>Business Fare</td>
<td>Airline ID</td>
<td>Departure Airport</td>
<td>Arrival Airport </td>
<td>Aircraft ID </td>
<td>Available Seats Economy</td>
<td>Available Seats First</td>
<td>Available Seats Business</td>
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
    rs = st.executeQuery("SELECT * FROM Flights");
    
    while (rs.next()) {
    %>
    <tr>
		<td><%=rs.getString("flight_number") %></td>
		<td><%=rs.getString("depart_date") %></td>
		<td><%=rs.getString("arrive_date") %></td>
		<td><%=rs.getString("depart_time") %></td>
		<td><%=rs.getString("arrive_time") %></td>
		<td><%=rs.getString("fare_first") %></td>
		<td><%=rs.getString("fare_economy") %></td>
		<td><%=rs.getString("fare_business") %></td>
		<td><%=rs.getString("airline_id") %></td>
		<td><%=rs.getString("depart_airport_id") %></td>
		<td><%=rs.getString("arrive_airport_id") %></td>
		<td><%=rs.getString("aircraft_id") %></td>
		<td><%=rs.getString("available_seats_economy") %></td>
		<td><%=rs.getString("available_seats_first") %></td>
		<td><%=rs.getString("available_seats_business") %></td>
		<td><a href="updateAirline.jsp?id=<%=rs.getString("flight_number")%>">update</a></td>
	</tr>
	<% 
    }
    con.close();
    }catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
	%>
	