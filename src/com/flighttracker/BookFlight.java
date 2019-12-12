package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

//@WebServlet("/CREditReservation")
public class BookFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering book flight"); 
    	System.out.println(request.getParameter("flight_number"));
    	int flight_number = Integer.parseInt(request.getParameter("flight_number"));
    	
    	//**********************
    	//Check if customer rep is making the reservation
    	String usernameToRes = request.getParameter("usernameToReserve");
    	
    	request.setAttribute("usernameToReserve", usernameToRes);
    	
//    	if(usernameToRes != null) {
//    		username = usernameToRes;
//    		System.out.println("in BookFlight.java, new username is: " + username);
//    	}
    	//**********************
    	
    	//String ticket_number="1";
    	System.out.println("GOT FROM JSP: " + flight_number);
        
		//also check if credentials meet
		
			
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
		        System.out.println("check ticket for populate info");
		        rs = st.executeQuery("SELECT * FROM Flights WHERE flight_number ='" + flight_number + "'");
		        //login successful
		        if (rs.next()) {
		        	Flight flight = new Flight();
					flight.setFlightNumber(rs.getInt("flight_number"));
					flight.setDepartDate(rs.getDate("depart_date"));
					flight.setArriveDate(rs.getDate("arrive_date"));
					flight.setDepartTime(rs.getTime("depart_time"));
					flight.setArriveTime(rs.getTime("arrive_time"));
					flight.setFareFirst(rs.getInt("fare_first"));
					flight.setFareEconomy(rs.getInt("fare_economy"));
					flight.setAirlineId(rs.getString("airline_id"));
					flight.setDepartAirportId(rs.getString("depart_airport_id"));
					flight.setArriveAirportId(rs.getString("arrive_airport_id"));
					flight.setAircraftId(rs.getInt("aircraft_id"));
					flight.setFareBusiness(rs.getInt("fare_business"));
					flight.setAvailableSeatsEconomy(rs.getInt("available_seats_economy"));
					flight.setAvailableSeatsBusiness(rs.getInt("available_seats_business"));
					flight.setAvailableSeatsFirst(rs.getInt("available_seats_first"));
					request.setAttribute("flight", flight); 
					con.close();
					//RequestDispatcher rd = request.getRequestDispatcher("/jsp/CREditReservation.jsp"); 
					//rd.forward(request, response); 
					
					getServletContext().getRequestDispatcher("/jsp/bookFlight.jsp").forward(request, response);
					//response.sendRedirect("jsp/CREditReservation2.jsp");
					//response1 = "/jsp/home.jsp";
		            //request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		        } 
		        else {
		        	System.out.println("FAIL");
		        	// login failed
		        	con.close();
		        	response.sendRedirect("jsp/CREditReservation.jsp");
					//RequestDispatcher req = request.getRequestDispatcher("/jsp/login.jsp");
					//request.setAttribute("error", errorMessage);
					//req.forward(request, response);
		        }
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("enter book post");
		String username = request.getSession().getAttribute("user").toString();
		System.out.println("username to book for:" + username);
		
		
		boolean isResForUser = false;
		String usernameToRes = null;
		usernameToRes = request.getParameter("usernameToReserve");
		
		System.out.println("usernameToReserve:" + usernameToRes);
		
		if(!usernameToRes.equals("null")) {
			System.out.println("IFFFFFFF:");
			isResForUser = true;
			username = usernameToRes;
		}
		
		System.out.println("username to book for:" + username);
		
		String flight_number= request.getParameter("flight_number");
		String classType = request.getParameter("class").split(",")[0];
		String airline_id = request.getParameter("airline_id");
		String aircraft_id = request.getParameter("aircraft_id");
		String depart_date = request.getParameter("depart_date");
		String depart_time = request.getParameter("depart_time");
		String depart_airport_id = request.getParameter("depart_airport_id");
		String arrive_date = request.getParameter("arrive_date");
		String arrive_time = request.getParameter("arrive_time");
		String arrive_airport_id = request.getParameter("arrive_airport_id");
		String booking_fee = request.getParameter("class").split(",")[1];
		String meal = request.getParameter("meal");
		if (meal == null) {
			meal = "0";
		}
		String waitlist_number = "";
		
		String cancel_fee = "0";
		if (classType.equals("Economy")) {
			cancel_fee = "100";
		}
		
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		String issue_date = formatter.format(date).substring(0,10);
		System.out.println(issue_date);
		
		
		String seat_number="0";
		
		//temporary while round trip not encoded
		String total_fare = booking_fee;
		String round_trip = "0";
		
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
		        Statement st1 = con.createStatement();
		        ResultSet rs, rs1, seats;
		        String temp = "available_seats_" + classType.toLowerCase();
		        System.out.println(temp);
		        int seats_remaining =0;
		        System.out.println("SELECT " + temp + " FROM Flights WHERE flight_number = '" + flight_number + "'");
		        rs = st1.executeQuery("SELECT " + temp + " FROM Flights WHERE flight_number = '" + flight_number + "'");
		        if (rs.next()) {
		        	seats_remaining =rs.getInt(temp);
		        }
		        System.out.println("remaing" + seats_remaining);
		        con.close();
		        
		        con = DriverManager.getConnection(url, "cs336", "admin123");
		        Statement st2 = con.createStatement();
		        if (seats_remaining >0) {
		        	String newValue = Integer.toString(seats_remaining - 1);
		        	System.out.println("UPDATE Flights SET " + temp + "='" + newValue +  "' WHERE flight_number='" + flight_number +"'");
		    		int success = st2.executeUpdate("UPDATE Flights SET " + temp + "='" + newValue +  "' WHERE flight_number='" + flight_number +"'");
		    		System.out.println("hi");
		    		waitlist_number = "0";
		    		con.close();
		    		con = DriverManager.getConnection(url, "cs336", "admin123");
		    		Statement seatSt = con.createStatement();
		        	seats = seatSt.executeQuery("SELECT MAX(seat_number) FROM Ticket WHERE flight_number ='" + flight_number + "' AND class='"+ classType.toLowerCase() + "'");
		        	if (seats.next()) {
		        		seat_number = Integer.toString(seats.getInt("MAX(seat_number)") + 1);
		        	}
		    		con.close();
		        }
		        else {
		        	rs1 = st2.executeQuery("SELECT MAX(waitlist_number) FROM Ticket WHERE flight_number ='" + flight_number + "' AND class='"+ classType.toLowerCase() + "'");
		        	//System.out.println("SELECT MAX(waitlist_number) FROM Ticket WHERE flight_number ='" + flight_number + "'");
		        	if (rs1.next()) {
		        	System.out.println("waitlist query worked");
		        	//System.out.println(rs1.getInt("MAX(waitlist_number)"));
		        	waitlist_number = Integer.toString(rs1.getInt("MAX(waitlist_number)") + 1);
		        	seat_number = "-1";
		        	System.out.println("waitlist: " + waitlist_number);
		        	}
		        }
		        con.close();
		        
		        con = DriverManager.getConnection(url, "cs336", "admin123");
		       // int success = st.executeUpdate("UPDATE Flights SET " + temp + " WHERE ticket_number='" + flight_number +"'");

		       
		
	    String insert = "INSERT INTO Ticket (round_trip, booking_fee, issue_date, total_fare, "
	    		+ "cancel_fee, meal, waitlist_number, username, flight_number, airline_id, seat_number, class) "
	    		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(insert);
       	st.setString(1, round_trip);
       	st.setString(2, booking_fee);
       	st.setString(3, issue_date);
       	st.setString(4, total_fare);
       	st.setString(5, cancel_fee);
       	st.setString(6, meal);
       	st.setString(7, waitlist_number);
       	st.setString(8, username);
       	st.setString(9, flight_number);
       	st.setString(10, airline_id);
       	st.setString(11, seat_number);
       	st.setString(12, classType);
        st.executeUpdate();
        con.close();
        System.out.println("success");
        
        if(isResForUser) {
        	response.sendRedirect("jsp/homeCustomerrep.jsp");
        }
        else {
        	response.sendRedirect("jsp/welcomePage.jsp");
        }
		} catch (SQLException e){
        	System.out.println("booking failed");
        	e.printStackTrace();
        }
		
	}
}
