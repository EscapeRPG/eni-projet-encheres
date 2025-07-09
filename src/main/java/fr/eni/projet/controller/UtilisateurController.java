package fr.eni.projet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import fr.eni.projet.bll.UtilisateurService;
import fr.eni.projet.bll.UtilisateurServiceImpl;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.dal.UtilisateurDAOImpl;
import fr.eni.projet.exception.BusinessException;
import jakarta.validation.Valid;


@SessionAttributes({ "utilisateurEnSession" })
@Controller
public class UtilisateurController {

	private final UtilisateurServiceImpl utilisateurServiceImpl;

	private UtilisateurService utilisateurService;

	public UtilisateurController(UtilisateurService utilisateurService, UtilisateurServiceImpl utilisateurServiceImpl) {
		this.utilisateurService = utilisateurService;
		this.utilisateurServiceImpl = utilisateurServiceImpl;
	}

	@GetMapping("/inscription")
	public String inscription(Model model) {
		Utilisateur utilisateur = new Utilisateur();
		model.addAttribute("utilisateur", utilisateur);
		return "inscription";
	}

	@PostMapping("/inscription")
	public String creerUtilisateur(@Valid @ModelAttribute Utilisateur utilisateur, BindingResult bindingResult,
			@RequestParam("confirmationMotDePasse") String confirmationMotDePasse, Model model) {

		// Vérifie si les mots de passe correspondent
		if (!utilisateur.getMotDePasse().equals(confirmationMotDePasse)) {
			bindingResult.rejectValue("motDePasse", "error.motDePasse", "Les mots de passe ne correspondent pas.");
		}

		// Vérifie si le pseudo existe déjà en base
		if (utilisateurService.pseudoExiste(utilisateur.getPseudo())) {
			bindingResult.rejectValue("pseudo", "error.pseudo", "Ce pseudo est déjà utilisé.");
		}

		// Vérifie si l'email existe déjà en base
		if (utilisateurService.emailExiste(utilisateur.getEmail())) {
			bindingResult.rejectValue("email", "error.email", "Cet email est déjà utilisé.");
		}

		// En cas d'erreur, on renvoie au formulaire
		if (bindingResult.hasErrors()) {
			return "inscription";
		}

		utilisateur.setAdministrateur(false); // sécurité

		try {
			utilisateurService.creerUtilisateur(utilisateur);
		} catch (BusinessException e) {
			e.getExceptionMessages().forEach(message -> {
				bindingResult.addError(new ObjectError("globalError", message));
			});
			return "inscription";
		}

		return "redirect:/connexion";
	}

	@GetMapping("/modifierProfil")
	public String goTomodifierProfil() {
		return "modifierProfil";
	}

	@GetMapping("/profil")
	public String goToProfil(@RequestParam(name = "pseudo") String pseudo, Model model) {
		try {
			Utilisateur utilisateur = utilisateurService.afficherProfil(pseudo);
			model.addAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
//			e.printStackTrace();
		}
		return "profil";
	}

	@PostMapping("/profil")
	public String changeProfil(@ModelAttribute("newUser") Utilisateur utilisateur,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {
		utilisateur.setIdUtilisateur(utilisateurEnSession.getIdUtilisateur());
		System.out.println(utilisateur);
		try {
			utilisateurService.modifierProfil(utilisateur);
		} catch (BusinessException e) {
			throw new RuntimeException(e);
		}
		return "redirect:/";
	}

	@GetMapping("/connexion")
	public String gotoConnexion(@RequestParam(name = "error", required = false) Integer error, Model model) {
		model.addAttribute("error", error);
		return "connexion";
	}

	@PostMapping("/connexion")
	public String connecterUtilisateur(@RequestParam(name = "pseudo") String pseudo,
			@RequestParam(name = "motDePasse") String motDePasse,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession, BindingResult bindingResult) {
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
			}
			return "redirect:/";

		} catch (BusinessException e) {
			e.getExceptionMessages().forEach(m -> {
				ObjectError error = new ObjectError("errorLogin", m);
				bindingResult.addError(error);

			});
			return "redirect:/connexion?error=1";
		}

	}

	@GetMapping("/deconnexion")
	public String deconnexion(@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {

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

		return "redirect:/";

	}

	@GetMapping("/supprimerProfil")
	public String supprimerProfil(@RequestParam(name = "pseudo") String pseudo,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {

		try {
			utilisateurService.supprimerUtilisateur(utilisateurService.afficherProfil(pseudo).getIdUtilisateur());
		} catch (BusinessException e) {
			throw new RuntimeException(e);
		}
		if (utilisateurEnSession.isAdministrateur()) {
			return "redirect:/";
		}
		return "redirect:/deconnexion";
	}

	@GetMapping("/desactiverProfil")
	public String desactiverProfil(@RequestParam(name = "pseudo") String pseudo,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {

		try {
			utilisateurService.desactiverUtilisateur(utilisateurService.afficherProfil(pseudo).getIdUtilisateur());
		} catch (BusinessException e) {
			throw new RuntimeException(e);
		}

		return "redirect:/";

	}

	@ModelAttribute("utilisateurEnSession")
	public Utilisateur addUtilisateurEnSession() {
		return new Utilisateur();
	}

	@GetMapping("/achatCredit")
	public String goToAchatCredit(@RequestParam(name = "pseudo") String pseudo,
			@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {

		return "achat-credit";
	}
	
	@PostMapping("/crediter")
	public String crediter(@RequestParam(name = "montant") int montant,@ModelAttribute("utilisateurEnSession") Utilisateur utilisateurEnSession) {
		this.utilisateurService.achatCredit(utilisateurEnSession.getIdUtilisateur(), montant);
		return "redirect:/profil?pseudo=" + utilisateurEnSession.getPseudo();
	}
	
}
