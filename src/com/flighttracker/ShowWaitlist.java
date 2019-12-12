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
public class ShowWaitlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//ignore for now
//    	System.out.println("entering edit reservation");
//	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering list tickets");
    	String flight_number = request.getParameter("flight_number");   
        String errorMessage = "";

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
		        System.out.println("check tickets list");
		        rs = st.executeQuery("SELECT username, waitlist_number, class FROM Ticket WHERE flight_number ='" + flight_number + "'");
		        //login successful
		        ArrayList<WaitlistObject> list = new ArrayList<WaitlistObject>();
		        while(rs.next()) {
		        	System.out.println(rs);
		        	WaitlistObject Waitlist = new WaitlistObject();
					Waitlist.setFlight_number(Integer.parseInt(flight_number));
					Waitlist.setUsername(rs.getString("username"));
					Waitlist.setWaitlist_number(rs.getInt("waitlist_number"));
					Waitlist.setClassType(rs.getString("class"));
		        	list.add(Waitlist);
		        	}
					System.out.println(list);
					request.setAttribute("list", list); 
					con.close();
					//RequestDispatcher rd = request.getRequestDispatcher("/jsp/CREditReservation.jsp"); 
					//rd.forward(request, response); 
					System.out.println(request.getAttribute("list"));
					//getServletContext().getRequestDispatcher("/jsp/CREditReservation2.jsp").forward(request, response);
					getServletContext().getRequestDispatcher("/jsp/showWaitlist.jsp").forward(request, response);
		        
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
