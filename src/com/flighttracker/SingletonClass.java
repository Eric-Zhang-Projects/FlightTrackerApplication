package com.flighttracker;

import com.mysql.jdbc.Connection;

public class SingletonClass {

	
	private static SingletonClass sc;
	
	public static SingletonClass getSingleton() {
		if (sc == null) {
			sc = new SingletonClass();
		}
		return sc;
	}
	
	private SingletonClass() {
		
		 System.out.println("Object created.");
	}
	
	
	protected Connection getConnection() {
		return null;
	}
	
	
}
