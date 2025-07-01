package fr.eni.projet.bll;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.UtilisateurDAO;

import org.springframework.stereotype.Service;

@Service
public class UtilisateurServiceImpl implements UtilisateurService{

	private UtilisateurDAO utilisateurDAO;

    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public Utilisateur afficherUtilisateur(long idUtilisateur) {
        return null;
    }

    @Override
    public void modifierUtilisateur(long idUtilisateur) {

    }

    @Override
    public void supprimerUtilisateur(long idUtlisateur) {
    }
}

