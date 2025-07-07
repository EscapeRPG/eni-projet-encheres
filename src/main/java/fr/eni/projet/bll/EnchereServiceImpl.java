package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Enchere;
import fr.eni.projet.bo.Utilisateur;
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
		
		Enchere enchereActuelle = enchereDAO.enchereMax(idArticle);
		int montantActuel = 0;
		
		if(enchereActuelle != null) {
			montantActuel = enchereActuelle.getMontantEnchere();
		}
		
		if(montant > montantActuel) {
			Utilisateur utilisateur = utilisateurDAO.consulterCompte(idUtilisateur);
			Article article = articleDAO.afficherArticle(idArticle);
			Enchere newEnchere = new Enchere(utilisateur, article, LocalDateTime.now(), montant);
			this.enchereDAO.creerEnchere(newEnchere);
		}
		else {
			System.out.println("Saisir une enchère plus élevée");
		}

	}

	@Override
	public Article detailVente(long idArticle) throws BusinessException {
		
		BusinessException be = new BusinessException();
		boolean existArticle = isExistArticle(idArticle, be);
		
		if(existArticle) {
			Article article = this.articleDAO.afficherArticle(idArticle);
			article.setCategorie(categorieDAO.afficherCategorieArticle(idArticle));
			article.setUtilisateur(utilisateurDAO.consulterCompte(article.getUtilisateur().getIdUtilisateur()));
			article.setRetrait(retraitDAO.afficherRetrait(idArticle));
			
		return article;
		}
		else {
			throw be;
		}
	
	}

	@Override
	public void remporterVente(long idArticle) {
		

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
	public Enchere consulterEnchereMax(long idArticle) {
		return this.enchereDAO.enchereMax(idArticle);
	}

	
}