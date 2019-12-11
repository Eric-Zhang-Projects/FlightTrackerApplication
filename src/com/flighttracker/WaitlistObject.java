package com.flighttracker;

import java.util.Date;

public class WaitlistObject {
	
	private int flight_number;
	private String username;
	private int waitlist_number;
	private String classType;

	
	public WaitlistObject() {
	}
	
	public WaitlistObject(int flight_number, String username, int waitlist_number, String classType) {
		this.flight_number = flight_number;
		this.username = username;
		this.waitlist_number = waitlist_number;
		this.classType = classType;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public int getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getWaitlist_number() {
		return waitlist_number;
	}

	public void setWaitlist_number(int waitlist_number) {
		this.waitlist_number = waitlist_number;
	}
}



