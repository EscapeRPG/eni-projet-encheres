package fr.eni.projet.dal;

import fr.eni.projet.bo.Enchere;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO{

    private final NamedParameterJdbcTemplate jdb;
    public EnchereDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }


    @Override
    public void creerEnchere(Enchere enchere) {
        String sql = "merge into enchere as vente " +
                "using (select :dateEnchere as dateEnchere, :montantEnchere as montantEnchere, " +
                ":idArticle as idArticle, :idUtilisateur as idUtilisateur) as source " +
                "on vente.idArticle = source.idArticle " +
                "when matched then " +
                "update set dateEnchere = source.dateEnchere, " +
                "montantEnchere = source.montantEnchere, " +
                "idArticle = source.idArticle, " +
                "idUtilisateur = source.idUtilisateur " +
                "when not matched then  " +
                "insert (dateEnchere,montantEnchere,idArticle,idUtilisateur) " +
                "values (:dateEnchere, :montantEnchere, :idArticle, :idUtilisateur);";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("dateEnchere", enchere.getDateEnchere());
        mapSqlParameterSource.addValue("montantEnchere", enchere.getMontantEnchere());
        mapSqlParameterSource.addValue("idArticle", enchere.getEnchereArticle().getIdArticle());
        mapSqlParameterSource.addValue("idUtilisateur", enchere.getUtilisateur().getIdUtilisateur());

        jdb.update(sql, mapSqlParameterSource);
    }

    @Override
    public Enchere enchereMax(long idArticle) {
        String sql = "SELECT *" +
                " FROM enchere" +
                " WHERE idArticle = :idArticle and montantEnchere = (SELECT MAX(montantEnchere) FROM enchere where idArticle = :idArticle )";

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idArticle", idArticle);

        return jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Enchere.class));

    }


    @Override
    public List<Enchere> consulterEnchere() {
        String sql = "select * from enchere";
        return jdb.query(sql,new BeanPropertyRowMapper<>(Enchere.class));
    }

    @Override
    public Enchere afficherEnchere(long idArticle) {
        String sql = "select * from enchere where idArticle = :idArticle";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idArticle", idArticle);
        return jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Enchere.class));
    }
    

}
