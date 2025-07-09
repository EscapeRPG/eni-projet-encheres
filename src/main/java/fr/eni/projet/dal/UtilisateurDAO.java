package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;

import java.util.List;

public interface UtilisateurDAO {

	int consulterNbreCredit(long idUtilisateur);
	
	
	
    void creerCompte(Utilisateur u);
    
    void supprimerCompte(long idUtilisateur);
    
    void desactiverCompte(long idUtilisateur);
    
    void updateCompte(Utilisateur u);
    
    void crediter(long idUtiisateur);
    
	void crediterVendeur(long idUtilisateur, long idArticle);
	
    void debiter(long idUtilisateur, int montant);
    
    
   
    List<Utilisateur> afficherComptes();
    
    
    
    String motDePasseOublie(String email);
    
    
    
    Utilisateur connecterCompte(String pseudo, String motDePasse);
    
    Utilisateur consulterCompte(long idUtilisateur);
    
    Utilisateur consulterCompte(String pseudo);
    
    
    
    boolean isUtilisateurInBDD(long idUtilisateur);
    
    boolean isUtilisateurInBDD(String pseudo); 
    
    boolean emailExist(String email);
    
	boolean isEmailInBDD(String email);
	
<<<<<<< HEAD
	void crediter(long idUtiisateur, int montant); 
	void crediterVendeur(long idUtilisateur, long idArticle); 
    void debiter(long idUtilisateur, int montant);
=======
	
>>>>>>> b636c71cde77b4142e20fb2819c628d43523b4a0

     
 

}
