package com.tollparking.repository;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.tollparking.domain.ParkingSlot;
import com.tollparking.domain.ParkingSlotType;

public class ParkingSlotRepositoryTest {

	ParkingSlotRepository parkingSlotRepository;

	@Before
	public void setUp() throws Exception {
		parkingSlotRepository = new ParkingSlotRepository();
		Map<ParkingSlotType, Integer> allParkingSlotConfig = new HashMap<ParkingSlotType, Integer>();
		;
		allParkingSlotConfig.put(ParkingSlotType.SLOT_20KV, 2);
		parkingSlotRepository.initializeRepository(allParkingSlotConfig);
	}

	@Test
	public void testGetAllSlots() {
		Map<ParkingSlotType, Map<Integer, ParkingSlot>> allSlots = parkingSlotRepository.getAllSlots();
		assertTrue(allSlots.containsKey(ParkingSlotType.SLOT_20KV));
		assertTrue(allSlots.get(ParkingSlotType.SLOT_20KV).get(0) != null);
		assertTrue(allSlots.get(ParkingSlotType.SLOT_20KV).get(1) != null);
	}

	@Test
	public void testGetAllAllotedSlotCount() {
		Map<ParkingSlotType, Integer> allAllotedSlotCount = parkingSlotRepository.getAllAllotedSlotCount();
		assertTrue(allAllotedSlotCount.containsKey(ParkingSlotType.SLOT_20KV));
		assertTrue(allAllotedSlotCount.get(ParkingSlotType.SLOT_20KV) == 0);
	}

	@Test
	public void testGetTotalSlotConfig() {
		Map<ParkingSlotType, Integer> totalSlotConfig = parkingSlotRepository.getTotalSlotConfig();
		assertTrue(totalSlotConfig.containsKey(ParkingSlotType.SLOT_20KV));
		assertTrue(totalSlotConfig.get(ParkingSlotType.SLOT_20KV) == 2);
	}
	
	@Test
	public void testGetAllSlotsNull() {
		parkingSlotRepository = new ParkingSlotRepository();
		Map<ParkingSlotType, Map<Integer, ParkingSlot>> allSlots = parkingSlotRepository.getAllSlots();
		assertTrue(!allSlots.containsKey(ParkingSlotType.SLOT_20KV));
		assertTrue(allSlots.isEmpty());
	}

}
