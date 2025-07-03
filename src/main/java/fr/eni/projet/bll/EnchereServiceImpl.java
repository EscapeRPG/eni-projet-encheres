package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.EnchereDAO;
import fr.eni.projet.dal.RetraitDAO;
import fr.eni.projet.dal.UtilisateurDAO;
import fr.eni.projet.exception.BusinessException;

import org.springframework.stereotype.Service;

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
	public void encherir(long idArticle, long idUtilisateur, int value) {

		if (value > enchereDAO.afficherEnchere(idArticle).getMontantEnchere()) {
			this.enchereDAO.creerEnchere(enchereDAO.afficherEnchere(idArticle));
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
		// TODO Auto-generated method stub

	}

	@Override
	public void clotureArticle(long idArticle) throws BusinessException {
		BusinessException be = new BusinessException();
		boolean existArticle = isExistArticle(idArticle, be);

		if (existArticle) {
			articleDAO.updateEtatArticle(idArticle, "CL");

		} else {
			throw be;
		}
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
		return categorieDAO.listerCategorie();
	}

}