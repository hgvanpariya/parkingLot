package com.tollparking.util;

import org.joda.time.DateTime;
import org.joda.time.Hours;

import com.tollparking.domain.Ticket;

public class DefaultBillingPolicy implements BillingPolicy {

	private int fixedHour;
	private double fixedHourRate;
	private double perHourRate;

	public DefaultBillingPolicy() {
	}

	public DefaultBillingPolicy(Builder defaultBillingPolicyBuilder) {
		this.fixedHour = defaultBillingPolicyBuilder.fixedHour;
		this.fixedHourRate = defaultBillingPolicyBuilder.fixedHourRate;
		this.perHourRate = defaultBillingPolicyBuilder.perHourRate;
	}

	public double calculateParkingFees(Ticket parkingTicket) {
		DateTime issuedAt = new DateTime(parkingTicket.getIssuedAt());
		DateTime payedAt = new DateTime(parkingTicket.getPayedAt());
		int totalTimeSpent = Hours.hoursBetween(issuedAt, payedAt).getHours();
		double totalCharges = 0;
		if (fixedHour != 0) {
			totalTimeSpent = totalTimeSpent - fixedHour;
			totalCharges += fixedHourRate;
		}
		if (totalTimeSpent > 0) {
			totalCharges += (totalTimeSpent * perHourRate);
		}
		return totalCharges;
	}

	public static class Builder {
		private int fixedHour;
		private double fixedHourRate;
		private double perHourRate;

		public Builder fixedHour(int fixedHour, double fixedHourRate) {
			this.fixedHour = fixedHour;
			this.fixedHourRate = fixedHourRate;
			return this;
		}

		public Builder perHour(double perHourRate) {
			this.perHourRate = perHourRate;
			return this;
		}

		public DefaultBillingPolicy build() {
			DefaultBillingPolicy defaultBillingPolicy = new DefaultBillingPolicy(this);
			return defaultBillingPolicy;
		}
	}

}
