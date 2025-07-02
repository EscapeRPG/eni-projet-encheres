package fr.eni.projet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet.bll.EnchereService;
import fr.eni.projet.bo.Article;



@SessionAttributes({ "utilisateurEnSession" })
@Controller
public class EncheresController {
	
	private EnchereService enchereService;

	public EncheresController(EnchereService enchereService) {
		this.enchereService = enchereService;
	}
	
	@GetMapping("/")
	public String goToIndex(Model model) {
		List<Article> listeArticles = enchereService.consulterAllVentes();
		model.addAttribute("articles", listeArticles);
		return "index";
	}
	
	@GetMapping("/modifierProfil")
	public String goTomodifierProfil() {
		return "modifierProfil";
	}
	
	@GetMapping("/profil")
	public String goToProfil() {
		return "profil";
	}
	
	@GetMapping("/Encherir")
	public String goToEncherir() {
		return goToEncherir(); 
	}
}

