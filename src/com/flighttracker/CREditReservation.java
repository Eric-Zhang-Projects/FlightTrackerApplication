package com.flighttracker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/CREditReservation")
public class CREditReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//ignore for now
//    	System.out.println("entering edit reservation");
//	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering edit reservation");
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
		        System.out.println("check user info");
		        rs = st.executeQuery("SELECT * FROM Ticket WHERE username ='" + username);
		        //login successful
		        if (rs.next()) {
					request.getSession().setAttribute("user", username);
					System.out.println("searching");
					//response1 = "/jsp/home.jsp";
		            //request.getRequestDispatcher("/jsp/home.jsp").forward(request, response);
		        } 
		        con.close();
		       System.out.println(response1);
		         if (response1!=""){
		        	 	System.out.println("successful query");
		        	   //request.getRequestDispatcher(response1).forward(request, response);
		        	    }
		        else {
		        	// login failed
		        	errorMessage = "Invalid username";
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
	}
}
