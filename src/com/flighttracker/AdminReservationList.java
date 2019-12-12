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
 * Servlet implementation class AdminReservationList
 */
@WebServlet("/AdminReservationList")
public class AdminReservationList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminReservationList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Admin Reservation List do GET called");
		getServletContext().getRequestDispatcher("/jsp/adminReservationList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//produce a list of reservations based on flight number or username
		String type = request.getParameter("type");
		String radioType = request.getParameter("radioType");
		
		System.out.println("Admin Reservation List do Post called");
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
	        
	        //produce a list of reservations by flight
	        if(radioType.equals("Flight")) {
	        	rs = st.executeQuery("SELECT * FROM Ticket WHERE flight_number = '" + type + "'");
			    ArrayList<TicketObject> tickets = new ArrayList<TicketObject>();
			    
	        	//set everything here
		        while (rs.next()){
		        	TicketObject ticket = new TicketObject();
		        	ticket.setAirline_id(rs.getString("airline_id"));
		        	ticket.setFlight_number(rs.getInt("flight_number"));
		        	ticket.setIssue_date(rs.getDate("issue_date"));
		        	ticket.setNumber(rs.getInt("ticket_number"));
		        	tickets.add(ticket);
		        }
		        request.setAttribute("reservations", tickets);
		        con.close();
		        getServletContext().getRequestDispatcher("/jsp/adminEachReservation.jsp").forward(request, response);
	        }
	      //produce a list of reservations by username
	        else if(radioType.equals("Customer")) {
	        	rs = st.executeQuery("SELECT * FROM Ticket WHERE username = '" + type + "'");
	        	ArrayList<TicketObject> tickets = new ArrayList<TicketObject>();
			    
	        	//set everything here
		        while (rs.next()){
		        	TicketObject ticket = new TicketObject();
		        	ticket.setAirline_id(rs.getString("airline_id"));
		        	ticket.setFlight_number(rs.getInt("flight_number"));
		        	ticket.setIssue_date(rs.getDate("issue_date"));
		        	ticket.setNumber(rs.getInt("ticket_number"));
		        	tickets.add(ticket);
		        }
		        request.setAttribute("reservations", tickets);
		        con.close();
		        getServletContext().getRequestDispatcher("/jsp/adminEachReservation.jsp").forward(request, response);
	        }
	        con.close();
        } catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
	}

}
