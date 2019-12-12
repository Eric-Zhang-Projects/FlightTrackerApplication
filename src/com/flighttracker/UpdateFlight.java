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

public class UpdateFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile admin DO GET called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect(req.getContextPath() + "/jsp/CRUpdateFlights.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	String flight_number = req.getParameter("flight_number"); 
    	String depart_date = req.getParameter("depart_date"); 
    	String arrive_date = req.getParameter("arrive_date");  
    	String depart_time = req.getParameter("depart_time"); 
    	String arrive_time = req.getParameter("arrive_time"); 
    	String fare_first = req.getParameter("fare_first"); 
    	String fare_economy = req.getParameter("fare_economy"); 
    	String fare_business = req.getParameter("fare_business"); 
    	String available_seats_first = req.getParameter("available_seats_first"); 
    	String available_seats_economy = req.getParameter("available_seats_economy"); 
    	String available_seats_business = req.getParameter("available_seats_business"); 
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
        	rs = st.executeQuery("SELECT * from Flights WHERE flight_number = '" + flight_number + "' ");
        	if(rs.next()) {
        		if(flight_number.isEmpty()) {
        			flight_number = rs.getString("flight_number");
            	}
        		if(depart_date.isEmpty()) {
        			depart_date = rs.getString("depart_date");
            	}
            	if(arrive_date.isEmpty()) {
            		arrive_date = rs.getString("arrive_date");
            	}
            	if(depart_time.isEmpty()) {
            		depart_time = rs.getString("depart_time");
            	}
            	if(arrive_time.isEmpty()) {
            		arrive_time = rs.getString("arrive_time");
            	}
            	if(fare_first.isEmpty()) {
            		fare_first = rs.getString("fare_first");
            	}
            	if(fare_economy.isEmpty()) {
            		fare_economy = rs.getString("fare_economy");
            	}
            	if(fare_business.isEmpty()) {
            		fare_business = rs.getString("fare_business");
            	}
            	if(available_seats_first.isEmpty()) {
            		available_seats_first = rs.getString("available_seats_first");
            	}
            	if(available_seats_economy.isEmpty()) {
            		available_seats_economy = rs.getString("available_seats_economy");
            	}
            	if(available_seats_business.isEmpty()) {
            		available_seats_business = rs.getString("available_seats_business");
            	}
        	}
        	
        	String a = ("select * from Flights where flight_number='"+flight_number+"'");
        	PreparedStatement ps = con.prepareStatement(a);
        	ResultSet rs2 = ps.executeQuery();
        	int count2 =0;
        	while(rs2.next())
        	{
        	count2++;
        	}
        	
        	
        	//update the table
        	
        	int j=0;
        	
        	if (count2 > 0) {
        	String insert = "UPDATE Flights SET depart_date = '" + depart_date +"', arrive_date = '" + arrive_date +"', depart_time = '" + depart_time + "', arrive_time = '" + arrive_time + "', fare_first = '" + fare_first + "', fare_economy = '" + fare_economy + "', fare_business = '" + fare_business + "', available_seats_first = '" + available_seats_first + "', available_seats_economy = '" + available_seats_economy + "', available_seats_business = '" + available_seats_business + "' where flight_number = '" + flight_number + "' ";

        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();
            System.out.println("successful Update");
        	}
        	else {
        		System.out.println("unsuccessful Update");
           		resp.sendRedirect(req.getContextPath() + "/jsp/CRUpdateFlight.jsp");
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