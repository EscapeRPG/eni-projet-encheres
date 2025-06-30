package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;

public interface UtilisateurService {

    void creerUtilisateur (Utilisateur utilisateur);

    Utilisateur afficherUtilisateur(long idUtilisateur);

    void modifierUtilisatuer(long idUtilisateur);

    Utilisateur supprimerUtilisateur (long idUtlisateur);

}
