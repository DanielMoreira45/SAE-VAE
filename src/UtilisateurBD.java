import java.sql.PreparedStatement;

/**
 * 
 */
public class UtilisateurBD {
    private ConnexionMySQL laConnexionMySQL;
    /**
     * Default constructor
     */
    public UtilisateurBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }


    int maxIdUtilisateur() throws SQLException {
		this.st = laConnexion.createStatement();
		this.resultats = this.st.executeQuery("SELECT max(idut) FROM UTILISATEUR;");
		resultats.next();
		int nb = resultats.getInt(1);
		this.resultats.close();
		return nb;
	}


    public int insererUtilisateur(Utilisateur j, Role role) throws SQLException {
		PreparedStatement ps = laConnexion.prepareStatement("INSERT INTO JOUEUR VALUES(?, ?, ?, ?, ?, ?)");
		ps.setInt((1),  maxIdJoueur() + 1);
		ps.setString(2, j.getPseudo());
		ps.setString(3, j.getEmail());
		ps.setString(4, j.getMotDePasse());
        ps.setChar(5, j.estActive());
        ps.setInt(6, role);
		ps.executeUpdate();
	}


    





    





}