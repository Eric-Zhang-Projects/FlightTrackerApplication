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
    	// System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	boolean  isCustomer = false;
    	boolean isCustomer_rep = false;
    	
    	String username = req.getParameter("username"); 
    	String firstName = req.getParameter("firstName"); 
    	String lastName = req.getParameter("lastName");  
    	String password = req.getParameter("password"); 
    	String edit = req.getParameter("edit");
    	String customerType = req.getParameter("customerType");
    	if (customerType ==null) {
    		customerType = "Customer";
    	}
    	System.out.println(edit);
    	//int id;
    	
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class driver found");
		} catch (ClassNotFoundException e){
			System.out.println("No driver found");
			e.printStackTrace();
        	return;
		}
    	
    	String url = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
    	try {
    		Connection con = DriverManager.getConnection(url, "cs336", "admin123");
        	Statement st = con.createStatement();
        	ResultSet rs, rs1, rs2, rs3;
        	
        	//id = Integer.parseInt(req.getSession().getAttribute("customer_id").toString());
        	//System.out.println(id);
        	//to get the other fields that are empty
        	rs = st.executeQuery("SELECT * from Customer WHERE username = '" + username + "' ");
        	if(rs.next()) {
        		isCustomer = true;
        		customerType = "Customer";
        	}
        	rs1 = st.executeQuery("SELECT * from Customer_rep WHERE username = '" + username + "' ");
        	if(rs1.next() && isCustomer == false) {
        		isCustomer = false;
        		customerType = "Customer_rep";
        	}
        	if (edit.equals("add")) {
        		System.out.println(customerType);
            	String insert = "INSERT INTO " + customerType + " (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
                PreparedStatement add = con.prepareStatement(insert);
               	add.setString(1, firstName);
               	add.setString(2, lastName);
               	add.setString(3, username);
               	add.setString(4, password);
                add.executeUpdate();
                con.close();
        	}
        	else if (edit.equals("delete")) {   
        		System.out.println("deleting "+ customerType);
        		String del = "DELETE FROM " + customerType + " WHERE username ='" + username + "'";
        		Statement delete = con.createStatement();
        		 delete.executeUpdate(del);
        		 con.close();
        		
        	}
        	else if (edit.equals("edit")) {
        	rs2 = st.executeQuery("SELECT * from Customer WHERE username = '" + username + "' ");
        	System.out.println("edit");
        	String insert = "";
        	if(rs2.next()) {
	        	if(isCustomer) {
	        		if(firstName.isEmpty()) {
	            		firstName = rs2.getString("first_name");
	            		System.out.println(firstName);
	            	}
	        		if(lastName.isEmpty()) {
	        			lastName = rs2.getString("last_name");
	            	}
	            	if(password.isEmpty()) {
	            		password = rs2.getString("password");
	            	}
	            	insert = "UPDATE Customer SET first_name = '" + firstName +"', last_name = '" + lastName +"', password = '" + password + "' where username = '" + username + "' ";
	
	
	        		System.out.println(firstName);
	        		System.out.println(lastName);
	        		System.out.println(password);
	        	}
        	}
        	
        	else if(!isCustomer) {
        		rs3 = st.executeQuery("SELECT * from Customer_rep WHERE username = '" + username + "' ");
        		if(rs3.next()) {
	        		if(firstName.isEmpty()) {
	            		firstName = rs3.getString("first_name");
	            	}
	        		if(lastName.isEmpty()) {
	        			lastName = rs3.getString("last_name");
	            	}
	            	if(password.isEmpty()) {
	            		password = rs3.getString("password");
	            	}
        		}
            	insert = "UPDATE Customer_rep SET first_name = '" + firstName +"', last_name = '" + lastName +"', password = '" + password + "' where username = '" + username + "' ";

        	}
        	//update the table
        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();
        	
            System.out.println("successful Update");
        	con.close();
    	}
        	//resp.sendRedirect(req.getContextPath() + "/jsp/profileAdmin.jsp");
        	resp.sendRedirect("jsp/homeAdmin.jsp");
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    }
}