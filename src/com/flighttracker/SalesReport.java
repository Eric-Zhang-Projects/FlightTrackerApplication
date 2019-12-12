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
public class SalesReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	//sales report will have by month:
	//total tickets bought
	//total flights operating in this month
	//total revenue
	//most tickets bought by a particular flight, 
	//most revenue generated  by airline, 
	/*
month: [month as string] (done)
total_tickets: [total as string]
total_flights: [total as string]
total_revenue: [total as string]
most_by_flight: [flight_number, total]
most_by_airline: [airline_id, total]

	 */

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("class driver found");
		} catch (ClassNotFoundException e){
			System.out.println("No driver found");
			e.printStackTrace();
        	return;
		}	 
        int month = Integer.parseInt(request.getParameter("month"));
        String monthS= monthToS(month);
       
        HashMap<String, String[]> table = new HashMap<String, String[]>();
        String[] value = new String[] {monthS};
        table.put("month", value);
        
    	String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
        try{
	        Connection con = DriverManager.getConnection(url, "cs336", "admin123");
	        Statement st = con.createStatement();
	        ResultSet allTicketsRS, allFlightsRS, totalRevenueRS, flightRS, airlineRS;
	        
	        //total_tickets: [total as string]
	        
	        String query = "SELECT issue_date FROM Ticket";
	        allTicketsRS = st.executeQuery(query);
	        int total_tickets = 0;
	        while(allTicketsRS.next()) {
	        	String date = allTicketsRS.getString("issue_date");
	        	String monthRS = date.substring(5,7);
	        	System.out.println("month: " +monthRS + " " + Integer.toString(month));
	        	if (monthRS.equals(Integer.toString(month))) {
	        		total_tickets++;
	        	}
	        }
	        System.out.println("total tickets in month:" + total_tickets);
	        value = new String[] {Integer.toString(total_tickets)};
	        table.put("total_tickets", value);
	        con.close();
	        
	        //total_flights: [total as string]
	        con = DriverManager.getConnection(url, "cs336", "admin123");
	        st = con.createStatement();
	        query = "SELECT arrive_date, depart_date FROM Flights";
	        allFlightsRS = st.executeQuery(query);
	        int total_flights =0;
	        while(allFlightsRS.next()) {
	        	String Adate = allFlightsRS.getString("arrive_date");
	        	System.out.println(Adate);
	        	String Ddate = allFlightsRS.getString("depart_date");
	        	String AmonthRS="";
	        	String DmonthRS ="";
	        	if (Adate != null) {
	        		AmonthRS = Adate.substring(5,7);
	        	}
	        	if (Ddate!= null) {
	        		DmonthRS = Ddate.substring(5,7);
	        	}
	        	//System.out.println("month: " +AmonthRS + " " + Integer.toString(month));
	        	if (AmonthRS.equals(Integer.toString(month)) || DmonthRS.equals(Integer.toString(month))) {
	        		total_flights++;
	        	}
	        }	        
	        System.out.println("total flights in month:" + total_flights);
	        value = new String[] {Integer.toString(total_flights)};
	        table.put("total_flights", value);
	        con.close();
	        
	        //total_revenue: [total as string]
	        con = DriverManager.getConnection(url, "cs336", "admin123");
	        st = con.createStatement();
	        query = "SELECT issue_date, total_fare FROM Ticket";
	        totalRevenueRS = st.executeQuery(query);
	        int totalRevenue = 0;
	        while(totalRevenueRS.next()) {
	        	String date = totalRevenueRS.getString("issue_date");
	        	String monthRS = date.substring(5,7);
	        	int revenue = totalRevenueRS.getInt("total_fare");
	        	if (monthRS.equals(Integer.toString(month))){	
	        		totalRevenue+=revenue;
	        	}
	        }
	        value = new String[] {Integer.toString(totalRevenue)};
	        table.put("total_revenue", value);
	        con.close();	        
	        
	        
	        
	        
	      //most_by_flight: [flight_number, total]
	        con = DriverManager.getConnection(url, "cs336", "admin123");
	        st = con.createStatement();
	        query = "SELECT issue_date, flight_number FROM Ticket";
	        flightRS = st.executeQuery(query);	
	        HashMap<Integer, Integer> temp = new HashMap<Integer, Integer>();
	        while(flightRS.next()) {
	        	String date = flightRS.getString("issue_date");
	        	String monthRS = date.substring(5,7);
	        	if (monthRS.equals(Integer.toString(month))){
	        		int flight_number = flightRS.getInt("flight_number");
	        		if (!temp.containsKey(flight_number)) {
	        			temp.put(flight_number, 1);
	        		}
	        		else {
	        			temp.put(flight_number, temp.get(flight_number)+1);
	        		}
	        	}
	        }
	        int greatest = 0;
	        int t =0;
	        int flight_numb = 0;
	        for (int fn : temp.keySet()) {
	        	t = temp.get(fn);
	        	if (t > greatest) {
	        		greatest = t;
	        		flight_numb = fn;
	        	}
	        }
	        value = new String[] {Integer.toString(flight_numb), Integer.toString(greatest)};
	        table.put("flight_number", value);
	        con.close();
	        

	        
	      //most_by_airline: [airline_id, total]
	        con = DriverManager.getConnection(url, "cs336", "admin123");
	        st = con.createStatement();
	        query = "SELECT issue_date, airline_id, total_fare FROM Ticket";
	        airlineRS = st.executeQuery(query);	
	        HashMap<String, Integer> temp1 = new HashMap<String, Integer>();
	      //  int total = 0;
	        while(airlineRS.next()) {
	        	String airline_id = airlineRS.getString("airline_id");
	        	int fare = airlineRS.getInt("total_fare");
	        	String date = airlineRS.getString("issue_date");
	        	String monthRS = date.substring(5,7);
	        	if (monthRS.equals(Integer.toString(month))){
	        		if (!temp1.containsKey(airline_id)) {
	        			temp1.put(airline_id, fare);
	        		}
	        		else {
	        			temp1.put(airline_id, temp1.get(airline_id)+fare);
	        		}
	        }
	        }
		      greatest = 0;
		      t =0;
		      String airline_id = "";
		      for (String aid : temp1.keySet()) {
		        	t = temp1.get(aid);
		        	if (t > greatest) {
		        		greatest = t;
		        		airline_id = aid;
		        	}
		        }	
		      	value = new String[] {airline_id, Integer.toString(greatest)};
		        table.put("airline_id", value);
		        con.close(); 
	        
	        		    
	        
		        System.out.println("table:");
		        for (String aa : table.keySet()) {
			        System.out.println(aa + " " + table.get(aa)[0]);
		        }
		    request.setAttribute("table", table); 
		    getServletContext().getRequestDispatcher("/jsp/salesReport.jsp").forward(request, response);
		           
	        
	       
        } catch (SQLException e){
    	System.out.println("connection failed");
    	e.printStackTrace();
    }
		}
    
    private String monthToS(int month) {
    	String monthS="";
    	 switch(month) {
         case 1: monthS = "January";
         break;
         case 2: monthS = "February";
         break;
         case 3: monthS = "March";
         break;
         case 4: monthS = "April";
         break;
         case 5: monthS = "May";
         break;
         case 6: monthS = "June";
         break;
         case 7: monthS = "July";
         break;
         case 8: monthS = "August";
         break;
         case 9: monthS = "Septmeber";
         break;
         case 10: monthS = "October";
         break;
         case 11: monthS = "November";
         break;
         case 12: monthS = "December";
         break;
         }
    	 return monthS;
    }
	}
