package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;
import fr.eni.projet.dal.ArticleDAO;
import fr.eni.projet.dal.EnchereDAO;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnchereServiceImpl implements EnchereService{

	@Override
	public List<Article> consulterAllVentes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void encherir(long idArticle, long idUtilisateur, int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Article detailVente(long idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remporterVente(long idArticle) {
		// TODO Auto-generated method stub
		
	}
}