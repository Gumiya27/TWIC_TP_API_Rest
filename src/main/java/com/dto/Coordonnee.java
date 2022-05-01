package com.dto;

public class Coordonnee {
	private String latitude;
	private String longitude;
	
	public Coordonnee(String latitude, String longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	@Override
	public String toString() {
		return "" + latitude + " ; " + longitude;
	}
}
