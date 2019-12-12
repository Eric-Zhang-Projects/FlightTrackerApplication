package com.flighttracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RevenueSummary
 */
@WebServlet("/RevenueSummary")
public class RevenueSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RevenueSummary() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Revenue Summary do GET called");
		getServletContext().getRequestDispatcher("/jsp/revenueSummary.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String radioType = request.getParameter("radioType");
		System.out.println("Revenue Summary do Post called");
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
	        ResultSet rs; 
	        int totalFare;
	        
	        //revenue summary by flight
	        if(radioType.equals("Flight")) {
	        	totalFare =0;
	        	rs = st.executeQuery("SELECT * FROM Ticket");
		        HashMap<Integer, Integer> flights = new HashMap<Integer, Integer>();
		        //populate a hashmap of all unique flight numbers
	        	while(rs.next()) {
	        		if(!flights.containsKey(rs.getInt("flight_number"))) {
	        			flights.put(rs.getInt("flight_number"), 0);
	        		}
	        		totalFare+=rs.getInt("total_fare");
	        	}
	        	//update the hashmap with total fare for each entry
//	        	for (HashMap.Entry<Integer, Integer> entry : flights.entrySet()) {
//	        		rs.absolute(0);
//	        		while(rs.next()){
//	        			if(rs.getInt("flight_number") == entry.getKey()) {
//	        				flights.put(entry.getKey(), entry.getValue() + rs.getInt("total_fare") );
//	        			}
//	        		}
//	    		}
	        	for (int key : flights.keySet()) {
        		rs.absolute(0);
        		while(rs.next()){
        			if(rs.getInt("flight_number") == key) {
        				flights.put(key, flights.get(key) + rs.getInt("total_fare") );
        			}
        		}
    		}
		        request.setAttribute("flights", flights);
		        request.setAttribute("radioType", radioType);
		        request.setAttribute("totalFare", totalFare);
		        con.close();
		        getServletContext().getRequestDispatcher("/jsp/flightSummary.jsp").forward(request, response);
	        }
	        //revenue summary by airline
	        else if(radioType.equals("Airline")) {
	        	totalFare =0;
	        	rs = st.executeQuery("SELECT * FROM Ticket");
		        HashMap<String, Integer> airlines = new HashMap<String, Integer>();
		        //populate a hashmap of all unique airline_id
	        	while(rs.next()) {
	        		if(!airlines.containsKey(rs.getString("airline_id"))) {
	        			airlines.put(rs.getString("airline_id"), 0);
	        		}
	        		totalFare+=rs.getInt("total_fare");
	        	}
	        	//update the hashmap with total fare for each entry
//	        	for (HashMap.Entry<String, Integer> entry : airlines.entrySet()) {
//	        		rs.absolute(0);
//	        		while(rs.next()){
//	        			if(rs.getString("airline_id").equals(entry.getKey())){
//	        				airlines.put(entry.getKey(), entry.getValue() + rs.getInt("total_fare") );
//	        			}
//	        		}
//	    		}
	        	for (String key : airlines.keySet()) {
        		rs.absolute(0);
        		while(rs.next()){
        			if(rs.getString("airline_id").equals(key)) {
        				airlines.put(key, airlines.get(key) + rs.getInt("total_fare") );
        			}
        		}
    		}
		        request.setAttribute("airlines", airlines);
		        request.setAttribute("radioType", radioType);
		        request.setAttribute("totalFare", totalFare);
		        con.close();
		        getServletContext().getRequestDispatcher("/jsp/airlineSummary.jsp").forward(request, response);
	        }
	        //revenue summary by customer
	        else if(radioType.equals("Customer")){
	        	totalFare =0;
	        	rs = st.executeQuery("SELECT * FROM Ticket");
		        HashMap<String, Integer> customers = new HashMap<String, Integer>();
		        //populate a hashmap of all unique flight numbers
	        	while(rs.next()) {
	        		if(!customers.containsKey(rs.getString("username"))) {
	        			customers.put(rs.getString("username"), 0);
	        		}
	        		totalFare+=rs.getInt("total_fare");
	        	}
	        	//update the hashmap with total fare for each entry
//	        	for (HashMap.Entry<String, Integer> entry : customers.entrySet()) {
//	        		rs.absolute(0);
//	        		while(rs.next()) {
//	        			if(rs.getString("username").equals(entry.getKey())) {
//	        				customers.put(entry.getKey(), entry.getValue() + rs.getInt("total_fare") );
//	        			}
//	        		}
//	    		}
	        	for (String key : customers.keySet()) {
        		rs.absolute(0);
        		while(rs.next()){
        			if(rs.getString("username").equals(key)) {
        				customers.put(key, customers.get(key) + rs.getInt("total_fare") );
        			}
        		}
    		}
		        request.setAttribute("customers", customers);
		        request.setAttribute("radioType", radioType);
		        request.setAttribute("totalFare", totalFare);
		        con.close();
		        getServletContext().getRequestDispatcher("/jsp/customerSummary.jsp").forward(request, response);
	        }
	        con.close();
        } catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
	}
		
}
