package fr.eni.projet.dal;

import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RetraitDAOImpl implements RetraitDAO{


    private final NamedParameterJdbcTemplate jdb;
    public RetraitDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }

    @Override
    public Retrait afficherRetrait(long idArticle) {
        String sql = "SELECT * FROM retrait WHERE idArticle = :idArticle";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("idArticle", idArticle);
        return jdb.queryForObject(sql, paramSource, new BeanPropertyRowMapper<>(Retrait.class));
    }

    @Override
    public void creerRetrait(Retrait retrait) {
        String sql = "insert into retrait(rue,codePostal,ville,idArticle) "
                + "values (:rue,:codePostal,:ville,:idArticle)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("rue", retrait.getRue());
        paramSource.addValue("codePostal", retrait.getCodePostal());
        paramSource.addValue("ville", retrait.getVille());
        paramSource.addValue("idArticle", retrait.getArticle().getIdArticle());
        jdb.update(sql, paramSource);
    }
}
