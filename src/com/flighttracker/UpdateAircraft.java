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

public class UpdateAircraft extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("profile admin DO GET called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect(req.getContextPath() + "/jsp/CRUpdateAircraft.jsp");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	System.out.println("profile admin DO POST called");
    	this.req = req;
    	this.resp = resp;
    	
    	String aircraft_id = req.getParameter("aircraft_id"); 
    	String airline_id = req.getParameter("airline_id"); 
    	String total_seats_economy = req.getParameter("total_seats_economy");  
    	String total_seats_first = req.getParameter("total_seats_first"); 
    	String total_seats_business = req.getParameter("total_seats_business"); 
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
        	rs = st.executeQuery("SELECT * from Aircraft WHERE aircraft_id = '" + aircraft_id + "' ");
        	if(rs.next()) {
        		if(airline_id.isEmpty()) {
            		airline_id = rs.getString("airline_id");
            	}
        		if(total_seats_economy.isEmpty()) {
        			total_seats_economy = rs.getString("total_seats_economy");
            	}
            	if(total_seats_first.isEmpty()) {
            		total_seats_first = rs.getString("total_seats_first");
            	}
            	if(total_seats_business.isEmpty()) {
            		total_seats_business = rs.getString("total_seats_business");
            	}

        		System.out.println(airline_id);
        		System.out.println(total_seats_economy);
        		System.out.println(total_seats_first);
        	}
        	
        	String a = ("select * from Aircraft where aircraft_id='"+aircraft_id+"'");
        	PreparedStatement ps = con.prepareStatement(a);
        	ResultSet rs2 = ps.executeQuery();
        	int count2 =0;
        	while(rs2.next())
        	{
        	count2++;
        	}
        	
        	String b = ("select * from Airlines where airline_id='"+airline_id+"'");
        	PreparedStatement ps3 = con.prepareStatement(b);
        	ResultSet rs3 = ps3.executeQuery();
        	int count3 =0;
        	while(rs3.next())
        	{
        	count3++;
        	}
        	
        	
        	//update the table
        	int j=0;
        	if(count2>0 && count3>0) {
        	String insert = "UPDATE Aircraft SET airline_id = '" + airline_id +"', total_seats_economy = '" + total_seats_economy +"', total_seats_first = '" + total_seats_first + "', total_seats_business = '" + total_seats_business + "' where aircraft_id = '" + aircraft_id + "' ";
        	PreparedStatement st1 = con.prepareStatement(insert);
        	st1.executeUpdate();
        	
            System.out.println("successful Update");
        	}
        	else {
       		System.out.println("unsuccessful Update");
       		resp.sendRedirect(req.getContextPath() + "/jsp/CRUpdateAircraft.jsp");
       		j=1;
       	}
        	con.close();
        	if(j==0) {
        	resp.sendRedirect(req.getContextPath() + "/jsp/CREditAircraft.jsp");
        	}
    	} catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
    }
}