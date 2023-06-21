import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 */
public class ObjetBD {
    private ConnexionMySQL laConnexionMySQL;
    /**
     * Default constructor
     */
    public ObjetBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
    }
    public void insereObjet(Objet o) throws SQLException{
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO OBJET VALUES (?,?,?,?,?)");
        s.setInt(1, o.getidObjet());
        s.setString(2, o.getNomObjet());
        s.setString(3, o.getDescription());
        s.setInt(4, o.getVendeur().getId());  
        s.setDouble(5, o.getCategorie());
        s.executeQuery();
    }
    public void supprimeObjet(Objet o) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("DELETE FROM OBJET WHERE idob = ?, nomob = ?, idut = ?, idcat = ?");
        s.setInt(1, o.getidObjet());
        s.setString(2, o.getNomObjet());
        s.setInt(3, o.getVendeur().getId());
        s.setInt(4, o.getCategorie());
        s.executeQuery();
    }
    public Objet recupObjet(int idob) throws SQLException{
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT * FROM OBJET WHERE idob ="+idob);
        rs.next();
        System.out.println("A verifier");
        return new Objet(idob, rs.getString(2), rs.getString(3), null, null, rs.getInt(4));
    }
    public int maxIdOb() throws SQLException{
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(idob)");
        rs.next();
        return rs.getInt(1);
    }
}