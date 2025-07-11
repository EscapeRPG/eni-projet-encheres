package fr.eni.projet.bo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Retrait {

    @ManyToOne
    @JoinColumn(name = "article_id_article")
    private Article article;

    private String rue;
    private String codePostal;
    private String ville;
    @Id
    private Long id;

    public Retrait() {
		// TODO Auto-generated constructor stub
	}

    public Retrait(Article article, String rue, String codePostal, String ville) {
        this.article = article;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Retrait{" +
                "article=" + article +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
