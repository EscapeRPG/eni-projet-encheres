package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;

import java.util.List;

public interface ArticleDAO {

    void ajouterArticle(Article article);
    void supprimerArticle(long idArticle);
    List<Article> afficherArticle();
    Article afficherArticle(long idArticle);

}
