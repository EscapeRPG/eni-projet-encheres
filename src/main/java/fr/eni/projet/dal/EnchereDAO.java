package fr.eni.projet.dal;

import fr.eni.projet.bo.Enchere;

import java.util.List;

public interface EnchereDAO {

    void creerEnchere(Enchere enchere);
    Enchere enchereMax(long idArticle);
    List<Enchere> consulterEnchere();
    Enchere afficherEnchere(long idArticle);
}
