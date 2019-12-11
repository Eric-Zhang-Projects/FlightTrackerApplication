package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

//@WebServlet("/CREditReservation")
public class CRUpdateReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering update reservation GET"); 
    	System.out.println(request.getParameter("ticket_number"));
    	int ticket_number = Integer.parseInt(request.getParameter("ticket_number"));
    	//String ticket_number="1";
    	System.out.println("GOT FROM JSP: " + ticket_number);
        
		//also check if credentials meet
		
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
		        System.out.println("check ticket for populate info");
		        rs = st.executeQuery("SELECT * FROM Ticket WHERE ticket_number ='" + ticket_number + "'");
		        //login successful
		        if (rs.next()) {
		        	TicketObject Ticket = new TicketObject();
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
					Ticket.setClassType(rs.getString("class"));

					System.out.println(Ticket.getIssue_date());
					request.setAttribute("ticket", Ticket); 
					con.close();
					//RequestDispatcher rd = request.getRequestDispatcher("/jsp/CREditReservation.jsp"); 
					//rd.forward(request, response); 
					System.out.println(request.getAttribute("ticket"));
					getServletContext().getRequestDispatcher("/jsp/CREditReservation2.jsp").forward(request, response);
					//response.sendRedirect("jsp/CREditReservation2.jsp");
					//response1 = "/jsp/home.jsp";
		            //request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		        } 
		        else {
		        	System.out.println("FAILLLLLL");
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
    	System.out.println("entering edit ticket");
    	Map<String, String[]> parameters = request.getParameterMap();
    	Map<String, String> oldValues = new HashMap<String, String>();
    	Map<String, String> newValues = new HashMap<String, String>();
    	for (String parameter : parameters.keySet()) {
    		System.out.println("PARAM" + parameters.get(parameter)[0]);
    		if(!parameters.get(parameter)[0].isEmpty()) {
    			if (parameter.contains("Update")) {
    				newValues.put(parameter, parameters.get(parameter)[0]);
    			}
    			else {
    				oldValues.put(parameter, parameters.get(parameter)[0]);
    			}	
    		}
    	}
    	System.out.println("old TABLE: " + oldValues.entrySet());
    	System.out.println("new TABLE: " + newValues.entrySet());
    	int ticket_number = Integer.parseInt(oldValues.get("ticket_number"));
    	String query = "";
    	for (String value : newValues.keySet()) {
    		String name = value.substring(0,value.length()-6);
    		System.out.println(value);
    		query += name + "='" + newValues.get(value) + "', ";
    	}
    	query = query.substring(0,query.length()-2);
    	System.out.println(query);

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
		        System.out.println("check customer info");
		        System.out.println("UPDATE Ticket SET " + query + " WHERE ticket_number='" + ticket_number +"'");
		        int success = st.executeUpdate("UPDATE Ticket SET " + query + " WHERE ticket_number='" + ticket_number +"'");
		        //int success = 1;
		        con.close();
		        //login successful
		        if (success > 0) {
		        	System.out.println("update success");
		        	//JOptionPane.showMessageDialog(null, "Update Successful!");
		        	response.sendRedirect("jsp/homeCustomerrep.jsp");
		        }
		        else {
		        	System.out.println("error");
		        	response.sendRedirect("");
		        }
		        
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	
			
	}
}
