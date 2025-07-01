package fr.eni.projet.dal;

import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;

import java.util.List;

public interface UtilisateurDAO {

    void creerCompte(Utilisateur u);
    Utilisateur connecterCompte(String pseudo,String motDePasse);
    String motDePasseOublie(String email);
    void supprimerCompte(String idUtilisateur);
    int consulterNbreCredit(String idUtilisateur);
    Utilisateur consulterCompte(String idUtilisateur);
    List<Utilisateur> afficherComptes();
    void desactiverCompte(String idUtilisateur);


}
