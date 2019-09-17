package com.tollparking.domain;

import java.util.Date;

public class Ticket {

	String ticketNumber;
	Date issuedAt;
	Date payedAt;
	double payedAmount;
	TicketStatus parkingTicketStatus;
	Integer parkingSlotNumber;
	Vehicle vehicle;

	public Ticket() {
		super();
	}

	public Integer getParkingSlotNumber() {
		return parkingSlotNumber;
	}

	public void setParkingSlotNumber(Integer parkingSlotNumber) {
		this.parkingSlotNumber = parkingSlotNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public Date getPayedAt() {
		return payedAt;
	}

	public void setPayedAt(Date payedAt) {
		this.payedAt = payedAt;
	}

	public double getPayedAmount() {
		return payedAmount;
	}

	public void setPayedAmount(double payedAmount) {
		this.payedAmount = payedAmount;
	}

	public TicketStatus getParkingTicketStatus() {
		return parkingTicketStatus;
	}

	public void setParkingTicketStatus(TicketStatus parkingTicketStatus) {
		this.parkingTicketStatus = parkingTicketStatus;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public void print() {
		System.out.println("============Ticket: "+ticketNumber+"============");
		System.out.println("Ticket Number = " + ticketNumber);
		System.out.println("Issued at = " + issuedAt);
		System.out.println("Payed at = " + payedAt);
		System.out.println("Total Bill = " + payedAmount);
		System.out.println("Ticket Status = " + parkingTicketStatus);
		System.out.println("-------------------------------------------");
		System.out.println("Parking Slot Number = " + parkingSlotNumber);
		System.out.println("Vehicle Number = " + vehicle.getLicenseNumber());
		System.out.println("Vehicle type = " + vehicle.getType());
		System.out.println("===========================================");

	}


}
