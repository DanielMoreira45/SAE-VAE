import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

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
    public void insereEnchere(Enchere e) throws SQLException{
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO ENCHERIR VALUES (?,?,STR_TO_DATE(?,'%d/%m/%Y:%h:%i:%s'),?)");
        s.setInt(1, e.getEncherisseur().getId());
        s.setInt(2, e.getVente().getIDVente());
        s.setTimestamp(3, new Timestamp(e.getDateHeure()));
        s.setDouble(4, e.getMontant());
        s.executeQuery();
    }

    public void supprimeEnchere(Enchere e) throws SQLException{
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("DELETE FROM ENCHERIR WHERE idut = ?, idve = ?, montant = ?, dateheure = ?");
        s.setInt(1, e.getEncherisseur().getId());
        s.setDouble(2, e.getVente().getIDVente());
        s.setDouble(3, e.getMontant());
        s.setTimestamp(4, new Timestamp(e.getDateHeure()));
        s.executeQuery();
    }
    public Enchere meilleurEnchere(int idve) throws SQLException{
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("select * from Encherir natural join Vente where idve = "+idve+"and montant >= montant");
        rs.next();
        return new Enchere(null, null, null, null)
    }
}