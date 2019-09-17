package com.tollparking.domain;

/**
 * 
 * @author hvanpariya
 *
 */
public class ParkingSlot {

	private int number;
	private boolean free = true;
	private Vehicle vehicle;
	private ParkingSlotType type;

	public ParkingSlot() {
		super();
	}
	
	public ParkingSlot(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
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
