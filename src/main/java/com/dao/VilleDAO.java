package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAO {

	Connection connexion;

	public static Connection getConnection() throws SQLException {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twic", "root", "");
		return c;
	}

	public static List<Ville> recupererVilles() {
		List<Ville> villes = new ArrayList<Ville>();
		Statement statement = null;
		ResultSet resultat = null;
		Connection connexion = null;
		try {
			connexion = getConnection();
			statement = connexion.createStatement();

			// Exécution de la requête
			resultat = statement.executeQuery("SELECT * FROM ville_france order by Nom_commune ASC;");

			// Récupération des données
			while (resultat.next()) {
				String code = resultat.getString("Code_commune_INSEE");
				String nom = resultat.getString("Nom_commune");
				String codePostal = resultat.getString("Code_Postal");
				String libelle = resultat.getString("Libelle_acheminement");
				String ligne5 = resultat.getString("Ligne_5");
				String latitude = resultat.getString("Latitude");
				String longitude = resultat.getString("Longitude");
				villes.add(new Ville(code, nom, codePostal, libelle, ligne5, latitude, longitude));
			}
		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (statement != null)
					statement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return villes;
	}

	public static List<Ville> recupererVilleCode(String codePostal) {
		List<Ville> villes = new ArrayList<Ville>();
		PreparedStatement preparedStatement = null;
		ResultSet resultat = null;
		Connection connexion = null;
		try {
			connexion = getConnection();
			preparedStatement = connexion.prepareStatement("SELECT * FROM ville_france WHERE Code_postal = ? order by Nom_commune ASC;");
			preparedStatement.setString(1, codePostal);
			
			// Exécution de la requête
			resultat = preparedStatement.executeQuery();

			// Récupération des données
			while (resultat.next()) {
				String code = resultat.getString("Code_commune_INSEE");
				String nom = resultat.getString("Nom_commune");
				String libelle = resultat.getString("Libelle_acheminement");
				String ligne5 = resultat.getString("Ligne_5");
				String latitude = resultat.getString("Latitude");
				String longitude = resultat.getString("Longitude");
				villes.add(new Ville(code, nom, codePostal, libelle, ligne5, latitude, longitude));
			}
		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (resultat != null)
					resultat.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return villes;
	}

	public static String AjouterVille(Ville ville) {
		Connection connexion = null;
		String etatFinal = "Echec";
		try {
			connexion = getConnection();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("INSERT INTO ville_france VALUES (?, ?, ?, ?, ?, ?, ?);");
			preparedStatement.setString(1, ville.getCodeInsee());
			preparedStatement.setString(2, ville.getNom());
			preparedStatement.setString(3, ville.getCodePostal());
			preparedStatement.setString(4, ville.getLibelle());
			preparedStatement.setString(5, ville.getLigne5());
			preparedStatement.setString(6, ville.getCoord().getLatitude());
			preparedStatement.setString(7, ville.getCoord().getLatitude());
			// Permet d'ajouter la date actuelle
			preparedStatement.executeUpdate();
			etatFinal = "Ville ajoutée";

		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return etatFinal;
	}

	public static String ModifierVille(Ville ville) {
		Connection connexion = null;
		String etatFinal = "Echec de modification";
		try {
			connexion = getConnection();
			PreparedStatement preparedStatement = connexion
					.prepareStatement("UPDATE ville_france SET Nom_commune = ?, Code_postal = ?,"
							+ " Libelle_acheminement = ?, Ligne_5 = ?, Latitude = ?, Longitude = ? "
							+ " WHERE ville_france.Code_commune_INSEE = ? ;");

			preparedStatement.setString(1, ville.getNom());
			preparedStatement.setString(2, ville.getCodePostal());
			preparedStatement.setString(3, ville.getLibelle());
			preparedStatement.setString(4, ville.getLigne5());
			preparedStatement.setString(5, ville.getCoord().getLatitude());
			preparedStatement.setString(6, ville.getCoord().getLatitude());
			preparedStatement.setString(7, ville.getCodeInsee());
			// Permet d'ajouter la date actuelle
			preparedStatement.executeUpdate();
			etatFinal = "Ville Modifiée";

		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return etatFinal;
	}

	public static boolean villePresente(Ville ville) {
		boolean resultat = false;
		List<Ville> villes = recupererVilles();
		for (Ville v : villes) {
			if (v.getCodeInsee().equals(ville.getCodeInsee())) {
				resultat = true;
			}
		}
		return resultat;
	}

	public static String SupprimerVilleAvecCode(String code) {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;
		String etatFinal = "Echec";
		System.out.println(code);
		try {
			connexion = getConnection();
			preparedStatement = connexion
					.prepareStatement("DELETE FROM ville_france WHERE Code_commune_INSEE = ?;");
			preparedStatement.setInt(1, Integer.parseInt(code));
			// Permet d'ajouter la date actuelle
			preparedStatement.executeUpdate();
			etatFinal = "Ville Supprimée";

		} catch (SQLException e) {
		} finally {
			// Fermeture de la connexion
			try {
				if (preparedStatement != null)
					preparedStatement.close();
				if (connexion != null)
					connexion.close();
			} catch (SQLException ignore) {
			}
		}

		return etatFinal;
	}
}
