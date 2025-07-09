package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;

public interface UtilisateurService {

	/**
	 * Crée un nouvel utilisateur dans le système.
	 * 
	 * @param utilisateur l'objet {@link Utilisateur} contenant les informations du
	 *                    nouvel utilisateur à enregistrer.
	 */
	void creerUtilisateur(Utilisateur utilisateur) throws BusinessException;

	/**
	 * Met à jour les informations d'un utilisateur existant.
	 * 
	 * @param utilisateur l'identifiant unique de l'utilisateur à modifier.
	 */
	void modifierProfil(Utilisateur utilisateur) throws BusinessException;

	/**
	 * Supprime un utilisateur du système.
	 * 
	 * @param idUtilisateur l'identifiant unique de l'utilisateur à supprimer.
	 */
	void supprimerUtilisateur(long idUtilisateur) throws BusinessException;

	void desactiverUtilisateur(long idUtilisateur) throws BusinessException;

	/**
	 * Récupère les informations d'un utilisateur à partir de son identifiant.
	 * 
	 * @param idUtilisateur l'identifiant unique de l'utilisateur à afficher.
	 * @return l'objet {@link Utilisateur} correspondant à l'identifiant fourni.
	 */
	Utilisateur afficherProfil(long idUtilisateur) throws BusinessException;

	/**
	 * Récupère les informations d'un utilisateur à partir de son identifiant.
	 * 
	 * @param le pseudo de l'utilisateur à afficher.
	 * @return l'objet {@link Utilisateur} correspondant à l'identifiant fourni.
	 */
	Utilisateur afficherProfil(String pseudo) throws BusinessException;

	Utilisateur connecterUtilisateur(String pseudo, String motDePasse) throws BusinessException;

	boolean pseudoExiste(String pseudo);

	boolean emailExiste(String email);
<<<<<<< HEAD


    void desactiverUtilisateur(long idUtilisateur) throws BusinessException;

	void achatCredit(long idUtilisateur) ;
=======
>>>>>>> c43ffe495b03ac6af65e3223d384d9a43be39cf7
}
