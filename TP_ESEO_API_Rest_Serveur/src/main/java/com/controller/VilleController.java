package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dao.VilleDAO;
import com.dto.Ville;

@RestController
public class VilleController {

	// fonction pour récupérer le contenu de la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.GET)

	public @ResponseBody Iterable<Ville> get(@RequestParam(required = false, value = "codePostal") String codePostal) {
		System.out.println("get");
		// TODO : mon code vers la BDD
		List<Ville> liste = new ArrayList<Ville>();
		if (codePostal == null) {
			liste = VilleDAO.recupererVilles();
		} else {
			if (codePostal.equals("")) {
				liste = VilleDAO.recupererVilles();
			} else {
				liste = VilleDAO.recupererVilleCode(codePostal);
			}

		}

		return liste;
	}

	// TODO :
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody Ville ville, Model model) {
		String resultat = "";
		if (ville != null) {
			resultat = VilleDAO.AjouterVille(ville);
		}

		return resultat;
	}

	// TODO :
	// fonction pour modifier un element dans la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.PUT)
	@ResponseBody
	public String update(@RequestBody Ville ville, Model model) {
		String resultat = "";
		if (ville != null) {
			if(VilleDAO.villePresente(ville)) {
				resultat = VilleDAO.ModifierVille(ville);
			}else {
				resultat = VilleDAO.AjouterVille(ville);
			}
		}

		return resultat;
	}

	// TODO :
	// fonction pour supprimer un element de la BDD
	@RequestMapping(value = "/ville", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(@RequestParam(required = true, value = "code") String code) {
		String resultat = "";
		if (code != null) {
			resultat = VilleDAO.SupprimerVilleAvecCode(code);
		}

		return resultat;
	}

}