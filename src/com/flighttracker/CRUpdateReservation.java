package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

//@WebServlet("/CREditReservation")
public class CRUpdateReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		//ignore for now
//    	System.out.println("entering edit reservation");
//	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("entering edit ticket");
    	Map<String, String[]> parameters = request.getParameterMap();
    	Map<String, String> oldValues = new HashMap<String, String>();
    	Map<String, String> newValues = new HashMap<String, String>();
    	for (String parameter : parameters.keySet()) {
    		System.out.println("PARAM" + parameters.get(parameter)[0]);
    		if(!parameters.get(parameter)[0].isEmpty()) {
    			if (parameter.contains("Update")) {
    				newValues.put(parameter, parameters.get(parameter)[0]);
    			}
    			else {
    				oldValues.put(parameter, parameters.get(parameter)[0]);
    			}	
    		}
    	}
    	System.out.println("old TABLE: " + oldValues.entrySet());
    	System.out.println("new TABLE: " + newValues.entrySet());
    	int ticket_number = Integer.parseInt(oldValues.get("ticket_number"));
    	String query = "";
    	for (String value : newValues.keySet()) {
    		String name = value.substring(0,value.length()-6);
    		System.out.println(value);
    		query += name + "='" + newValues.get(value) + "', ";
    	}
    	query = query.substring(0,query.length()-2);
    	System.out.println(query);

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
		        System.out.println("check customer info");
		        System.out.println("UPDATE Ticket SET " + query + " WHERE ticket_number='" + ticket_number +"'");
		        int success = st.executeUpdate("UPDATE Ticket SET " + query + " WHERE ticket_number='" + ticket_number +"'");
		        //int success = 1;
		        con.close();
		        //login successful
		        if (success > 0) {
		        	System.out.println("update success");
		        	//JOptionPane.showMessageDialog(null, "Update Successful!");
		        	response.sendRedirect("jsp/homeCustomerrep.jsp");
		        }
		        else {
		        	System.out.println("error");
		        	response.sendRedirect("");
		        }
		        
		        con.close();
	        } catch (SQLException e){
	        	System.out.println("connection failed");
	        	e.printStackTrace();
	        }
	
			
	}
}
