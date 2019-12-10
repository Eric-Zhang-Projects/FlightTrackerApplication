package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/CREditReservation")
public class CRTickets extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//ignore for now
//    	System.out.println("entering edit reservation");
//	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering list tickets");
    	String username = request.getParameter("username");   
        String errorMessage = "";
        
		//also check if credentials meet
		if(username.isEmpty()){
		
			RequestDispatcher req = request.getRequestDispatcher(request.getContextPath());
			
			
			if(username.isEmpty()) {
				errorMessage = "Please enter username.";
			} 
		
			request.setAttribute("error", errorMessage);
			req.include(request, response);
		}
		else{
			System.out.println("username given");
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
		        String response1 = "";
		        System.out.println("check tickets list");
		        System.out.println("username:" + username);
		        rs = st.executeQuery("SELECT * FROM Ticket WHERE username ='" + username + "'");
		        //login successful
		        ArrayList<TicketObject> numbers = new ArrayList<TicketObject>();
		        while(rs.next()) {
		        	System.out.println(rs);
		        	TicketObject Ticket = new TicketObject();
					request.getSession().setAttribute("user", username);
					Ticket.setNumber(rs.getInt("ticket_number"));
					Ticket.setRound_trip(rs.getInt("round_trip"));
					Ticket.setBooking_fee(rs.getInt("booking_fee"));
					Ticket.setIssue_date(rs.getDate("issue_date"));
					Ticket.setTotal_fare(rs.getInt("total_fare"));
					Ticket.setCancel_fee(rs.getInt("cancel_fee"));
					Ticket.setMeal(rs.getInt("meal"));
					Ticket.setWaitlist_number(rs.getInt("waitlist_number"));
					Ticket.setUsername(rs.getString("username"));
					Ticket.setFlight_number(rs.getInt("flight_number"));
					Ticket.setAirline_id(rs.getString("airline_id"));
					Ticket.setSeat_number(rs.getInt("seat_number"));
		        	numbers.add(Ticket);
		        	}
					System.out.println(numbers);
					request.setAttribute("ticket_numbers", numbers); 
					con.close();
					//RequestDispatcher rd = request.getRequestDispatcher("/jsp/CREditReservation.jsp"); 
					//rd.forward(request, response); 
					System.out.println(request.getAttribute("number"));
					//getServletContext().getRequestDispatcher("/jsp/CREditReservation2.jsp").forward(request, response);
					getServletContext().getRequestDispatcher("/jsp/crTickets.jsp").forward(request, response);
		        
//		        else {
//		        	// login failed
//		        	con.close();
//		        	errorMessage = "Invalid username";
//		        	response.sendRedirect("jsp/CREditReservation.jsp");
//					//RequestDispatcher req = request.getRequestDispatcher("/jsp/login.jsp");
//					//request.setAttribute("error", errorMessage);
//					//req.forward(request, response);
//		        }
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	
			
		}
	}
}
