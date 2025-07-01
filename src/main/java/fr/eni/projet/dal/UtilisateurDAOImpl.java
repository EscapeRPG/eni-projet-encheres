package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;


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
    public void supprimerCompte(long idUtilisateur) {
        String sql = "delete from utilisateur where email = :idUtilisateur";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
        jdb.update(sql,mapSqlParameterSource);

    }

    @Override
    public int consulterNbreCredit(long idUtilisateur) {
        String sql = "select credit from utilisateur where idUtilisateur  = :idUtilisateur";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
        return jdb.queryForObject(sql,mapSqlParameterSource,Integer.class);
    }

    @Override
    public Utilisateur consulterCompte(long idUtilisateur) {
        String sql = "select * from utilisateur where idUtilisateur = :idUtilisateur";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
        return jdb.queryForObject(sql,mapSqlParameterSource,Utilisateur.class);
    }

    @Override
    public Utilisateur consulterCompte(String pseudo) {
        String sql = "select * from utilisateur where pseudo = :pseudo";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", pseudo);
        return jdb.queryForObject(sql,mapSqlParameterSource,Utilisateur.class);
    }

    @Override
    public List<Utilisateur> afficherComptes() {
        String sql = "select * from utilisateur";
        return jdb.query(sql,new BeanPropertyRowMapper<>(Utilisateur.class));
    }



    @Override
    public void desactiverCompte(long idUtilisateur) {

    }

    @Override
    public boolean isUtilisateurInBDD(long idUtilisateur) {
        String sql = "select count(*) from utilisateur where idUtilisateur = :idUtilisateur";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
        int i = jdb.queryForObject(sql,mapSqlParameterSource,Integer.class);
        if (i == 0) {
            return false;
        }
        else if (i == 1) {
            return true;
        }
        else
        {
            System.out.println("Erreur plus de 1 utilisateur présent");
            return true;
        }
    }


}


