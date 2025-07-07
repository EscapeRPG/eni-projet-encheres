package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.EnchereDAO;
import fr.eni.projet.dal.RetraitDAO;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.exception.BusinessException;

import org.springframework.stereotype.Service;import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService {
	
	
	private ArticleDAO articleDAO;
	private EnchereDAO enchereDAO;
	private CategorieDAO categorieDAO;
	private RetraitDAO retraitDAO;
	private UtilisateurDAO utilisateurDAO;

	public EnchereServiceImpl(ArticleDAO articleDAO, EnchereDAO enchereDAO, CategorieDAO categorieDAO,
			RetraitDAO retraitDAO, UtilisateurDAO utilisateurDAO) {
		this.articleDAO = articleDAO;
		this.enchereDAO = enchereDAO;
		this.categorieDAO = categorieDAO;
		this.retraitDAO = retraitDAO;
		this.utilisateurDAO = utilisateurDAO;
		
	}

	@Override
	public List<Article> consulterAllVentes() {
		List<Article> articles = this.articleDAO.afficherArticles();

		for (Article article : articles) {
			article.setUtilisateur(utilisateurDAO.consulterCompte(article.getUtilisateur().getIdUtilisateur()));
		}

		return articles;
	}

	@Override
	public void encherir(long idArticle, long idUtilisateur, int montant) {

		if (montant > enchereDAO.enchereMax(idArticle)) {
			Enchere newEnchere = new Enchere(utilisateurDAO.consulterCompte(idUtilisateur), articleDAO.afficherArticle(idArticle), LocalDateTime.now(), montant);
			this.enchereDAO.creerEnchere(newEnchere);
		} else {
			System.out.println("Saisir une enchère plus élevée");
		}
	}

	@Override
	public Article detailVente(long idArticle) {
		Article article = this.articleDAO.afficherArticle(idArticle);
		article.setCategorie(categorieDAO.afficherCategorieArticle(idArticle));
		article.setUtilisateur(utilisateurDAO.consulterCompte(article.getUtilisateur().getIdUtilisateur()));
		article.setRetrait(retraitDAO.afficherRetrait(idArticle));

		return article;
	}

	@Override
	public void remporterVente(long idArticle) {		
	    Article article = articleDAO.afficherArticle(idArticle);
	    LocalDateTime now = LocalDateTime.now();
	
	    if (now.isAfter(article.getDateFinEncheres())) {
	    	article.setEtatVente("ET");
            articleDAO.updateEtatArticle(idArticle, "ET");           
	    }	    	   
	}   

	 
	
	@Override
	public void clotureArticle(long idArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean existArticle = isExistArticle(idArticle, be);

		if (existArticle) {
			articleDAO.updateEtatArticle(idArticle, "RE");

		} else {
			throw be;
		}
	}
	
	@Override
	public void supprimerVente(long idArticle) {
		// TODO Auto-generated method stub
		this.articleDAO.supprimerArticle(idArticle);
	}


	private boolean isExistArticle(long idArticle, BusinessException be) {
		boolean i = articleDAO.hasArticle(idArticle);
		if (i) {
			return true;
		} else {
			be.add("L'article n'existe pas");
			return false;
		}

	}

	@Override
	public List<Categorie> consulterAllCategories() {
		return this.categorieDAO.listerCategorie();
	}

	@Override
	public List<Article> filtrerRecherche(String filtreNomArticle, int categorieFiltree, String encheresEnCours,
			int mesEncheres, int encheresRemportees, int ventesEnCours, int ventesEnAttente, int ventesTerminees) {
		List<Article> articles = this.articleDAO.afficherArticlesFiltres(filtreNomArticle, categorieFiltree, encheresEnCours, mesEncheres,
				encheresRemportees, ventesEnCours, ventesEnAttente, ventesTerminees);

		for (Article article : articles) {
			article.setUtilisateur(utilisateurDAO.consulterCompte(article.getUtilisateur().getIdUtilisateur()));
		}
		return articles;
	}

	@Override
	public int consulterEnchereMax(long idArticle) {
		return this.enchereDAO.enchereMax(idArticle);
	}

	@Override
	public void CreationArticle(Article article) throws BusinessException {
		articleDAO.ajouterArticle(article);
	}


}