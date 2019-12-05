package com.flighttracker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Home extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 String departure_airport = request.getParameter(""); 
		 String arrival_airport = request.getParameter("");
		 String departure_date = request.getParameter("");
		 String arrival_date = request.getParameter("");
		 String errorMessage = "";
		 
		 if(departure_airport.isEmpty() || arrival_airport.isEmpty() || departure_date.isEmpty() || arrival_date.isEmpty()){
				
				RequestDispatcher req = request.getRequestDispatcher(request.getContextPath());
				
				if(departure_airport.isEmpty()) {
					errorMessage = "Please enter a departure airport.";}
				else if (arrival_airport.isEmpty()) {
					errorMessage = "Please enter an arrival airport.";} 
				else if (departure_date.isEmpty()) {
					errorMessage = "Please enter a departure date.";}
				else if (arrival_date.isEmpty()) {
					errorMessage = "Please enter an arrival date.";
				}
			
				request.setAttribute("error", errorMessage);
				req.include(request, response);
		}
		 else {
			 try{
					Class.forName("com.mysql.jdbc.Driver");
					System.out.println("driver found");
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
			        rs = st.executeQuery("SELECT * FROM Flights WHERE depart_airport_id ='" + departure_airport + "' and arrive_airport_id = '" + arrival_airport + "' and depart_date = '" + departure_date + "' and arrive_date = '" + arrival_date + "'");
			        /*
			        //login successful
			        if (rs.next()) {
						request.getSession().setAttribute("user", username);
			            request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
			        } else {
			        	// login failed
			        	errorMessage = "Invalid username or password.";
						RequestDispatcher req = request.getRequestDispatcher("/jsp/login.jsp");
						request.setAttribute("error", errorMessage);
						req.forward(request, response);
			        }
			        */
			        con.close();
		        } catch (SQLException e){
		        	System.out.println("connection failed");
		        	e.printStackTrace();
		        }
		 }
	 }
}
