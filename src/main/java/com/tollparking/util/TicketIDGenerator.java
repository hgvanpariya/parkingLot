package com.tollparking.util;

import java.util.concurrent.atomic.AtomicLong;

public class TicketIDGenerator {

	private static AtomicLong idCounter = new AtomicLong();

	public static String createID() {
		return String.valueOf("TKT-"+idCounter.getAndIncrement());
	}
}
