package com.tollparking.service;

import java.util.Collection;
import java.util.Date;

import com.tollparking.domain.Ticket;
import com.tollparking.domain.TicketStatus;
import com.tollparking.domain.Vehicle;
import com.tollparking.repository.TicketRepository;
import com.tollparking.util.TicketIDGenerator;

public class TicketService {

	private static TicketService ticketService;

	private TicketRepository ticketRepository;

	private TicketService() {
		ticketRepository = new TicketRepository();
	}

	public static TicketService getInstance() {
		if (ticketService == null) {
			ticketService = new TicketService();

		}
		return ticketService;

	}

	public Ticket createTicket(Vehicle vehicle, Integer parkingSlotNumber) {
		Ticket ticket = new Ticket();
		ticket.setIssuedAt(new Date());

		ticket.setVehicle(vehicle);
		// Update ticket with Parking slot number
		ticket.setParkingSlotNumber(parkingSlotNumber);
		String ticketID = TicketIDGenerator.createID();
		ticket.setTicketNumber(ticketID);
		ticket.setParkingTicketStatus(TicketStatus.ACTIVE);
		ticketRepository.getActiveTickets().put(ticketID, ticket);

		return ticket;

	}

	public boolean hasTicket(String ticketId) {
		if (ticketId == null) {
			throw new IllegalArgumentException("ERR04: Invalid Ticket ID.");
		}
		return ticketRepository.getActiveTickets().containsKey(ticketId);
	}

	public Ticket find(String ticketId) {
		if (ticketId == null) {
			throw new IllegalArgumentException("ERR04: Invalid Ticket ID.");
		}
		return ticketRepository.getActiveTickets().get(ticketId);
	}

	public Collection<Ticket> findAll() {
		return ticketRepository.getActiveTickets().values();

	}

	public Ticket delete(String ticketId) {
		if (ticketRepository.getActiveTickets().containsKey(ticketId)) {
			return ticketRepository.getActiveTickets().remove(ticketId);
		} else {
			return null;
		}
	}
}
