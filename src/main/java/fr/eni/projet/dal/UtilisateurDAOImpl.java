package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UtilisateurDAOImpl implements UtilisateurDAO{

    private static final Class<Utilisateur> entityClass = Utilisateur.class;

    private final NamedParameterJdbcTemplate jdb;
    public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }

    @Override
    public void creerCompte(Utilisateur u) {
        String sql = "insert into utilisateur (pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,administrateur) "
                + "value (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :administrateur)";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", u.getPseudo());
        mapSqlParameterSource.addValue("nom", u.getNom());
        mapSqlParameterSource.addValue("prenom", u.getPrenom());
        mapSqlParameterSource.addValue("email", u.getEmail());
        mapSqlParameterSource.addValue("telephone", u.getTelephone());
        mapSqlParameterSource.addValue("rue", u.getRue());
        mapSqlParameterSource.addValue("codePostal", u.getCodePostal());
        mapSqlParameterSource.addValue("ville", u.getVille());
        mapSqlParameterSource.addValue("motDePasse", u.getMotDePasse());
    }

    @Override
    public Utilisateur connecterCompte(String pseudo, String motDePasse) {
        String sql = "select * from utilisateur where pseudo = :pseudo and motDePasse = :motDePasse";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", pseudo);
        mapSqlParameterSource.addValue("motDePasse", motDePasse);
        return  jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public String  motDePasseOublie(String email) {
        String sql = "select motDePasse from utilisateur where email = :email";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("email", email);
        return jdb.queryForObject(sql,mapSqlParameterSource,String.class);
    }

    @Override
    public void supprimerCompte(String idUtilisateur) {
        String sql = "delete from utilisateur where email = :idUtilisateur";

    }

    @Override
    public int consulterNbreCredit(String idUtilisateur) {
        return 0;
    }

    @Override
    public Utilisateur consulterCompte(String idUtilisateur) {
        return null;
    }

    @Override
    public List<Utilisateur> afficherComptes() {
        return List.of();
    }

    @Override
    public void desactiverCompte(String idUtilisateur) {

    }


}


