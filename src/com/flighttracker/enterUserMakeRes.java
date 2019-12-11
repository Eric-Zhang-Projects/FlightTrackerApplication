package com.flighttracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class enterUserMakeRes
 */
public class enterUserMakeRes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public enterUserMakeRes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		System.out.println("get parameter username in enterUserMakeRes: " + username);

		// also check if credentials meet
		if (username.isEmpty()) {
			// Display error message
		}
		else {
			// Make connection and find username in database
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
		        ResultSet rs, rs1;
		        
		        
		        rs1 = st.executeQuery("SELECT * FROM Airports");
			    ArrayList<Airport> airports = new ArrayList<Airport>(); 
			   
		        while (rs1.next()) {
		        	Airport airport = new Airport();
		        	airport.setAirportId(rs1.getString(1));
		        	airports.add(airport);
		        }
		        request.setAttribute("airports", airports);
		        
		        rs = st.executeQuery("SELECT * FROM Customer WHERE username ='" + username + "'");
		        if (rs.next()) {
		        	request.setAttribute("usernameToReserve", rs.getString("username"));
		            request.getRequestDispatcher("/jsp/CRMakeRes.jsp").forward(request, response);
		        }
		        else {
		        	response.sendRedirect("jsp/enterUser.jsp");
		        	
		        	
		        }
	        } catch(SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
		}
	}

}
