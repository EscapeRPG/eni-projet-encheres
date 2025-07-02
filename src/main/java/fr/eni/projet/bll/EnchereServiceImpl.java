package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.CategorieDAO;
import fr.eni.projet.dal.EnchereDAO;
import fr.eni.projet.dal.RetraitDAO;
import fr.eni.projet.dal.UtilisateurDAO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService{

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
    		Utilisateur utilisateur = utilisateurDAO.consulterCompte(1);
//			article.setCategorie(this.categorieDAO.afficherCategorieArticle(article.getIdArticle()).getCategorie());
//			article.setRetrait(this.retraitDAO.afficherRetrait(article.getIdArticle()));
			article.setUtilisateur(utilisateur);
		}
    	
        return articles;
    }

    @Override
    public void encherir(long idArticle, long idUtilisateur, int value) {
    	
    	if(value > enchereDAO.afficherEnchere(idArticle).getMontantEnchere()) {
    		this.enchereDAO.creerEnchere(enchereDAO.afficherEnchere(idArticle));
    	}
    	else {
    		System.out.println("Saisir une enchère plus élévée");
    	
    	}
     
    }

    @Override
    public Article detailVente(long idArticle) {
    	
        return this.articleDAO.afficherArticle(idArticle);
    }

    @Override
    public void remporterVente(long idArticle) {


    }
}
