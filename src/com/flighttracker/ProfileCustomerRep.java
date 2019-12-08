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
		System.out.println("profileCustomerRep doGET Called");
        this.req = req;
        this.resp = resp;
        resp.sendRedirect("jsp/profileCustomer.jsp");
    }
}