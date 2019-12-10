<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
String aircraft_id=request.getParameter("aircraft_id");
String airline_id=request.getParameter("airline_id");
String total_seats_economy=request.getParameter("total_seats_economy");
String total_seats_first=request.getParameter("total_seats_first");

try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel", "cs336", "admin123");
Statement st=conn.createStatement();

int i=st.executeUpdate("insert into Aircraft (aircraft_id,airline_id,total_seats_economy,total_seats_first)values('"+aircraft_id+"','"+airline_id+"','"+total_seats_economy+"','"+total_seats_first+"')");
out.println("Data is successfully inserted!");
}
catch(Exception e)
{
System.out.print(e);
e.printStackTrace();
}
%>
<div class="container" style="background-color: #f1f1f1">
<a href='CREditAircraft.jsp'>Go Back </a>
<label> <b> | </b> </label> 

</div>