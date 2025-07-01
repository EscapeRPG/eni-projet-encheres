package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;

public class ArticleDAOImpl implements ArticleDAO {

    private final NamedParameterJdbcTemplate jdb;
    public ArticleDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }

    @Override
    public void ajouterArticle(Article article) {
        String sql = "insert into article(nomArticle,descriptions,dateDebutEncheres,dateFinEncheres,miseAPrix,prixVente,idUtilisateur,idCategorie) "
                + "values(:nomArticle, :descriptions, :dateDebutEncheres, :dateFinEncheres, :miseAPrix, :prixVente, :idUtilisateur, :idCategorie)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("nomArticle", article.getNomArticle());
        mapSqlParameterSource.addValue("descriptions",article.getDescription());
        mapSqlParameterSource.addValue("dateDebutEncheres", article.getDateDebutEncheres());
        mapSqlParameterSource.addValue("dateFinEncheres", article.getDateFinEncheres());
        mapSqlParameterSource.addValue("miseAPrix", article.getMiseAPrix());
        mapSqlParameterSource.addValue("prixVente", article.getPrixVente());
        mapSqlParameterSource.addValue("idUtilisateur",article.getUtilisateur().getIdUtilisateur());
        mapSqlParameterSource.addValue("idCategorie",article.getCategorie().getIdCategorie());

        jdb.update(sql, mapSqlParameterSource);
    }

    @Override
    public void supprimerArticle(long idArticle) {
        String sql = "delete from article where idArticle = :idArticle";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idArticle", idArticle);
        jdb.update(sql, mapSqlParameterSource);
    }

    @Override
    public List<Article> afficherArticle() {
        String sql = "select * from article";
        return jdb.query(sql,new BeanPropertyRowMapper<>(Article.class));
    }

    @Override
    public Article afficherArticle(long idArticle) {
        String sql = "select * from article where idArticle = :idArticle";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idArticle", idArticle);

        return jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Article.class));
    }
}
