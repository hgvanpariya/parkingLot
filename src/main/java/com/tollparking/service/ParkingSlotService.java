package com.tollparking.service;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.tollparking.domain.ParkingSlot;
import com.tollparking.domain.ParkingSlotType;
import com.tollparking.domain.Ticket;
import com.tollparking.domain.Vehicle;
import com.tollparking.domain.VehicleType;
import com.tollparking.exception.InvalidParkingSlotTypeException;
import com.tollparking.exception.ParkingFullException;
import com.tollparking.repository.ParkingSlotRepository;

public class ParkingSlotService {

	private static ParkingSlotService parkingSlotService;
	private ParkingSlotRepository parkingSlotRepository;
	private Properties properties;

	private ParkingSlotService(Map<ParkingSlotType, Integer> allParkingSlotConfig) {
		parkingSlotRepository = new ParkingSlotRepository();
		parkingSlotRepository.initializeRepository(allParkingSlotConfig);
		properties = new Properties();
		try {
			properties.load(ParkingSlotService.class.getResourceAsStream("VehicleTypeParkingSlotMapping.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ParkingSlotService getInstance(Map<ParkingSlotType, Integer> allParkingSlotConfig) {
		if (parkingSlotService == null) {
			parkingSlotService = new ParkingSlotService(allParkingSlotConfig);
		}
		return parkingSlotService;
	}

	/**
	 * This method will find the fee parking slot for the vehicle.
	 * 
	 * @param vehicle
	 * @return
	 * @throws ParkingFullException
	 */
	public Integer findFreeSlotForVehicle(Vehicle vehicle) throws ParkingFullException {
		Map<Integer, ParkingSlot> map = parkingSlotRepository.getAllSlots().get(convert(vehicle.getType()));
		for (Integer eachSlotNumber : map.keySet()) {
			if (map.get(eachSlotNumber).isFree()) {
				return eachSlotNumber;
			}
		}
		throw new ParkingFullException();
	}

	/**
	 * This method will assign a vehicle to an empty slot.
	 * 
	 * @param vehicle
	 * @return
	 * @throws ParkingFullException
	 */
	public Integer assignVehicleToSlot(Vehicle vehicle) throws ParkingFullException {
		Integer findFreeSlotForVehicle = findFreeSlotForVehicle(vehicle);
		Map<Integer, ParkingSlot> map = parkingSlotRepository.getAllSlots().get(convert(vehicle.getType()));
		ParkingSlot parkingSlot = map.get(findFreeSlotForVehicle);
		parkingSlot.setVehicle(vehicle);
		parkingSlot.setFree(false);
		return findFreeSlotForVehicle;
	}

	public void incrementSpotCount(VehicleType type) {
		Integer totalAllotedSlot = parkingSlotRepository.getAllAllotedSlotCount().get(convert(type)) + 1;
		parkingSlotRepository.getAllAllotedSlotCount().put(convert(type), totalAllotedSlot);
	}

	/**
	 * This method will help to perform operations on ParkingSlot when vehicle is
	 * leaving.
	 * 
	 * @param ticket
	 */
	public void freeParkingSlot(Ticket ticket) {
		ParkingSlotType parkingSlotType = convert(ticket.getVehicle().getType());

		// Free Parking slot
		ParkingSlot parkingSlot = parkingSlotRepository.getAllSlots().get(parkingSlotType)
				.get(ticket.getParkingSlotNumber());
		parkingSlot.setFree(true);
		parkingSlot.setVehicle(null);
		parkingSlot.setType(parkingSlotType);

		// Reduce slot count to one lesser
		Integer allotedSLotCount = parkingSlotRepository.getAllAllotedSlotCount().get(parkingSlotType);
		parkingSlotRepository.getAllAllotedSlotCount().put(parkingSlotType, allotedSLotCount - 1);
	}

	/**
	 * This method will check if the parking is full for the given type of vehicle.
	 * 
	 * @param type Type of the vehicle for which the parking has to be searched.
	 * @return status of the parking slot, it will return true if parking slot is
	 *         available else it will return false.
	 */
	public boolean isFull(VehicleType type) {
		ParkingSlotType parkingSlotType = convert(type);
		Integer totalAllotedSlot = parkingSlotRepository.getAllAllotedSlotCount().get(parkingSlotType);
		Integer totalAvailableSlots = parkingSlotRepository.getTotalSlotConfig().get(parkingSlotType);
		if (totalAllotedSlot == null) {
			throw new InvalidParkingSlotTypeException(type);
		}
		return totalAllotedSlot >= totalAvailableSlots;
	}

	/**
	 * This method is the converter from {@link VehicleType} to {@link ParkingSlotType}
	 * @param vehicleType
	 * @return
	 */
	public ParkingSlotType convert(VehicleType vehicleType) {
		return ParkingSlotType.valueOf(properties.getProperty(vehicleType.toString()));
	}
	
	/**
	 * This method will clean the parking. It will be mainly used for test.
	 */
	public void cleanSpecificParkingType(ParkingSlotType parkingSlotType) {
		parkingSlotRepository.getAllAllotedSlotCount().put(parkingSlotType,0);
	}
}
