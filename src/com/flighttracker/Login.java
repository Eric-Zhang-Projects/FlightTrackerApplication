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

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
  /*
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ignore for now
	}*/
	
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
		        rs = st.executeQuery("SELECT * FROM Customer WHERE username ='" + username + "' and password = '" + password + "'");
		        
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
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	
			
		}
	}
}