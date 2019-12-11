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
public class FlightMostSold extends HttpServlet {
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
		        ResultSet flights;
		        System.out.println("check tickets list");
		        flights = st.executeQuery("SELECT flight_number FROM Ticket");
		        HashMap<Integer, Integer> table = new HashMap<Integer, Integer>();
		        while (flights.next()) {
		        	int flight_number = flights.getInt("flight_number");
		        	if (!table.containsKey(flight_number)) {
		        		table.put(flight_number, 1);
		        	}
		        	else {
		        		table.put(flight_number, table.get(flight_number)+1);
		        	}
		        }
		        int greatest = 0;
		        int temp =0;
		        int flight_numb = 0;
		        for (int fn : table.keySet()) {
		        	temp = table.get(fn);
		        	if (temp > greatest) {
		        		greatest = temp;
		        		flight_numb = fn;
		        	}
		        }
		        con.close();
		        System.out.println(greatest);
		        int[] great = new int[]{flight_numb, greatest};
				request.setAttribute("greatest", great); 
				getServletContext().getRequestDispatcher("/jsp/flightMostSold.jsp").forward(request, response);

		
	} catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
	}
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		}
	}
