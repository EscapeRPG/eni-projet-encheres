package fr.eni.projet.bll;

import fr.eni.projet.bo.Article;

import java.util.List;

public interface EnchereService {

    /**
     * Récupère la liste complète des ventes disponibles sur la plateforme.
     * @return une liste d'objets {@link Article} représentant toutes les ventes en cours.
     */
    List<Article> consulterAllVentes();

    /**
     * Permet à un utilisateur de placer une enchère sur un article spécifique.
     * @param idArticle     l'identifiant de l'article concerné par l'enchère.
     * @param idUtilisateur l'identifiant de l'utilisateur plaçant l'enchère.
     * @param value         le montant proposé pour l'enchère.
     */
    void encherir(long idArticle, long idUtilisateur, int value);

    /**
     * Récupère les détails complets d'un article en vente.
     * @param idArticle l'identifiant de l'article à consulter.
     * @return l'objet {@link Article} contenant les informations détaillées de la vente.
     */
    Article detailVente(long idArticle);

    /**
     * Détermine et enregistre l'utilisateur ayant remporté l'enchère
     * sur un article donné (c'est-à-dire celui ayant proposé la valeur la plus haute).
     * N.B : À utiliser par le contrôleur lors de la clôture.
     * @param idArticle l'identifiant de l'article concerné.
     */
    void remporterVente(long idArticle);

}
