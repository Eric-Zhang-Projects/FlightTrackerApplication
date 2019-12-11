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

int i=st.executeUpdate("insert into Airports (airport_id,airport_name)values('"+airport_id+"','"+airport_name+"')");
out.println("Data is successfully inserted!");
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