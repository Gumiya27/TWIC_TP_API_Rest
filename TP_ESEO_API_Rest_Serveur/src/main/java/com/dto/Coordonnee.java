package com.dto;

public class Coordonnee {
	
	private String Longitude;
	private String Latitude;
	
	public Coordonnee(String latitude2, String longitude2) {
		this.Latitude = latitude2;
		this.Longitude = longitude2;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	
}
