package fr.eni.projet.bo;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class Utilisateur {

    private List<Enchere> encheres;
    private List<Article> articles;

    private long idUtilisateur;

    
    
    @NotBlank(message = "Le pseudo est obligatoire.")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,20}$")




    private String pseudo;
    @Pattern(regexp = "^[\\p{L} '-]{2,30}$")
    private String nom;
    @Pattern(regexp = "^[\\p{L} '-]{2,30}$") 
    		
    private String prenom;
    
    private String email;
    @Pattern(regexp = "^\\d{10}$")
    private String telephone;
    @Pattern(regexp = "^[\\p{L}\\d\\s,'-.]{5,50}$") 
    private String rue; 
    @Pattern(regexp = "^\\d{5}$")
    private String codePostal;
    private String ville;
    
    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")
    private String motDePasse;
    private int credit;
    private boolean administrateur;
    
    public Utilisateur() {
		// TODO Auto-generated constructor stub
	}

    public Utilisateur(long idUtilisateur, String pseudo, String nom,
                       String prenom, String email, String telephone, String rue, String codePostal, String ville,
                       String motDePasse, int credit, boolean administrateur) {
        this.encheres = new ArrayList<>();
        this.articles = new ArrayList<>();
        this.idUtilisateur = idUtilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.rue = rue;
        this.codePostal = codePostal;
        this.ville = ville;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }


    public List<Enchere> getEncheres() {
        return encheres;
    }

    public void setEncheres(List<Enchere> encheres) {
        this.encheres = encheres;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public long getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(long idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }


    @Override
    public String toString() {
        return "Utilisateur{" +
                "encheres=" + encheres +
                ", articles=" + articles +
                ", idUtilisateur=" + idUtilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", rue='" + rue + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", ville='" + ville + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }
}
