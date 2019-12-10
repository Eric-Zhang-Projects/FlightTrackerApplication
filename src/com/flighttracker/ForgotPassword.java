package com.flighttracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
//import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class ForgotPassword extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	    protected HttpServletResponse resp;
	    
	    public static void thisIsStaticMethod() {
	    	System.out.println("static called");
	    	
	    	
	    }
	    
	    
	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        this.req = req;
	        this.resp = resp;
	        System.out.println("DO GET Called");
	        resp.sendRedirect(req.getContextPath() + "/jsp/ForgotPassword.jsp");
	    }
	    
	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        this.req = req;
	        this.resp = resp;
	        
	        //System.out.println("DO POST Called");
	        //resp.sendRedirect(req.getContextPath() + "/jsp/register.jsp");
	        
	        String firstName = req.getParameter("firstName");   
	        String lastName = req.getParameter("lastName");
	        String username = req.getParameter("username"); 
	        
	        try{
	        Class.forName("com.mysql.jdbc.Driver"); 
	        System.out.println("driver found");
	        } catch (ClassNotFoundException e){
	        	System.out.println("No driver found");
	        	e.printStackTrace();
	        	return;
	        }
	        
	        //SingletonClass sc = SingletonClass.getSingleton();
	        
	        
	        String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
	        try{
	        Connection con = DriverManager.getConnection(url, "cs336", "admin123");
	        //Connection con = sc.getConnection();
	        Statement st = con.createStatement();
	        ResultSet rs;
	        rs = st.executeQuery("SELECT password FROM Customer WHERE last_name ='" + lastName + "' and username ='" + username + "' and first_name = '" + firstName + "'");
	        if (rs.next()) {
	         String password = rs.getString("password");
	        //System.out.println(password);
	        req.setAttribute("Password", password);
	        }
	        else {
	        	System.out.print("No Account Found");
	        }
	        con.close();
	    
	        //out.print("Successful Create!");
	        con.close();
	        
	        req.getRequestDispatcher("/jsp/RetrievePassword.jsp").forward(req, resp);
	        
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	        //end doPost
	    }

	}