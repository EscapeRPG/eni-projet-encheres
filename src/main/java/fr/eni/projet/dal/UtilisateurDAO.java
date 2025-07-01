package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void creerCompte(Utilisateur u);
    Utilisateur connecterCompte(String pseudo, String motDePasse);
    String motDePasseOublie(String email);
    void supprimerCompte(long idUtilisateur);
    int consulterNbreCredit(long idUtilisateur);
    Utilisateur consulterCompte(long idUtilisateur);
    Utilisateur consulterCompte(String pseudo);
    List<Utilisateur> afficherComptes();
    void desactiverCompte(long idUtilisateur);
    boolean isUtilisateurInBDD(long idUtilisateur);


}
