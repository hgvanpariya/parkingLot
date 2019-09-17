package com.tollparking.domain;

/**
 * 
 * @author hvanpariya
 *
 */
public class ParkingSlot {

	private String number;
	private boolean free = true;
	private Vehicle vehicle;
	private ParkingSlotType type;

	public ParkingSlot() {
		super();
	}

	public ParkingSlot(String number, boolean free, Vehicle vehicle, ParkingSlotType type) {
		super();
		this.number = number;
		this.free = free;
		this.vehicle = vehicle;
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public ParkingSlotType getType() {
		return type;
	}

	public void setType(ParkingSlotType type) {
		this.type = type;
	}

}
