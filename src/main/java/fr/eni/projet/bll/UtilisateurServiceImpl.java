package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.exception.BusinessException;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	private UtilisateurDAO utilisateurDAO;

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

        Utilisateur newUtilisateur = this.utilisateurDAO.creerComtpe(utilisateur);
    }

    @Override
    public Utilisateur afficherUtilisateur(long idUtilisateur) {

        return this.utilisateurDAO.consulterCompte(idUtilisateur);
    }

    @Override
    public void modifierUtilisateur(Utilisateur utilisateur) throws BusinessException{

        BusinessException be = new BusinessException();
        boolean existUser = isExistUtilisateur(utilisateur.getIdUtilisateur(), be);

            if (existUser) {
                utilisateur.motDePasseOublie();
            }
            else {
                System.out.println("Utilisateur inconnu");
                throw be;
            }

    }

    @Override
    public void supprimerUtilisateur(long idUtlisateur) throws BusinessException{

        BusinessException be = new BusinessException();
        boolean existUser = isExistUtilisateur(utilisateur.getIdUtilisateur(), be);

        if (existUser){
            utilisateurDAO.supprimerCompte(idUtlisateur);

        }else {
            System.out.println("Erreur de suppression de l'utilisateur avec l'identifiant : Identifiant introuvable");
            throw be;
        }

    }

    private boolean isExistUtilisateur (long idUtilisateur, BusinessException be){

        if (idUtilisateur != null) {
            return true;
        }
        else {
            be.add("L'utilisateur n'existe pas");
            return false;
        }
    }

}

