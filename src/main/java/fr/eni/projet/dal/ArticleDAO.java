package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;

import java.util.List;

public interface ArticleDAO {

	long ajouterArticle(Article article, String image);

	void supprimerArticle(long idArticle);

	void updateEtatArticle(long idArticle, String etat);

	List<Article> afficherArticles();

	Article afficherArticle(long idArticle);

	boolean hasArticle(long idArticle);

	List<Article> afficherArticlesFiltres(String filtreNomArticle, int categorieFiltree, String encheresEnCours,
			int mesEncheres, int encheresRemportees, int ventesEnCours, int ventesEnAttente, int ventesTerminees);

} 
 