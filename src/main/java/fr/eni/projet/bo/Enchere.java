package fr.eni.projet.bo;

import java.time.LocalDateTime;

public class Enchere {

	private Utilisateur utilisateur;
	private Article enchereArticle;
	private LocalDateTime dateEnchere;
	private int montantEnchere;

	public Enchere() {
		// TODO Auto-generated constructor stub
	}

	public Enchere(Utilisateur utilisateur, Article enchereArticle, LocalDateTime dateEnchere, int montantEnchere) {
		this.utilisateur = utilisateur;
		this.enchereArticle = enchereArticle;
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Article getEnchereArticle() {
		return enchereArticle;
	}

	public void setEnchereArticle(Article enchereArticle) {
		this.enchereArticle = enchereArticle;
	}

	public LocalDateTime getDateEnchere() {
		return dateEnchere;
	}

	public void setDateEnchere(LocalDateTime dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	@Override
	public String toString() {
		return "Enchere{" + "utilisateur=" + utilisateur + ", enchereArticle=" + enchereArticle + ", dateEnchere="
				+ dateEnchere + ", montantEnchere=" + montantEnchere + '}';
	}
}
