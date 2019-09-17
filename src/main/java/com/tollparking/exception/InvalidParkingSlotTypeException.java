package com.tollparking.exception;

import com.tollparking.domain.VehicleType;

/**
 * 
 * @author hvanpariya
 *
 */
public class InvalidParkingSlotTypeException extends RuntimeException {

	
	private static final long serialVersionUID = 7271625899816803333L;
	VehicleType type;
	public InvalidParkingSlotTypeException(VehicleType type) {
		this.type = type;
	}

	@Override
	public String getMessage() {
		return "ERR002: Parking slot is not configured for given car type ( "+type+" ).";
	}
}
