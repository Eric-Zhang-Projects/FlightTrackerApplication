package com.flighttracker;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Array;

//@WebServlet("/CREditReservation")
public class CustomerMostRevenue extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		        ResultSet customers;
		        System.out.println("check tickets list");
		        customers = st.executeQuery("SELECT username, total_fare FROM Ticket");
		        HashMap<String, Integer> table = new HashMap<String, Integer>();
		        while (customers.next()) {
		        	int fare = customers.getInt("total_fare");
		        	String username = customers.getString("username");
		        	if (!table.containsKey(username)) {
		        		table.put(username, fare);
		        	}
		        	else {
		        		table.put(username, table.get(username)+fare);
		        	}
		        }
		        int greatest = 0;
		        int temp =0;
		        String usr = "";
		        for (String user : table.keySet()) {
		        	temp = table.get(user);
		        	if (temp > greatest) {
		        		greatest = temp;
		        		usr = user;
		        	}
		        }
		        con.close();
		        System.out.println(greatest);
		        String[] great = new String[]{usr, Integer.toString(greatest)};
				request.setAttribute("greatest", great); 
				getServletContext().getRequestDispatcher("/jsp/customerMostRevenue.jsp").forward(request, response);

		
	} catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		}
	}
