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

public class DeleteFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile admin DO GET called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect(req.getContextPath() + "/jsp/CREditFlights.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	String flight_number = req.getParameter("flight_number"); 
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
       
    		String a = ("select * from Flights where flight_number='"+flight_number+"'");
        	PreparedStatement ps = con.prepareStatement(a);
        	ResultSet rs = ps.executeQuery();
        	int count =0;
        	while(rs.next())
        	{
        	count++;
        	}
        	int j=0;
        	if (count > 0) {
        	String insert = "DELETE FROM Flights WHERE flight_number = '" + flight_number + "' ";
        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();
        	
        	System.out.println("Hello Im here");
        	
            System.out.println("successful Update");
        	}
        	else {
       		 System.out.println("unsuccessful Update");
       		resp.sendRedirect(req.getContextPath() + "/jsp/DeleteFlight.jsp");
       		 j=1;
       	}
        	con.close();
        	if(j==0) {
        	resp.sendRedirect(req.getContextPath() + "/jsp/CREditFlights.jsp");
        	}
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    }
}