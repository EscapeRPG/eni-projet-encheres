package fr.eni.projet.dal;

import fr.eni.projet.bo.Categorie;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO{

    private final NamedParameterJdbcTemplate jdb;
    public CategorieDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }

    @Override
    public List<Categorie> listerCategorie() {
        String sql = "select * from categorie";
        return jdb.query(sql,new BeanPropertyRowMapper<>(Categorie.class));
    }

    @Override
    public Categorie afficherCategorieArticle(long idArticle) {
        String sql = "select * from categorie where idArticle = idArticle";
        return null;
    }

    @Override
    public void ajouterCategorie(Categorie categorie) {
        String sql = "insert into categorie (libelle) values (:libelle)";
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("libelle", categorie.getLibelle());
        jdb.update(sql, paramSource);
    }

    @Override
    public void SupprimerCategorie(long idCategorie) {
        String sql = "delete from categorie where idCategorie = :idCategorie";
        jdb.update(sql, new MapSqlParameterSource("idCategorie", idCategorie));

    }
}
