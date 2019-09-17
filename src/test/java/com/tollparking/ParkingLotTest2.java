package com.tollparking;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.tollparking.domain.Ticket;
import com.tollparking.domain.VehicleType;
import com.tollparking.exception.ParkingFullException;

public class ParkingLotTest2 {

	static ParkingLot parkingLot;

	@BeforeClass
	public static void setup() {
		parkingLot = new ParkingLot.Builder().build();
	}

	@Test
	public void assignParkingTest() throws ParkingFullException {
		Ticket assignParking = parkingLot.assignParking("AB292929", VehicleType.SEDAN_CAR);
		assertTrue(true);
	}


}
