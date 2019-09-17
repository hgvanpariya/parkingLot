package com.tollparking.exception;

public class ParkingFullException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4098031264235127179L;

	@Override
	public String getMessage() {
		return "ERR001: Parking is full";
	}
}
