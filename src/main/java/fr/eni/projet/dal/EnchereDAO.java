package fr.eni.projet.dal;

import fr.eni.projet.bo.Enchere;

import java.util.List;

public interface EnchereDAO {

    void creerEnchere(Enchere enchere);
   
    List<Enchere> consulterEnchere();
    
    List<Enchere> afficherEncheres(long idArticle);

    Enchere enchereMax(long idArticle);
}
