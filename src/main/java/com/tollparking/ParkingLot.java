package com.tollparking;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tollparking.domain.ParkingSlotType;
import com.tollparking.domain.Ticket;
import com.tollparking.domain.TicketStatus;
import com.tollparking.domain.Vehicle;
import com.tollparking.domain.VehicleType;
import com.tollparking.exception.ParkingFullException;
import com.tollparking.service.ParkingSlotService;
import com.tollparking.service.TicketService;
import com.tollparking.util.BillingPolicy;
import com.tollparking.util.DefaultBillingPolicy;

/**
 * This class is the main class for the ParkingLot, with this the parking can be
 * configured.
 * 
 * @author hvanpariya
 *
 */
public class ParkingLot {

	private BillingPolicy billingPolicy;
	private TicketService ticketService;
	private ParkingSlotService parkingSlotService;

	private ParkingLot(Builder parkingLotBuilder) {
		this.parkingSlotService = ParkingSlotService.getInstance(parkingLotBuilder.allParkingSlotConfig);
		this.ticketService = TicketService.getInstance();
		if (parkingLotBuilder.billingPolicy == null) {

		} else {
			this.billingPolicy = parkingLotBuilder.billingPolicy;

		}
	}

	public Ticket assignParking(String licenseNumber, VehicleType vehicleType) throws ParkingFullException {
		// Check if Parking is full
		if (this.isFull(vehicleType)) {
			throw new ParkingFullException();
		}
		synchronized (this) {
			Vehicle vehicle = new Vehicle(licenseNumber, vehicleType);
			// Get Parking slot number
			Integer parkingSlotNumber = parkingSlotService.assignVehicleToSlot(vehicle);
			parkingSlotService.incrementSpotCount(vehicle.getType());
			return ticketService.createTicket(vehicle, parkingSlotNumber);
		}
	}

	public synchronized double vehicleExit(String ticketID) throws ParkingFullException {

		if (ticketID == null) {
			return -1;
		}
		if (!ticketService.hasTicket(ticketID)) {
			return -1;
		}
		Ticket parkingTicket = ticketService.find(ticketID);
		parkingTicket.setPayedAt(new Date());
		parkingTicket.setParkingTicketStatus(TicketStatus.PAID);

		double calculateParkingFees = billingPolicy.calculateParkingFees(parkingTicket);
		parkingTicket.setPayedAmount(calculateParkingFees);
		// Remove ticket from active tickets
		ticketService.delete(parkingTicket.getTicketNumber());

		parkingSlotService.freeParkingSlot(parkingTicket);

		return calculateParkingFees;
	}

	public boolean isFull(VehicleType type) {
		return parkingSlotService.isFull(type);
	}

	public static class Builder {

		BillingPolicy billingPolicy;
		Map<ParkingSlotType, Integer> allParkingSlotConfig = new HashMap<ParkingSlotType, Integer>();

		public Builder() {
		}

		public Builder addSlots(ParkingSlotType parkingSlotType, int count) {
			allParkingSlotConfig.put(parkingSlotType, count);
			return this;
		}

		public Builder addBillingPolicy(BillingPolicy billingPolicy) {
			this.billingPolicy = billingPolicy;
			return this;
		}

		public ParkingLot build() {
			ParkingLot parkingLot = new ParkingLot(this);
			return parkingLot;

		}
	}

	public static void main(String[] args) throws ParkingFullException {

		ParkingLot parkingLot = new ParkingLot.Builder().addSlots(ParkingSlotType.SLOT_20KV, 6)
				.addSlots(ParkingSlotType.SLOT_50KV, 6)
				.addBillingPolicy(new DefaultBillingPolicy.Builder().perHour(10).build()).build();

		Ticket allotedTicket = parkingLot.assignParking("AB11AA3939", VehicleType.CAR_20KV);
		allotedTicket.print();

		allotedTicket = parkingLot.assignParking("AB11AA393900", VehicleType.CAR_20KV);
		allotedTicket.setIssuedAt(new Date(119, 8, 14));

		double freeParking = parkingLot.vehicleExit("TKT-1");
		allotedTicket.print();

		allotedTicket = parkingLot.assignParking("AB11AA3939999", VehicleType.CAR_50KV);

		allotedTicket.print();
	}

}
