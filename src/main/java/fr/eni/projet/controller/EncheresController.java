package fr.eni.projet.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.eni.projet.bll.EnchereService;
import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;

@SessionAttributes({ "utilisateurEnSession", "categoriesEnSession" })
@Controller
public class EncheresController {

	private EnchereService enchereService;

	public EncheresController(EnchereService enchereService) {
		this.enchereService = enchereService;
	}

	@GetMapping({"/", "/index"})
	public String goToIndex(Model model) {
		List<Article> listeArticles = enchereService.consulterAllVentes();
		List<Categorie> listeCategories = enchereService.consulterAllCategories();
		model.addAttribute("categoriesEnSession", listeCategories);
		model.addAttribute("articles", listeArticles);
		return "index";
	}

	@GetMapping("/acquisition")
	public String goToAcquisition(@RequestParam(name = "idArticle") long idArticle, Model model) {
		Article articleGagne = enchereService.detailVente(idArticle);
		model.addAttribute("article", articleGagne);
		return "acquisition";
	}
	
	@GetMapping("/detail-vente")
	public String goToDetailVente(@RequestParam(name = "idArticle") long idArticle, Model model) {
		Article article = this.enchereService.detailVente(idArticle);
		model.addAttribute("article", article);
		
		return "detail-vente";
	}
	
	@PostMapping("/retraitEffectue")
	public String retraitEffectue(@RequestParam(name = "idArticle") long idArticle) {
		
		try {
			this.enchereService.clotureArticle(idArticle);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		 
	    return "redirect:/profil";
	}

	@PostMapping("/encherir")
	public String goToEncherir(@RequestParam(name = "idArticle") long idArticle,
							   @RequestParam(name = "montant") int montant ,
							   @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession, Model model) {
		
		
		enchereService.encherir(idArticle, utilisateurEnSession.getIdUtilisateur(), montant);
		
		Article articleEncheri = enchereService.detailVente(idArticle);
		model.addAttribute("article", articleEncheri);
		
		return "detail-vente";
	}
	
	
	@GetMapping("/vendreArticle")
	public String goToVendreArticle(@RequestParam(name="idArticle") long idArticle,@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurenSession, Model model ) {
		Article newArticle = enchereService.detailVente(idArticle); 
		//non foonctionnelle pour le moment 
		model.addAttribute("article", newArticle);		
		return "vendreArticle";
	}
	
	@PostMapping("/annulerVente")
	public String goToAnnulerArticle (@RequestParam(name="idArticle") long idArticle, @ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession, Model model) {
		
		this.enchereService.supprimerVente(idArticle);
		
		List<Article> listeArticles = enchereService.consulterAllVentes();
		List<Categorie> listeCategories = enchereService.consulterAllCategories();
		model.addAttribute("categoriesEnSession", listeCategories);
		model.addAttribute("articles", listeArticles);
		
		return "redirect:/";
	}
	
	@ModelAttribute("categoriesEnSession")
	public Categorie addCategorieEnSession() {
		return new Categorie();
	}
	
}
