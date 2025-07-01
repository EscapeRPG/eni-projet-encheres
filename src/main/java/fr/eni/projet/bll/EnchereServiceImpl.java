package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService{

	private ArticleDAO articleDAO;

    @Override
    public List<Article> consulterAllVentes() {
    	
    	List<Article> articles = this.articleDAO.findAll();
        return articles;
    }

    @Override
    public Article encherir(long idArticle, long idUtilisateur, int value) {
        return null;
    }

    @Override
    public Article detailVente(long idArticle) {
        return null;
    }

    @Override
    public void remporterVente(long idArticle) {

    }
}
