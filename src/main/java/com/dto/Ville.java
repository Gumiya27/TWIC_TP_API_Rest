package com.dto;

public class Ville {
	
	private String codeInsee;
	private String nom;
	private String codePostal;
	private String libelle;
	private String ligne5;
	private Coordonnee coord;
	
	
	public Ville(String code, String nom, String codePostal, String libelle, String ligne5, String latitude, String longitude) {
		this.codeInsee = code;
		this.nom = nom;
		this.codePostal = codePostal;
		this.libelle = libelle;
		this.ligne5 = ligne5;
		this.coord = new Coordonnee(latitude, longitude);
	}
	
	public String getCodeInsee() {
		return codeInsee;
	}
	public String getNom() {
		return nom;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public String getLibelle() {
		return libelle;
	}
	public String getLigne5() {
		return ligne5;
	}

	public Coordonnee getCoord() {
		return coord;
	}
	
	@Override
	public String toString() {
		return codeInsee + ";" + nom + ";" + codePostal + ";" + libelle + ";" + ligne5 + ";" + coord.getLatitude() + ";" + coord.getLongitude();
	}
	
}
