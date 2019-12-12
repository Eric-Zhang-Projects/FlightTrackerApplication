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

public class ProfileCustomerRep extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile customer rep DO GET called");
        this.req = req;
        this.resp = resp;
        //make a query 
        String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
    	try {
    		Connection con = DriverManager.getConnection(url, "cs336", "admin123");
        	Statement st = con.createStatement();
        	ResultSet rs;
        	String firstName, lastName, username, password;
        	
        	username = req.getSession().getAttribute("user").toString();
        	System.out.println(username);
        	rs = st.executeQuery("SELECT * from Customer_rep WHERE username = '" + username + "' ");
        	if(rs.next()) {
        		//pull information from the database
        		firstName = rs.getString("first_name");
        		lastName = rs.getString("last_name");
        		password = rs.getString("password");
        		//set the info in the req object
        		req.setAttribute("fName", firstName);
                req.setAttribute("lName", lastName);
                req.setAttribute("uName", username);
                req.setAttribute("pwd", password);
        	}
        	con.close();
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    	//send req to jsp
        getServletContext().getRequestDispatcher("/jsp/profileCustomerRep.jsp").forward(req, resp);
        //resp.sendRedirect("jsp/profileCustomer.jsp");
    }
}