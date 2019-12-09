package com.flighttracker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProfileAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile admin DO GET called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect(req.getContextPath() + "/jsp/profileAdmin.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	String username = req.getParameter("username"); 
    	String firstName = req.getParameter("firstName"); 
    	String lastName = req.getParameter("lastName");  
    	String password = req.getParameter("password"); 
    	//int id;
    	
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class driver found");
		} catch (ClassNotFoundException e){
			System.out.println("No driver found");
			e.printStackTrace();
        	return;
		}
    	
    	String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
    	try {
    		Connection con = DriverManager.getConnection(url, "cs336", "admin123");
        	Statement st = con.createStatement();
        	ResultSet rs;
        	
        	//id = Integer.parseInt(req.getSession().getAttribute("customer_id").toString());
        	//System.out.println(id);
        	//to get the other fields that are empty
        	rs = st.executeQuery("SELECT * from Customer WHERE username = '" + username + "' ");
        	if(rs.next()) {
        		if(firstName.isEmpty()) {
            		firstName = rs.getString("first_name");
            	}
        		if(lastName.isEmpty()) {
        			lastName = rs.getString("last_name");
            	}
            	if(password.isEmpty()) {
            		password = rs.getString("password");
            	}

        		System.out.println(firstName);
        		System.out.println(lastName);
        		System.out.println(password);
        	}
        	
        	//update the table
        	String insert = "UPDATE Customer SET first_name = '" + firstName +"', last_name = '" + lastName +"', password = '" + password + "' where username = '" + username + "' ";
        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();
        	
        	
            System.out.println("successful Update");
        	con.close();
        	resp.sendRedirect(req.getContextPath() + "/jsp/profileAdmin.jsp");
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    }
}