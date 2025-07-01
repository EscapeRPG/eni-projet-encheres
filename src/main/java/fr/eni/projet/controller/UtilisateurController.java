package fr.eni.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.projet.bll.UtilisateurService;
import fr.eni.projet.bo.Utilisateur;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class UtilisateurController {
	
	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService) {
		this.utilisateurService = utilisateurService;
	}

	@GetMapping("/inscription")
	public String inscription(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "inscription";
	}
	
	@PostMapping("/inscription")
	public String creerUtilisateur(@ModelAttribute Utilisateur utilisateur, Model model) {
		this.utilisateurService.creerUtilisateur(utilisateur);
		return "index";
	}
	
	@GetMapping("/connexion")
	public String gotoConnexion() {
		return "/connexion";
	}
	
	@PostMapping("/connexion")
	public String connecterUtilisateur(@ModelAttribute Utilisateur utilisateur, Model model) {
		this.utilisateurService.
		return "index";
	}
	

}
