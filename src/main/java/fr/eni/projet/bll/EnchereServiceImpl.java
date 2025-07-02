package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.EnchereDAO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService{

	



	@Override
	public Article detailVente(long idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

    public EnchereServiceImpl(ArticleDAO articleDAO, EnchereDAO enchereDAO) {
		this.articleDAO = articleDAO;
		this.enchereDAO = enchereDAO;
	}

	@Override
    public List<Article> consulterAllVentes() {
    	
    	List<Article> articles = this.articleDAO.afficherArticles();
    	
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
	public void remporterVente(long idArticle) {
		// TODO Auto-generated method stub
		
	}
}