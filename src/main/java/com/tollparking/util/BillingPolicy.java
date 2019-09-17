package com.tollparking.util;

import com.tollparking.domain.Ticket;

public interface BillingPolicy {

	double calculateParkingFees(Ticket parkingTicket);
}
