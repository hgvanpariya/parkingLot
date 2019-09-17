/**
 * 
 */
package com.tollparking.service;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.tollparking.domain.Ticket;
import com.tollparking.domain.Vehicle;
import com.tollparking.service.TicketService;

/**
 * @author hvanpariya
 *
 */
public class TicketServiceTest {

	TicketService ticketService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ticketService = TicketService.getInstance();
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#createTicket(com.tollparking.domain.Vehicle, java.lang.Integer)}.
	 */
	@Test
	public void testCreateTicket() {
		Vehicle vehicle = new Vehicle();
		Ticket createTicket = ticketService.createTicket(vehicle, 1);
		assertTrue(createTicket.getParkingSlotNumber() == 1);
		assertTrue(createTicket.getVehicle().equals(vehicle));
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#createTicket(com.tollparking.domain.Vehicle, java.lang.Integer)}.
	 */
	@Test(expected = NullPointerException.class)
	public void testCreateTicketNull() {
		Vehicle vehicle = new Vehicle();
		Ticket createTicket = ticketService.createTicket(null, 1);
		assertTrue(createTicket.getParkingSlotNumber() == 1);
		assertTrue(createTicket.getVehicle().equals(vehicle));
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#hasTicket(java.lang.String)}.
	 */
	@Test
	public void testHasTicket() {
		Vehicle vehicle = new Vehicle();
		ticketService.createTicket(vehicle, 1);
		boolean hasTicket = ticketService.hasTicket("TKT-1");
		assertTrue(hasTicket);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#hasTicket(java.lang.String)}.
	 */
	@Test
	public void testHasTicketFalse() {
		boolean hasTicket = ticketService.hasTicket("TKT-941");
		assertTrue(!hasTicket);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#hasTicket(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testHasTicketNull() {
		ticketService.hasTicket(null);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#find(java.lang.String)}.
	 */
	@Test
	public void testFind() {
		Ticket find = ticketService.find("TKT-1");
		assertNotNull(find);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#find(java.lang.String)}.
	 */
	@Test
	public void testFindFalse() {
		Ticket find = ticketService.find("TKT-1adasda");
		assertNull(find);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#find(java.lang.String)}.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindNull() {
		ticketService.find(null);
	}

	/**
	 * Test method for {@link com.tollparking.service.TicketService#findAll()}.
	 */
	@Test
	public void testFindAll() {
		Vehicle vehicle = new Vehicle();
		ticketService.createTicket(vehicle, 1);
		Collection<Ticket> find = ticketService.findAll();
		assertNotNull(find);
		assertTrue(find.size() > 0);
	}

	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#delete(java.lang.String)}.
	 */
	@Test
	public void testDelete() {
		Vehicle vehicle = new Vehicle();
		ticketService.createTicket(vehicle, 1);
		Ticket delete = ticketService.delete("TKT-1");
		assertNotNull(delete);
	}
	
	/**
	 * Test method for
	 * {@link com.tollparking.service.TicketService#delete(java.lang.String)}.
	 */
	@Test
	public void testDeleteNull() {
		Vehicle vehicle = new Vehicle();
		ticketService.createTicket(vehicle, 1);
		Ticket delete = ticketService.delete(null);
		assertNull(delete);
	}

}
