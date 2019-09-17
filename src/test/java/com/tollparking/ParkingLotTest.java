/**
 * 
 */
package com.tollparking;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.tollparking.domain.ParkingSlotType;
import com.tollparking.domain.Ticket;
import com.tollparking.domain.TicketStatus;
import com.tollparking.domain.VehicleType;
import com.tollparking.exception.InvalidParkingSlotTypeException;
import com.tollparking.exception.ParkingFullException;
import com.tollparking.service.ParkingSlotService;
import com.tollparking.util.DefaultBillingPolicy;

/**
 * @author hvanpariya
 *
 */
public class ParkingLotTest {

	ParkingLot parkingLot;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		parkingLot = new ParkingLot.Builder()
				.addSlots(ParkingSlotType.SLOT_50KV, 6).addSlots(ParkingSlotType.STANDARD_SLOT, 6)
				.addBillingPolicy(new DefaultBillingPolicy.Builder().perHour(10).build()).build();

	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#assignParking(java.lang.String, com.tollparking.domain.VehicleType)}.
	 * 
	 * @throws ParkingFullException
	 */
	@Test
	public void testAssignParking() throws ParkingFullException {
		Ticket allotedTicket = parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
		allotedTicket.print();
		assertNotNull(allotedTicket);
		assertTrue(allotedTicket.getParkingTicketStatus() == TicketStatus.ACTIVE);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#assignParking(java.lang.String, com.tollparking.domain.VehicleType)}.
	 * 
	 * @throws ParkingFullException
	 */
	@Test
	public void testAssignParkingNull() throws ParkingFullException {
		Ticket allotedTicket = parkingLot.assignParking(null, VehicleType.CAR_50KV);
		assertNull(allotedTicket);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#vehicleExit(java.lang.String)}.
	 * 
	 * @throws ParkingFullException
	 * @throws InterruptedException
	 */
	@Test
	public void testVehicleExit() throws ParkingFullException, InterruptedException {
		Ticket allotedTicket = parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
		allotedTicket.setIssuedAt(new Date(119, 8, 14));
		double vehicleExit = parkingLot.vehicleExit(allotedTicket.getTicketNumber());
		assertTrue(vehicleExit > 0);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#vehicleExit(java.lang.String)}.
	 * 
	 * @throws ParkingFullException
	 * @throws InterruptedException
	 */
	@Test
	public void testVehicleInvalidExit() throws ParkingFullException, InterruptedException {
		double vehicleExit = parkingLot.vehicleExit("sdss");
		assertTrue(vehicleExit == -1);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#isFull(com.tollparking.domain.VehicleType)}.
	 * 
	 * @throws ParkingFullException
	 */
	@Test
	public void testIsFullException() throws ParkingFullException {
		try {
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_50KV);
		} catch (ParkingFullException pfe) {
			assertTrue(true);
		} finally {
		}

	}

	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#isFull(com.tollparking.domain.VehicleType)}.
	 * 
	 * @throws ParkingFullException
	 */
	@Test
	public void testIsFull() throws ParkingFullException {
		try {
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
			parkingLot.assignParking("AB11AA3939", VehicleType.SEDAN_CAR);
		} catch (ParkingFullException e) {
			String message = e.getMessage();
			assertTrue(!message.isEmpty());
		} finally {
			boolean full = parkingLot.isFull(VehicleType.SEDAN_CAR);
			assertTrue(full);
		}
	}
	
	/**
	 * Test method for
	 * {@link com.tollparking.ParkingLot#isFull(com.tollparking.domain.VehicleType)}.
	 * 
	 * @throws ParkingFullException
	 */
	@Test
	public void testInvalidCarType() throws ParkingFullException {
		try {
			parkingLot.assignParking("AB11AA3939", VehicleType.CAR_20KV);
			
		} catch (InvalidParkingSlotTypeException e) {
			String message = e.getMessage();
			assertTrue(!message.isEmpty());
		}
	}

}
