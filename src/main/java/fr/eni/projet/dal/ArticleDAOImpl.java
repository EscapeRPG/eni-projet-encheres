package fr.eni.projet.dal;

import fr.eni.projet.bo.Article;
import fr.eni.projet.bo.Categorie;
import fr.eni.projet.bo.Retrait;
import fr.eni.projet.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
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
        mapSqlParameterSource.addValue("descriptions",article.getDescriptions());
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
    public List<Article> afficherArticles() {
        String sql = "select * from article";
        return jdb.query(sql,new ArticleMapper());
    }

    @Override
    public Article afficherArticle(long idArticle) {
        String sql = "select * from article where idArticle = :idArticle";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idArticle", idArticle);

        return jdb.queryForObject(sql,mapSqlParameterSource,new ArticleMapper());
    }
}

class ArticleMapper implements RowMapper<Article>
{

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {

        Categorie categorie = new Categorie();
        categorie.setIdCategorie(rs.getLong("idCategorie"));

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setIdUtilisateur(rs.getLong("idUtilisateur"));

        Article article = new Article();
        article.setIdArticle(rs.getLong("idArticle"));
        article.setNomArticle(rs.getString("nomArticle"));
        article.setDescriptions(rs.getString("descriptions"));
        article.setCategorie(categorie);
        article.setUtilisateur(utilisateur);
        return article;
    }
}