package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;

import java.util.List;

public interface CategorieDAO {

    List<Categorie> listerCategorie();
    Article afficherCategorieArticle(long idArticle);
    void ajouterCategorie(Categorie categorie);
    void SupprimerCategorie(long idCategorie);

}
