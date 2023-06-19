import java.sql.PreparedStatement;

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
    public void insereVente(Vente v, Objet o, Status status) {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO VENTE VALUES (?,?,?,?,?,?,?)");
        s.setInt(1, v.getIdVente());
        s.setDouble(2, v.getPrixBase());
        s.setDouble(3, v.getPrixMin());
        s.setDate(4, v.getdebutVente());
        s.setDate(5, v.getfinVe());
        s.setInt(6, o.getidObjet());
        s.setString(7, status);
        s.executeQuery();
    }
}