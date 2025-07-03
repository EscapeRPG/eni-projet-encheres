package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;

import java.util.List;

public interface ArticleDAO {

	void ajouterArticle(Article article);

	void supprimerArticle(long idArticle);

	void updateEtatArticle(long idArticle, String etat);

	List<Article> afficherArticles();

	Article afficherArticle(long idArticle);

	boolean hasArticle(long idArticle);

	List<Article> afficherArticlesFiltres(String filtreNomArticle, int categorieFiltree, String encheresEnCours,
			String mesEncheres, String encheresRemportees, String ventesEnCours, String ventesEnAttente,
			String ventesTerminees);

}
