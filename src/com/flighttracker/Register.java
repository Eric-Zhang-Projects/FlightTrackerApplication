package com.flighttracker;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Register extends HttpServlet {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	    
    public static void thisIsStaticMethod() {
    	System.out.println("static called");
    	
    	
    }
	   
	  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        System.out.println("DO GET Called");
        resp.sendRedirect(req.getContextPath() + "/jsp/register.jsp");
    }
	    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.req = req;
        this.resp = resp;
        
        String firstName = req.getParameter("firstName");   
    	String lastName = req.getParameter("lastName");
        String username = req.getParameter("username");   
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");
        
        try{
        Class.forName("com.mysql.jdbc.Driver"); 
        System.out.println("driver found");
        } catch (ClassNotFoundException e){
        	System.out.println("No driver found");
        	e.printStackTrace();
        	return;
        }
        
        //SingletonClass sc = SingletonClass.getSingleton();
        
        
        String url  = "jdbc:mysql://cs336db.c0d2khgtglaj.us-east-2.rds.amazonaws.com:3306/travel";
        try{
        Connection con = DriverManager.getConnection(url, "cs336", "admin123");
        //Connection con = sc.getConnection();
        //Statement findId = con.createStatement();
        Statement checkS = con.createStatement();
        ResultSet checkR;
        checkR = checkS.executeQuery("SELECT username FROM Customer WHERE username ='" + username + "'");
        if (checkR.next()) {
        	con.close();
        	resp.sendRedirect("jsp/register.jsp");
        }
        else {
        String insert = "INSERT INTO Customer (first_name, last_name, username, password) VALUES (?, ?, ?, ?)";
        PreparedStatement st = con.prepareStatement(insert);
       	st.setString(1, firstName);
       	st.setString(2, lastName);
       	st.setString(3, username);
       	st.setString(4, password);
        st.executeUpdate();
        con.close();
        System.out.println("successful update");
        con.close();
        resp.sendRedirect(req.getContextPath() + "/");
        }
        
        } catch (SQLException e){
        	System.out.println("connection failed");
        	e.printStackTrace();
        }
        //end doPost
    }

}