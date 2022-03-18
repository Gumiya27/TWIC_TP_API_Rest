package com.controller;

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
	@RequestMapping(value="/ville", method=RequestMethod.GET)
	
	public @ResponseBody Iterable<Ville> get(@RequestParam(required  = false, value="codePostal") String codePostal) {
		
		System.out.println("get");
		// TODO : mon code vers la BDD
		List<Ville> liste = VilleDAO.recupererVilles();
		return liste;
	}
	
	// TODO : 
	// fonction pour enregistrer un element dans la BDD
	@RequestMapping(value="/ville", method=RequestMethod.POST)
	@ResponseBody
	public String insert(@RequestBody Ville ville, Model model) {
		
		return "todo";
	}
}