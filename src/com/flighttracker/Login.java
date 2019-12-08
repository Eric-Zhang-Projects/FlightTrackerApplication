package com.flighttracker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Login get works");
		response.sendRedirect("");
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
    	String username = request.getParameter("username");   
        String password = request.getParameter("password");
        String errorMessage = "";
        
		//also check if credentials meet
		if(username.isEmpty() || password.isEmpty()){
		
			RequestDispatcher req = request.getRequestDispatcher(request.getContextPath());
			
			
			if(username.isEmpty()) {
				errorMessage = "Please enter username.";
			} else if (password.isEmpty()) {
				errorMessage = "Please enter password.";
			}
		
			request.setAttribute("error", errorMessage);
			req.include(request, response);
		}
		else{
			
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
		        ResultSet rs, rs1, rs2;

		        ResultSet rep;
		        ResultSet admin;
		        String response1 = "";		        
		        
		        rs1 = st.executeQuery("SELECT * FROM Airports");
			    ArrayList<Airport> airports = new ArrayList<Airport>(); 
			   
		        while (rs1.next()) {
		        	Airport airport = new Airport();
		        	airport.setAirportId(rs1.getString(1));
		        	airports.add(airport);
		        }
		        request.setAttribute("airports", airports);
		        
		        // RequestDispatcher rd = request.getRequestDispatcher("jsp/home.jsp");
		        // rd.forward(request, response); 
		        
		        
		        rs = st.executeQuery("SELECT * FROM Customer WHERE username ='" + username + "' and password = '" + password + "'");
		        //login successful
		        if (rs.next()) {
		        	System.out.println("If works");
					request.getSession().setAttribute("user", username);
					System.out.println(username);
					rs2 = st.executeQuery("SELECT * FROM Customer WHERE username = '" + username + "' ");
					if(rs2.next()) {
						System.out.println("YESS");
						int id = rs2.getInt("customer_id");
						System.out.println("Hi /" + username + " your id is: " + id);
						request.getSession().setAttribute("customer_id", Integer.toString(id));
						System.out.println(request.getSession().getAttribute("customer_id"));
					}
		        }
		        // response.sendRedirect(response1);
		        request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		         
		        con.close();
		        con = DriverManager.getConnection(url, "cs336", "admin123");
		        st = con.createStatement();
		        rep = st.executeQuery("SELECT * FROM Customer_rep WHERE username ='" + username + "' and password = '" + password + "'");
		        if(rep.next()){
		        	request.getSession().setAttribute("user", username); // the username will be stored in the session
		            response1 = "jsp/homeCustomerrep.jsp";  
					//response.sendRedirect(response1);
		        } 
		         con.close();
		         con = DriverManager.getConnection(url, "cs336", "admin123");
		         st = con.createStatement();
		         admin = st.executeQuery("SELECT * FROM Admin WHERE username ='" + username + "' and password = '" + password + "'");
		         if(admin.next()){
		        	 request.getSession().setAttribute("user", username); // the username will be stored in the session
		             response1 = "jsp/homeAdmin.jsp"; 
					//response.sendRedirect(response1);
		         } 
		         System.out.println(response1);
		         if (response1!=""){
		        	   // request.getRequestDispatcher(response1).forward(request, response);
		        	    }
		         else {
		        	// login failed
		        	errorMessage = "Invalid username or password.";
					//RequestDispatcher req = request.getRequestDispatcher("/jsp/login.jsp");
					request.setAttribute("error", errorMessage);
					//response.sendRedirect("");
		         }
		         
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
		}
	}
}