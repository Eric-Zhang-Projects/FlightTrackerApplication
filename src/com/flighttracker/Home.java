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
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {	       
		 //get fields for searching here
		 	//System.out.println("profile customer DO GET called");
	        this.req = req;
	        this.resp = resp;
	        //make a query to get the parameters from the database
	        String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
	        try{
	        	Connection con = DriverManager.getConnection(url, "cs336", "admin123");
		        Statement st = con.createStatement();
		        ResultSet rs1;	        
		        
		        rs1 = st.executeQuery("SELECT * FROM Airports");
			    ArrayList<Airport> airports = new ArrayList<Airport>(); 
			   
		        while (rs1.next()) {
		        	Airport airport = new Airport();
		        	airport.setAirportId(rs1.getString(1));
		        	airports.add(airport);
		        }
		        req.setAttribute("airports", airports);
		        con.close();        
	        }catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);
	 }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
	     String departAirport = request.getParameter("departAirport");
	     String arriveAirport = request.getParameter("arriveAirport");
	     String departDateString = request.getParameter("departDate");
	     String arriveDateString = request.getParameter("arriveDate");
	     
	     
	     //**************************
	     //This only exists if customerRep is making a reservation for a user. Null otherwise
	     boolean isResForUser = false;
	     String usernameToReserve = request.getParameter("usernameToReserve");
	     //System.out.println("NAMEEEE:" + usernameToReserve);
	     
	     if(usernameToReserve!= null && !usernameToReserve.equals("null")) {
	    	 isResForUser = true;
		     //System.out.println("Setting usernameToReserve attribute in Home.java POST:" + usernameToReserve);
	    	 request.setAttribute("usernameToReserve", usernameToReserve);
	    	 
	     }
	     //**************************
	     
	     String roundTrip = request.getParameter("isRoundTrip");
	     boolean isRoundTrip = false;
	     
	     String flexible = request.getParameter("isFlexible");
	     boolean isFlexible = false;
	     
	     if (flexible != null) 
	    	 isFlexible = true;
	     
	     if (roundTrip != null)
	    	 isRoundTrip = true;

	     boolean invalidDate = false;
	     java.sql.Date departDateSql = null;
	     java.sql.Date arriveDateSql = null;
	     // Parse string date to Date datatype then SQL Date datatype
	     try {
	    	 // First convert depart date to java.util.Date
			java.util.Date departDate = new SimpleDateFormat("MM/dd/yyyy").parse(departDateString);
			
			// Then convert to java.sql.Date
			departDateSql = new java.sql.Date(departDate.getTime());
			
			if(isRoundTrip) {
				// Convert arrive date
				java.util.Date arriveDate = new SimpleDateFormat("MM/dd/yyyy").parse(arriveDateString);
				
				// Then convert to java.sql.Date
				arriveDateSql = new java.sql.Date(arriveDate.getTime());
			}
			else {
				arriveDateSql = null;
			}

			
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
	    	PreparedStatement st;
	    	
	    	//if flexible and it is round trip
	    	//Show +/- 3 days and search with arrivalDate
	    	if(isFlexible && isRoundTrip) {
	    		st = con.prepareStatement("SELECT * FROM Flights WHERE depart_airport_id = '" + departAirport + "' AND arrive_airport_id = '" + arriveAirport + "' AND "
			    	+ "depart_date BETWEEN date_sub('" + departDateSql + "', interval 3 day) AND date_add('" + departDateSql + "', interval 3 day) AND arrive_date BETWEEN date_sub('" + arriveDateSql + "', interval 3 day) AND date_add('" + arriveDateSql + "', interval 3 day)");
	    	} 
	    	//not flexible but it is round trip 
	    	//search with arrivalDate
	    	else if(!isFlexible && isRoundTrip){
	    		st = con.prepareStatement("SELECT * FROM Flights WHERE depart_airport_id = '" + departAirport + "' AND arrive_airport_id = '" + arriveAirport + "' AND "
			    	+ "depart_date = '" + departDateSql + "' AND arrive_date = '" + arriveDateSql + "'");
	    	}
	    	//if flexible but it is NOT round trip
	    	//Show +/- 3 days and don't check arrivalDate
	    	else if(isFlexible && !isRoundTrip) {
	    		st = con.prepareStatement("SELECT * FROM Flights WHERE depart_airport_id = '" + departAirport + "' AND arrive_airport_id = '" + arriveAirport + "' AND "
			    	+ "depart_date BETWEEN date_sub('" + departDateSql + "', interval 3 day) AND date_add('" + departDateSql + "', interval 3 day)");
	    	}
	    	//not flexible and it is NOT round trip
	    	//don't search arrivalDate
	    	else if(!isFlexible && !isRoundTrip) {
	    		st = con.prepareStatement("SELECT * FROM Flights WHERE depart_airport_id = '" + departAirport + "' AND arrive_airport_id = '" + arriveAirport + "' AND "
				    + "depart_date = '" + departDateSql + "'");
	    	}
	    	else {
	    		st = null;
	    	}
    
		    ResultSet rs;
		    rs = st.executeQuery();
		    while (rs.next()) {
		        Flight f = new Flight();
		        f.setFlightNumber(rs.getInt(1));
		        f.setDepartAirportId(departAirport);
		        f.setArriveAirportId(arriveAirport);
		        f.setDepartDate(departDateSql);
		        f.setArriveDate(arriveDateSql);
		        f.setDepartTime(rs.getTime(4));
		        f.setArriveTime(rs.getTime(5));
		        f.setAirlineId(rs.getString(8));
		        f.setFareEconomy(rs.getInt("fare_economy"));
		        
		        flightList.add(f);
		    }
		    
		    System.out.println(flightList.toString());
		    request.setAttribute("flightList", flightList);
		    
		    if(isFlexible) {
		    	request.setAttribute("flexibility", "Dates flexible");
		    }
		    else {
		    	request.setAttribute("flexibility", "Not flexible");
		    }
		    
		    
		    if(isRoundTrip) {
		    	request.setAttribute("flightType", "Round Trip");
		    }
		    else {
		    	request.setAttribute("flightType", "One Way");
		    }
		    
		    
		    request.getRequestDispatcher("/jsp/viewFlights.jsp").forward(request, response);
		    con.close();
	      } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	      }
	 }
			
}
