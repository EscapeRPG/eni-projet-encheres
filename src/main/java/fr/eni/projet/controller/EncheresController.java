package fr.eni.projet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet.bll.EnchereService;
import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleDAO;

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

	@GetMapping("/acquisition")
	public String goToAcquisition(@RequestParam(name = "idArticle") long idArticle, Model model) {
		Article articleGagne = enchereService.detailVente(idArticle);
		model.addAttribute("article", articleGagne);
		return "acquisition";
	}
	
	@PostMapping("/retraitEffectue")
	public String retraitEffectue() {
		
		// Methode : gerer retrait effectue
	   
	    return "redirect:/profil";
	}
	@GetMapping("/modifierProfil")
	public String goTomodifierProfil() {
		return "modifierProfil";
	}

	@GetMapping("/profil")
	public String goToProfil() {
		return "profil";
	}

	@GetMapping("/encherir")
	public String goToEncherir(@RequestParam(name = "idArticle") long idArticle, @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession, Model model) {
		enchereService.encherir(idArticle, utilisateurEnSession.getIdUtilisateur(), 20); // Le montant est indiqué ici en dur, il faudra le changer une fois la méthode adaptée créée
		
		Article articleEncheri = enchereService.detailVente(idArticle);
		model.addAttribute("article", articleEncheri);
		return "encherir";
	}
	
	
	@GetMapping("/vendreArticle")
	public String goToVendreArticle(@RequestParam(name="idArticle") long idArticle,@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurenSession, Model model ) {
		Article nouvelleArticle = enchereService.detailVente(idArticle); 
		//non foonctionnelle pour le moment 
		model.addAttribute("article", nouvelleArticle);		
		return "vendreArticle";
	}
	
}
