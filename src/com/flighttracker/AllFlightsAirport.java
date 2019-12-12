package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;

//@WebServlet("/CREditReservation")
public class AllFlightsAirport extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String airport_id = request.getParameter("airport_id").toUpperCase();
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
		        Statement st = con.createStatement();
		        ResultSet check;
		        check = st.executeQuery("SELECT flight_number FROM Flights WHERE arrive_airport_id='" + airport_id + "' OR depart_airport_id ='" + airport_id + "'");
		        if (!check.next()) {
		        		con.close();
		        		System.out.println("Airport ID Not Found");
						getServletContext().getRequestDispatcher("/jsp/searchAirport.jsp").forward(request, response);
		        }
		        ResultSet flights;
		        System.out.println("check tickets list");
		        //query for all flight_ids in tickets
		        flights = st.executeQuery("SELECT flight_number FROM Flights");
		        HashMap<Integer, String> table = new HashMap<Integer, String>();
		        while (flights.next()) {
		        	int flight_number = flights.getInt("flight_number");
		        	if (!table.containsKey(flight_number)) {
		        		table.put(flight_number, null);
		        	}
		        }
		    
		        con.close();
		       //from unique flight ids, look at flight table, and for each of these flight ids, take given airpot_id and match between airrinvg and departing
		        con = DriverManager.getConnection(url, "cs336", "admin123");
		        ResultSet airports;
		        st = con.createStatement();
		        airports = st.executeQuery("SELECT flight_number, depart_airport_id, arrive_airport_id FROM Flights");

		        while(airports.next()) {
		        	int flight_number = airports.getInt("flight_number");
		        	String depart_airport_id = airports.getString("depart_airport_id");
		        	String arrive_airport_id = airports.getString("arrive_airport_id");
		        	String value = "";
		        	if (airport_id.equals(depart_airport_id)) {
		        		value += "Departing from " + depart_airport_id;
		        	}
		        	else if (airport_id.equals(arrive_airport_id)){
		        		value += "Arriving at " + arrive_airport_id;
		        	}
		        	if (table.containsKey(flight_number)) {
		        		table.put(flight_number, value);
		        	}
		        }
		        
		        con.close();
		        System.out.println(table);
		        request.setAttribute("table", table); 
				getServletContext().getRequestDispatcher("/jsp/allFlightsAirport.jsp").forward(request, response);

		
	} catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
		}
	}
