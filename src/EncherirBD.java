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
    public void insereEnchere(Enchere e){
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO ENCHERIR VALUES (?,?,?,?)");
        s.setInt(1, s.getIDUtilisateur());
        s.setInt(2, s.getIDVente());
        s.setDate(3, e.getDateHeure());
        s.setDouble(4, e.getMontant());
        s.executeQuery();
    }
    public void supprimeEnchere(Enchere e){
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("DELETE FROM ENCHERIR WHERE idut = ?, idve = ?, montant = ?, dateheure = ?");
        s.setInt(1, e.getIDUtilisateur());
        s.setDouble(2, e.getIDVente());
        s.setDouble(3, e.getMontant());
        s.setDate(4, e.getDateHeure());
    }
}