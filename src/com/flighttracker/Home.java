package com.flighttracker;
import java.io.IOException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	       

	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	     String departAirport = request.getParameter("departAirport");
	     String arriveAirport = request.getParameter("arriveAirport");
	     String departDateString = request.getParameter("departDate");
	     String arriveDateString = request.getParameter("arriveDate");
	     
	     boolean invalidDate = false;
	     java.sql.Date departDateSql = null;
	     java.sql.Date arriveDateSql = null;
	     // Parse string date to Date datatype then SQL Date datatype
	     try {
	    	 // First convert depart date to java.util.Date
			java.util.Date departDate = new SimpleDateFormat("dd/MM/yyyy").parse(departDateString);
			
			// Then convert to java.sql.Date
			departDateSql = new java.sql.Date(departDate.getTime());
			
			// Convert arrive date
			java.util.Date arriveDate = new SimpleDateFormat("dd/MM/yyyy").parse(arriveDateString);
			
			// Then convert to java.sql.Date
			arriveDateSql = new java.sql.Date(arriveDate.getTime());
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			invalidDate = true;
			e1.printStackTrace();
		}  
	     

		 ArrayList<Flight> flightList = new ArrayList<Flight>(); 

		 try{
				Class.forName("com.mysql.jdbc.Driver");
				System.out.println("class driver found");
		 } catch (ClassNotFoundException e){
				System.out.println("No driver found");
				e.printStackTrace();
	        	return;
		 }
		 
		 String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
	     try{
	    	Connection con = DriverManager.getConnection(url, "cs336", "admin123");
		    PreparedStatement st = con.prepareStatement("SELECT * FROM Flights WHERE depart_airport_id = ? AND arrive_airport_id = ? AND "
		    		+ "depart_date = ? AND arrive_date = ?");
		    st.setString(1, departAirport);
		    st.setString(2, arriveAirport);
		    st.setDate(3, departDateSql);
		    st.setDate(4, arriveDateSql);
		    
		    ResultSet rs;
		    rs = st.executeQuery();
		    while (rs.next()) {
		        Flight f = new Flight();
		        f.setFlightNumber(rs.getInt(1));
		        flightList.add(f);
		    } 
		    //request.setAttribute("data", flightList);
		    //request.getRequestDispatcher("home.jsp").forward(request, response); 
		    request.getSession().setAttribute("data", flightList);
	        request.getRequestDispatcher("/jsp/viewFlights.jsp").forward(request, response);
		    con.close();
	      } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	      }
	 }
			
}
