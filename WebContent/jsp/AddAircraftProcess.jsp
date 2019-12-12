<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
String aircraft_id=request.getParameter("aircraft_id");
String airline_id=request.getParameter("airline_id");
String total_seats_economy=request.getParameter("total_seats_economy");
String total_seats_first=request.getParameter("total_seats_first");
String total_seats_business=request.getParameter("total_seats_business");

try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel", "cs336", "admin123");
Statement st=conn.createStatement();

ResultSet rs=st.executeQuery("select * from Airlines where airline_id='"+airline_id+"'");

int count=0;
int count2=0;

while(rs.next())
{
count++;
}

ResultSet rs2=st.executeQuery("select * from Aircraft where aircraft_id='"+aircraft_id+"'");
while(rs2.next())
{
count2++;
}

if(count>0 && count2==0)
{
	int i=st.executeUpdate("insert into Aircraft (aircraft_id,airline_id,total_seats_economy,total_seats_first,total_seats_business)values('"+aircraft_id+"','"+airline_id+"','"+total_seats_economy+"','"+total_seats_first+"','"+total_seats_business+"')");
	out.println("Data is successfully inserted!");
		
}
else{
	out.println("Please enter valid info");
}
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