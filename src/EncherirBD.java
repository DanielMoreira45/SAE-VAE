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
    public void insereVente(Vente v) {




        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO VENTE VALUES (?,?,?,?,?,?,?)");
        s.setDouble(1, v.getIdVente());
        s.setDouble(2, v.getPrixBase());
        s.setDouble(3, v.getPrixMin());
        s.setDate(4, v.getdebutVente());
        s.setDate(5, v.getfinVe());
        s.setDouble(6, 0.);
        s.setString(7, null);
        s.executeQuery();
    }

}