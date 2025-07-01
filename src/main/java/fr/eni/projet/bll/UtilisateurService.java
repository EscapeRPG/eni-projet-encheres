package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;


public interface UtilisateurService {


    /**
     * Crée un nouvel utilisateur dans le système.
     * @param utilisateur l'objet {@link Utilisateur} contenant les informations du nouvel utilisateur à enregistrer.
     */
    void creerUtilisateur (Utilisateur utilisateur);

    /**
     * Récupère les informations d'un utilisateur à partir de son identifiant.
     * @param idUtilisateur l'identifiant unique de l'utilisateur à afficher.
     * @return l'objet {@link Utilisateur} correspondant à l'identifiant fourni.
     */
    Utilisateur afficherUtilisateur(long idUtilisateur);

    /**
     * Met à jour les informations d'un utilisateur existant.
     * @param idUtilisateur l'identifiant unique de l'utilisateur à modifier.
     */
    void modifierUtilisateur(long idUtilisateur);

    /**
     * Supprime un utilisateur du système.
     * @param idUtilisateur l'identifiant unique de l'utilisateur à supprimer.
     */
    void supprimerUtilisateur (long idUtilisateur);

}
