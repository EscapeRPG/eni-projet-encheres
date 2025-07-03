package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {

	private UtilisateurDAO utilisateurDAO;

	public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
		this.utilisateurDAO = utilisateurDAO;
	}

	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		this.utilisateurDAO.creerCompte(utilisateur);
	}

	@Override
	public Utilisateur afficherProfil(long idUtilisateur) {
		return this.utilisateurDAO.consulterCompte(idUtilisateur);
	}

	@Override
	public Utilisateur afficherProfil(String pseudo) {
		return this.utilisateurDAO.consulterCompte(pseudo);
	}

	@Override
	public Utilisateur connecterUtilisateur(String pseudo, String motDePasse) throws BusinessException {

		BusinessException be = new BusinessException();
		boolean existUser = isExistUtilisateur(pseudo, be);

		if (existUser) {
			if (this.utilisateurDAO.connecterCompte(pseudo, motDePasse) != null) {
				return this.utilisateurDAO.connecterCompte(pseudo, motDePasse);
			} else {
				be.add("Le mot de passe ou user ne correspond pas");
				throw be;
			}
		} else {
			be.add("l'utilisateur n'existe pas");
			throw be;
		}

	}
	
	@Override
	public void modifierProfil(Utilisateur utilisateur) throws BusinessException {

		BusinessException be = new BusinessException();
		
		utilisateurDAO.updateCompte(utilisateur);	

	}

	@Override
	public void supprimerUtilisateur(long idUtilisateur) throws BusinessException {

		BusinessException be = new BusinessException();
		boolean existUser = isExistUtilisateur(idUtilisateur, be);

		if (existUser) {
			utilisateurDAO.supprimerCompte(idUtilisateur);

		} else {
			System.out.println("Erreur de suppression de l'utilisateur avec l'identifiant : Identifiant introuvable");
			throw be;
		}

	}

	private boolean isExistUtilisateur(long idUtilisateur, BusinessException be) {
		boolean i = utilisateurDAO.isUtilisateurInBDD(idUtilisateur);
		if (i) {
			return true;
		} else {
			be.add("L'utilisateur n'existe pas");
			return false;
		}

	}

	private boolean isExistUtilisateur(String pseudo, BusinessException be) {
		boolean i = utilisateurDAO.isUtilisateurInBDD(pseudo);
		if (i) {
			return true;
		} else {
			be.add("L'utilisateur n'existe pas");
			return false;
		}

	}

	

}
