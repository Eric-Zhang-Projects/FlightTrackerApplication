package com.flighttracker;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateAirport extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile admin DO GET called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect(req.getContextPath() + "/jsp/CRUpdateAirports.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	String airport_name = req.getParameter("airport_name"); 
    	String newairport_name = req.getParameter("newairport_name");
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
        	System.out.println("Hello im here");
        	rs = st.executeQuery("SELECT * from Airports WHERE airport_name= '" + airport_name + "' ");
        	if(rs.next()) {
        		if(airport_name.isEmpty()) {
        			airport_name = rs.getString("airport_name");
            	}
        		
        		
        		System.out.println(airport_name);
        	}
 
        	//update the table
        	String insert = "UPDATE Airports SET airport_name = '" + newairport_name + "' where airport_name= '" + airport_name + "'";
        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();

        	
            System.out.println("successful Update");
        	con.close();
        	resp.sendRedirect(req.getContextPath() + "/jsp/CREditAirports.jsp");
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    }
}