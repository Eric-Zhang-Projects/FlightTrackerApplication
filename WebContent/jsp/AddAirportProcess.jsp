<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*,java.util.*"%>

<%
String airport_id=request.getParameter("airport_id");
String airport_name=request.getParameter("airport_name");


try
{
Class.forName("com.mysql.jdbc.Driver");
Connection conn = DriverManager.getConnection("jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel", "cs336", "admin123");
Statement st=conn.createStatement();
ResultSet rs=st.executeQuery("select * from Airports where airport_id='"+airport_id+"'");

int count=0;
int count2=0;
while(rs.next())
{
count++;
}

ResultSet rs2=st.executeQuery("select * from Airports where airport_name='"+airport_name+"'");
while(rs2.next())
{
count2++;
}

if(count==0 && count2==0)
{
int i=st.executeUpdate("insert into Airports (airport_id,airport_name)values('"+airport_id+"','"+airport_name+"')");
out.println("Data is successfully inserted!");
}
else{
	out.println("Please enter new valid info");
}
}
	
catch(Exception e)
{
System.out.print(e);
e.printStackTrace();
}
%>
<div class="container" style="background-color: #f1f1f1">
<a href='CREditAirports.jsp'>Go Back </a>
<label> <b> | </b> </label> 

</div>