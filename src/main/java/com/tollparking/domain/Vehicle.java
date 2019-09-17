package com.tollparking.domain;

public class Vehicle {
	private String licenseNumber;
	private VehicleType type;

	public Vehicle() {
		super();
	}

	public Vehicle(String licenseNumber, VehicleType type) {
		super();
		this.licenseNumber = licenseNumber;
		this.type = type;
	}

	public String getLicenseNumber() {
		return licenseNumber;
	}

	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}

	public VehicleType getType() {
		return type;
	}

}
