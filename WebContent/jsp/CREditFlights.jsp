<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Checker</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
<%@ page import ="java.sql.*" %>
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
<html>
<body>
<h1>Update Flight Information:</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Flight</th>
      <th scope="col">Depart Date</th>
      <th scope="col">Arrive Date</th>
      <th scope="col">Depart Time</th>
      <th scope="col">Arrive Time</th>
      <th scope="col">First Class Fare</th>
      <th scope="col">Economy Fare</th>
      <th scope="col">Business Fare</th>
      <th scope="col">Airline ID</th>
      <th scope="col">Depart Airport ID</th>
      <th scope="col">Arrive Airport ID</th>
      <th scope="col">Aircraft ID</th>
      <th scope="col">Available Seats Economy</th>
      <th scope="col">Available Seats First</th>
      <th scope="col">Available Seats Business</th>
    </tr>
  </thead>
  

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
	</tr>
	<% 
    }
    con.close();
    }catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
	%>
</tbody>
</table>
<%@ page import ="java.sql.*" %>
<div>
<a href='AddFlight.jsp'>Add Flight</a>
</div>
<div>
<a href='DeleteFlight.jsp'>Delete Flight</a>
</div>

<div>
<a href='CRUpdateFlight.jsp'>Update Flight</a>
</div>
	