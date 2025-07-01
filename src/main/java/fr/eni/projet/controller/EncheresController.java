package fr.eni.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import fr.eni.projet.bll.EnchereService;



@Controller
public class EncheresController {
	
	private EnchereService enchereService;

	public EncheresController(EnchereService enchereService) {
		this.enchereService = enchereService;
	}
	
	@GetMapping("/")
	public String goToIndex() {
		return "index";
	}

	@GetMapping("/inscription")
	public String inscription() {
		return "inscription";
	}
	
	@GetMapping("/connexion")
	public String gotoConnexion() {
		return "/connexion";
	}
	
	@GetMapping("/modifierProfil")
	public String goTomodifierProfil() {
		return "modifierProfil";
	}
	
	@GetMapping("/profil")
	public String goToProfil() {
		return "profil";
	}
	
}

