import java.sql.PreparedStatement;

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
    public void insereObjet(Objet o,Utilisateur u, Categorie cat){
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO OBJET VALUES (?,?,?,?,?)");
        s.setDouble(1, o.getidObjet());
        s.setString(2, o.getNomObjet());
        s.setString(3, o.getDescription());
        s.setDouble(4, u.getIdUtilisateur());  
        s.setDouble(5, cat);
        s.executeQuery();
    }

}