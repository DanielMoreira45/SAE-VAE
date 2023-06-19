import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * 
 */
public class VenteBD {
    private ConnexionMySQL laConnexionMySQL;
    /**
     * Default constructor
     */
    public VenteBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }
    public void insereVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO VENTE VALUES (?,?,?,?,?,?,?)");
        s.setInt(1, v.getIDVente());
        s.setDouble(2, v.getPrixBase());
        s.setDouble(3, v.getPrixMin());
        s.setTimestamp(4, new Timestamp(v.getDebutVente()));
        s.setTimestamp(5,new Timestamp(v.getFinVente()));
        s.setInt(6, v.getObjet().getidObjet());
        s.setInt(7, v.getStatus());
        s.executeQuery();
    }
    public void supprimeVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("DELETE FROM VENTE WHERE idve = ?, idob = ?, idst = ?");
        s.setInt(1, v.getIDVente());
        s.setInt(2, v.getObjet().getidObjet());
        s.setInt(3, v.getStatus());        
        s.executeQuery();
    }
}