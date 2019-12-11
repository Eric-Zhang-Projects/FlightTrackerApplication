package com.flighttracker;

import java.util.Date;

public class TicketObject {
	
	private int number;
	private int round_trip;
	private int booking_fee;
	private Date issue_date;
	private int total_fare;
	private int cancel_fee;
	private int meal;
	private int waitlist_number;
	private String username;
	private int flight_number;
	private String airline_id;
	private int seat_number;
	private String classType;
	
	public TicketObject() {
	}
	
	public TicketObject(int number, int round_trip, int booking_fee, Date issue_date, int total_fare, int cancel_fee, int meal, 
			int waitlist_number, String username, int flight_number, String airline_id, int seat_number, String classType) {
		this.number = number;
		this.round_trip = round_trip;
		this.booking_fee = booking_fee;
		this.issue_date = issue_date;
		this.total_fare = total_fare;
		this.cancel_fee = cancel_fee;
		this.meal = meal;
		this.waitlist_number = waitlist_number;
		this.username = username;
		this.flight_number = flight_number;
		this.airline_id = airline_id;
		this.seat_number = seat_number;
		this.classType = classType;
	}

	public int getNumber() {
		return number;
	}

	public String getClassType() {
		return classType;
	}

	public void setClassType(String classType) {
		this.classType = classType;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getRound_trip() {
		return round_trip;
	}

	public void setRound_trip(int round_trip) {
		this.round_trip = round_trip;
	}

	public int getBooking_fee() {
		return booking_fee;
	}

	public void setBooking_fee(int booking_fee) {
		this.booking_fee = booking_fee;
	}

	public Date getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(Date issue_date) {
		this.issue_date = issue_date;
	}

	public int getTotal_fare() {
		return total_fare;
	}

	public void setTotal_fare(int total_fare) {
		this.total_fare = total_fare;
	}

	public int getCancel_fee() {
		return cancel_fee;
	}

	public void setCancel_fee(int cancel_fee) {
		this.cancel_fee = cancel_fee;
	}

	public int getMeal() {
		return meal;
	}

	public void setMeal(int meal) {
		this.meal = meal;
	}

	public int getWaitlist_number() {
		return waitlist_number;
	}

	public void setWaitlist_number(int waitlist_number) {
		this.waitlist_number = waitlist_number;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(int flight_number) {
		this.flight_number = flight_number;
	}

	public String getAirline_id() {
		return airline_id;
	}

	public void setAirline_id(String airline_id) {
		this.airline_id = airline_id;
	}

	public int getSeat_number() {
		return seat_number;
	}

	public void setSeat_number(int seat_number) {
		this.seat_number = seat_number;
	}
	
	

}
