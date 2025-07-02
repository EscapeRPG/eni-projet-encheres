package fr.eni.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import fr.eni.projet.bll.UtilisateurService;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({ "utilisateurEnSession" })
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
	public String creerUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult) {
		try {
			this.utilisateurService.creerUtilisateur(utilisateur);
		} catch (BusinessException e) {
			e.getExceptionMessages().forEach(m -> {
				ObjectError error = new ObjectError("globalError", m);
				bindingResult.addError(error);
			});
		}
		return "index";
	}

	@GetMapping("/connexion")
	public String gotoConnexion() {
		return "/connexion";
	}
	
	@PostMapping("/annulerVente")
	public String annulerVente() {
		
		// Methode : annulerVente
	   
	    return "redirect:/profil";
	}
	

	@PostMapping("/connexion")
	public String connecterUtilisateur(@Valid @RequestParam(name = "pseudo") String pseudo,
			@RequestParam(name = "motDePasse") String motDePasse, BindingResult bindingResult,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {
		try {
			Utilisateur utilisateurInBDD = this.utilisateurService.connecterUtilisateur(pseudo, motDePasse);

			if (utilisateurInBDD != null) {
				utilisateurEnSession.setIdUtilisateur(utilisateurInBDD.getIdUtilisateur());
				utilisateurEnSession.setPseudo(utilisateurInBDD.getPseudo());
				utilisateurEnSession.setNom(utilisateurInBDD.getNom());
				utilisateurEnSession.setPrenom(utilisateurInBDD.getPrenom());
				utilisateurEnSession.setEmail(utilisateurInBDD.getEmail());
				utilisateurEnSession.setTelephone(utilisateurInBDD.getTelephone());
				utilisateurEnSession.setRue(utilisateurInBDD.getRue());
				utilisateurEnSession.setCodePostal(utilisateurInBDD.getCodePostal());
				utilisateurEnSession.setVille(utilisateurInBDD.getVille());
				utilisateurEnSession.setMotDePasse(utilisateurInBDD.getMotDePasse());
				utilisateurEnSession.setCredit(utilisateurInBDD.getCredit());
				utilisateurEnSession.setAdministrateur(utilisateurInBDD.isAdministrateur());
			} else {
				utilisateurEnSession.setIdUtilisateur(0);
				utilisateurEnSession.setPseudo(null);
				utilisateurEnSession.setNom(null);
				utilisateurEnSession.setPrenom(null);
				utilisateurEnSession.setEmail(null);
				utilisateurEnSession.setTelephone(null);
				utilisateurEnSession.setRue(null);
				utilisateurEnSession.setCodePostal(null);
				utilisateurEnSession.setVille(null);
				utilisateurEnSession.setMotDePasse(null);
				utilisateurEnSession.setCredit(0);
				utilisateurEnSession.setAdministrateur(false);
			}
		} catch (BusinessException e) {
			e.getExceptionMessages().forEach(m -> {
				ObjectError error = new ObjectError("globalError", m);
				bindingResult.addError(error);
			});
		}

		return "index";
	}

	@ModelAttribute("utilisateurEnSession")
	public Utilisateur addUtilisateurEnSession() {
		return new Utilisateur();
	}

}
