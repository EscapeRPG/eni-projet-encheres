package fr.eni.projet.dal;

import fr.eni.projet.bo.Utilisateur;
import fr.eni.projet.exception.BusinessException;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.security.Key;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO{
	
	private UtilisateurDAO utilisateurDAO;
    private final NamedParameterJdbcTemplate jdb;
    public UtilisateurDAOImpl(NamedParameterJdbcTemplate jdb) {
        this.jdb = jdb;
    }

    @Override
    public void creerCompte(Utilisateur u) {
        String sql = "INSERT INTO utilisateur (pseudo,nom,prenom,email,telephone,rue,codePostal,ville,motDePasse,administrateur) "
                + "values (:pseudo, :nom, :prenom, :email, :telephone, :rue, :codePostal, :ville, :motDePasse, :administrateur)";
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
        mapSqlParameterSource.addValue("administrateur", u.isAdministrateur());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdb.update(sql,mapSqlParameterSource,keyHolder);

        if(keyHolder.getKey()!=null) {
            u.setIdUtilisateur(keyHolder.getKey().longValue());
        }
    }

    @Override
    public Utilisateur connecterCompte(String pseudo, String motDePasse) {
        String sql = "select * from utilisateur where pseudo = :pseudo";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", pseudo);
        Utilisateur utilisateurBDD = jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
        
        if(utilisateurBDD.getMotDePasse().equals(motDePasse)) {
        	return utilisateurBDD;
        }
        
        return null;
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
        String sql1 = "delete from enchere where idUtilisateur = :idUtilisateur;";
        String sql2 = "delete from utilisateur where idUtilisateur = :idUtilisateur";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("idUtilisateur", idUtilisateur);
        jdb.update(sql1,mapSqlParameterSource);
        jdb.update(sql2,mapSqlParameterSource);

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
        return jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
    }

    @Override
    public Utilisateur consulterCompte(String pseudo) {
        String sql = "select * from utilisateur where pseudo = :pseudo";
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", pseudo);
        return jdb.queryForObject(sql,mapSqlParameterSource,new BeanPropertyRowMapper<>(Utilisateur.class));
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
        
        return i != 0;
    }
    
    @Override
    public boolean isUtilisateurInBDD(String pseudo) {
        String sql = "select count(*) from utilisateur where pseudo = :pseudo";       
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("pseudo", pseudo);
        int i = jdb.queryForObject(sql,mapSqlParameterSource,Integer.class);
        
        return i != 0; 
    }
    
   
    
    @Override
	public boolean emailExist(String email) {
    	String sql = "SELECT COUNT(*) FROM utilisateur WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);
        int count = jdb.queryForObject(sql, params, Integer.class);
        return count != 0;
    }
 
    @Override
	public boolean isEmailInBDD(String email) {
		return false;
	}


	@Override 
	public void updateCompte(Utilisateur u) {
		String sql = "update utilisateur set pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email,"
				+ "telephone = :telephone, rue = :rue, codePostal = :codePostal, ville = :ville where idUtilisateur = :idUtilisateur";
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("pseudo", u.getPseudo());
		map.addValue("nom", u.getNom());
		map.addValue("prenom", u.getPrenom());
		map.addValue("email", u.getEmail());
		map.addValue("telephone", u.getTelephone());
		map.addValue("rue", u.getRue());
		map.addValue("codePostal", u.getCodePostal());
		map.addValue("ville", u.getVille());
        map.addValue("idUtilisateur", u.getIdUtilisateur());
        jdb.update(sql,map);
		
	}

	@Override
	public void crediter(long idUtiisateur) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void crediterVendeur(long idUtilisateur, long idArticle) {
		
		String sql = "SELECT MAX(montantEnchere) FROM enchere WHERE idArticle = :idArticle";
		
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idArticle", idArticle);
		Integer montantCredit = jdb.queryForObject(sql, map,Integer.class);
	
		String sqlUpdate = "update utilisateur set credit = :montantCredit where idUtilisateur = :idUtilisateur";
		MapSqlParameterSource map2 = new MapSqlParameterSource();
		map2.addValue("montantCredit", montantCredit);
		map2.addValue("idUtilisateur", idUtilisateur);
		
		jdb.update(sqlUpdate, map2);
	}

	@Override
	public void debiter(long idUtilisateur, int montant) {
		
		String sql ="update utilisateur set credit = :montant where idUtilisateur = :idUtilisateur";
		
		int montantDebit = (consulterCompte(idUtilisateur).getCredit() - montant);
		MapSqlParameterSource map = new MapSqlParameterSource();
		map.addValue("idUtilisateur", idUtilisateur);
		map.addValue("montant", montantDebit);
		
		jdb.update(sql,map);
	}

	

	
	
}




