package com.tollparking.repository;

import java.util.HashMap;
import java.util.Map;

import com.tollparking.domain.Ticket;

/**
 * This class will store and retrieve the details related to Ticket.
 * 
 * @author hvanpariya
 *
 */
public class TicketRepository {

	Map<String, Ticket> activeTickets;

	public TicketRepository() {
		activeTickets = new HashMap<String, Ticket>();
	}

	public Map<String, Ticket> getActiveTickets() {
		return activeTickets;
	}

}
