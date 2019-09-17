package com.tollparking.repository;

import java.util.HashMap;
import java.util.Map;

import com.tollparking.domain.ParkingSlot;
import com.tollparking.domain.ParkingSlotType;

/**
 * This class will store and retrieve the data related to Parking slot.
 * 
 * @author hvanpariya
 *
 */
public class ParkingSlotRepository {
	Map<ParkingSlotType, Map<Integer, ParkingSlot>> allSlots;
	Map<ParkingSlotType, Integer> totalSlotConfig;
	Map<ParkingSlotType, Integer> allAllotedSlotCount;

	public ParkingSlotRepository() {
		this.allSlots = new HashMap<ParkingSlotType, Map<Integer, ParkingSlot>>();
		this.totalSlotConfig = new HashMap<ParkingSlotType, Integer>();
		this.allAllotedSlotCount = new HashMap<ParkingSlotType, Integer>();
	}

	/**
	 * This method will initialize the repository
	 * 
	 * @param allParkingSlotConfig
	 */
	public void initializeRepository(Map<ParkingSlotType, Integer> allParkingSlotConfig) {
		int count = 0;
		for (ParkingSlotType parkinSlotType : allParkingSlotConfig.keySet()) {
			totalSlotConfig.put(parkinSlotType, allParkingSlotConfig.get(parkinSlotType));
			allAllotedSlotCount.put(parkinSlotType, 0);
			Map<Integer, ParkingSlot> newSlotDetails = new HashMap<Integer, ParkingSlot>();
			for (int i = 0; i < allParkingSlotConfig.get(parkinSlotType); i++) {
				newSlotDetails.put(count, new ParkingSlot(count));
				count++;
			}
			allSlots.put(parkinSlotType, newSlotDetails);
		}

	}

	public Map<ParkingSlotType, Map<Integer, ParkingSlot>> getAllSlots() {
		return this.allSlots;
	}

	public Map<ParkingSlotType, Integer> getAllAllotedSlotCount() {
		return allAllotedSlotCount;
	}

	public Map<ParkingSlotType, Integer> getTotalSlotConfig() {
		return totalSlotConfig;
	}
}
