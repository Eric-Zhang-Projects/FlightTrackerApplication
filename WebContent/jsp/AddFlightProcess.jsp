<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
String flight_number=request.getParameter("flight_number");
String depart_date=request.getParameter("depart_date");
String arrive_date=request.getParameter("arrive_date");
String depart_time=request.getParameter("depart_time");
String arrive_time=request.getParameter("arrive_time");
String fare_first=request.getParameter("fare_first");
String fare_economy=request.getParameter("fare_economy");
String fare_business=request.getParameter("fare_business");
String airline_id=request.getParameter("airline_id");
String depart_airport_id=request.getParameter("depart_airport_id");
String arrive_airport_id=request.getParameter("arrive_airport_id");
String aircraft_id=request.getParameter("aircraft_id");
String available_seats_economy =null;
String available_seats_first=null;
String available_seats_business=null;
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel", "cs336", "admin123");
Statement st=conn.createStatement();
ResultSet rs =  st.executeQuery("SELECT * from Aircraft WHERE aircraft_id= '" + aircraft_id + "' ");
if(rs.next()) {
		available_seats_economy = rs.getString("total_seats_economy");
		available_seats_first = rs.getString("total_seats_first");
		available_seats_business = rs.getString("total_seats_business");
	}

ResultSet rs2=st.executeQuery("select * from Flights where flight_number='"+flight_number+"'");
int count2 =0;
while(rs2.next())
{
count2++;
}

ResultSet rs3=st.executeQuery("select * from Airlines where airline_id='"+airline_id+"'");
int count3 =0;
while(rs3.next())
{
count3++;
}

ResultSet rs4=st.executeQuery("select * from Aircraft where aircraft_id='"+aircraft_id+"'");
int count4 =0;
while(rs4.next())
{
count4++;
}

ResultSet rs5=st.executeQuery("select * from Airports where airport_id='"+depart_airport_id+"'");
int count5 =0;
while(rs5.next())
{
count5++;
}

ResultSet rs6=st.executeQuery("select * from Airports where airport_id='"+arrive_airport_id+"'");
int count6 =0;
while(rs6.next())
{
count6++;
}


if(count2==0 && count3>0 && count4>0 && count6>0 && count5>0){
int i=st.executeUpdate("insert into Flights (flight_number,depart_date,arrive_date,depart_time,arrive_time,fare_first,fare_economy,airline_id,depart_airport_id,arrive_airport_id,aircraft_id,fare_business,available_seats_economy,available_seats_first,available_seats_business)values('"+flight_number+"','"+depart_date+"','"+arrive_date+"','"+depart_time+"','"+arrive_time+"','" + fare_first +"','" + fare_economy +"','" + airline_id +"','" + depart_airport_id +"','" + arrive_airport_id +"','" + aircraft_id +"','" + fare_business +"','" + available_seats_economy +"','" + available_seats_first +"','" + available_seats_business +"')");
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
<a href='CREditFlights.jsp'>Go Back </a>
<label> <b> | </b> </label> 

</div>