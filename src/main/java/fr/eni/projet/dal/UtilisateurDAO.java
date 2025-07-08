package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;

import java.util.List;

public interface UtilisateurDAO {

    void creerCompte(Utilisateur u);
    void supprimerCompte(long idUtilisateur);
    void desactiverCompte(long idUtilisateur);
    void updateCompte(Utilisateur u);
    
    Utilisateur connecterCompte(String pseudo, String motDePasse);
    Utilisateur consulterCompte(long idUtilisateur);
    Utilisateur consulterCompte(String pseudo);
    
    List<Utilisateur> afficherComptes();
    
    String motDePasseOublie(String email);
    
    int consulterNbreCredit(long idUtilisateur);
    
    boolean isUtilisateurInBDD(long idUtilisateur);
    boolean isUtilisateurInBDD(String pseudo);
    
    boolean emailExist(String email);
	boolean isEmailInBDD(String email);
	
	void crediter(long idUtiisateur);
    void debiter(long idUtilisateur, int montant);

     
 

}
