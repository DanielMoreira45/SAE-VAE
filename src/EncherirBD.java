import java.sql.PreparedStatement;

/**
 * 
 */
public class EncherirBD {
    private ConnexionMySQL laConnexionMySQL;
    /**
     * Default constructor
     */
    public EncherirBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }
    public insereEnchere(Enchere e,Utilisateur u, Vente v){
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO ENCHERIR VALUES (?,?,?,?)");
        s.setDouble(1, u.getIdUtilisateur());
        s.setDouble(2, v.getIDVente());
        s.setDate(0, e.getDateHeure());
        s.setDouble(4, e.getMontant());
        s.executeQuery();
    }

}