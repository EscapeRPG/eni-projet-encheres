package fr.eni.projet.dal;

import fr.eni.projet.bo.Retrait;

public interface RetraitDAO {

    Retrait afficherRetrait(long idArticle);
    void creerRetrait(Retrait retrait);

}
