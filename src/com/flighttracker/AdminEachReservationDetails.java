package com.flighttracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminEachReservationDetails
 */
@WebServlet("/AdminEachReservationDetails")
public class AdminEachReservationDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminEachReservationDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//display all the ticket information
		String ticket_number;
		//get and set all the ticket info here
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class driver found");
		} catch (ClassNotFoundException e){
			System.out.println("No driver found");
			e.printStackTrace();
        	return;
		}
		ticket_number = request.getParameter("ticket_number");
		String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
        try{
        	Connection con = DriverManager.getConnection(url, "cs336", "admin123");
	        Statement st = con.createStatement();
	        ResultSet rs;        
	        
	        rs = st.executeQuery("SELECT * FROM Ticket WHERE ticket_number = '" + ticket_number + "'");
		    TicketObject ticket = new TicketObject();
        	//set everything here
	        if (rs.next()){
	        	ticket.setAirline_id(rs.getString("airline_id"));
	        	ticket.setBooking_fee(rs.getInt("booking_fee"));
	        	ticket.setCancel_fee(rs.getInt("cancel_fee"));
	        	ticket.setFlight_number(rs.getInt("flight_number"));
	        	ticket.setIssue_date(rs.getDate("issue_date"));
	        	ticket.setMeal(rs.getInt("meal"));
	        	ticket.setNumber(rs.getInt("ticket_number"));
	        	ticket.setRound_trip(rs.getInt("round_trip"));
	        	ticket.setSeat_number(rs.getInt("seat_number"));
	        	ticket.setTotal_fare(rs.getInt("total_fare"));
	        	ticket.setUsername(rs.getString("username"));
	        	ticket.setWaitlist_number(rs.getInt("waitlist_number"));
	        	ticket.setClassType(rs.getString("class"));
	        }
	        request.setAttribute("ticket", ticket);
	        con.close();
        } catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
		getServletContext().getRequestDispatcher("/jsp/adminEachReservationDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//do nothing
	}

}
