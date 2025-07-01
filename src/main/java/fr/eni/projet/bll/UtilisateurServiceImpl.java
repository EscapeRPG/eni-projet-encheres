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
        this.utilisateurDAO.creerCompte(utilisateur);
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
                utilisateurDAO.motDePasseOublie(utilisateur.getEmail());

            }
            else {
                System.out.println("Utilisateur inconnu");
                throw be;
            }

    }

    @Override
    public void supprimerUtilisateur(long idUtilisateur) throws BusinessException{

        BusinessException be = new BusinessException();
        boolean existUser = isExistUtilisateur(idUtilisateur, be);

        if (existUser){
            utilisateurDAO.supprimerCompte(idUtilisateur);

        }else {
            System.out.println("Erreur de suppression de l'utilisateur avec l'identifiant : Identifiant introuvable");
            throw be;
        }

    }

    private boolean isExistUtilisateur (long idUtilisateur, BusinessException be){
        boolean i = utilisateurDAO.isUtilisateurInBDD(idUtilisateur);
        if (i) {
            return true;
        }
        else {
            be.add("L'utilisateur n'existe pas");
            return false;
        }
    }

}

