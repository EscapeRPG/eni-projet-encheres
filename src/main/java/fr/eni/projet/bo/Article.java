package fr.eni.projet.bo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private List<Enchere> encheres;
    private Utilisateur utilisateur;
    private Categorie categorie;
    private Retrait retrait;

    private long idArticle;
    private String nomArticle;
    private String descriptions;
    private LocalDateTime dateDebutEncheres;
    private LocalDateTime dateFinEncheres;
    private int miseAPrix;
    private int prixVente;
    private String etatVente;
    
    public Article() {
		// TODO Auto-generated constructor stub
	}

    public Article(Utilisateur utilisateur, Categorie categorie, Retrait retrait,
                   long idArticle, String nomArticle, String descriptions, LocalDateTime dateDebutEncheres,
                   LocalDateTime dateFinEncheres, int miseAPrix, int prixVente, String etatVente) {
        this.encheres = new ArrayList<>();
        this.utilisateur = utilisateur;
        this.categorie = categorie;
        this.retrait = retrait;
        this.idArticle = idArticle;
        this.nomArticle = nomArticle;
        this.descriptions = descriptions;
        this.dateDebutEncheres = dateDebutEncheres;
        this.dateFinEncheres = dateFinEncheres;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
    }

    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Retrait getRetrait() {
        return retrait;
    }

    public void setRetrait(Retrait retrait) {
        this.retrait = retrait;
    }

    public long getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(long idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public LocalDateTime getDateDebutEncheres() {
        return dateDebutEncheres;
    }

    public void setDateDebutEncheres(LocalDateTime dateDebutEncheres) {
        this.dateDebutEncheres = dateDebutEncheres;
    }

    public LocalDateTime getDateFinEncheres() {
        return dateFinEncheres;
    }

    public void setDateFinEncheres(LocalDateTime dateFinEncheres) {
        this.dateFinEncheres = dateFinEncheres;
    }

    public int getMiseAPrix() {
        return miseAPrix;
    }

    public void setMiseAPrix(int miseAPrix) {
        this.miseAPrix = miseAPrix;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(int prixVente) {
        this.prixVente = prixVente;
    }

    public String getEtatVente() {
        return etatVente;
    }

    public void setEtatVente(String etatVente) {
        this.etatVente = etatVente;
    }

    @Override
    public String toString() {
        return "Article{" +
                "encheres=" + encheres +
                ", utilisateur=" + utilisateur +
                ", categorie=" + categorie +
                ", retrait=" + retrait +
                ", idArticle=" + idArticle +
                ", nomArticle='" + nomArticle + '\'' +
                ", description='" + descriptions + '\'' +
                ", dateDebutEncheres=" + dateDebutEncheres +
                ", dateFinEncheres=" + dateFinEncheres +
                ", miseAPrix=" + miseAPrix +
                ", prixVente=" + prixVente +
                ", etatVente='" + etatVente + '\'' +
                '}';
    }
}
