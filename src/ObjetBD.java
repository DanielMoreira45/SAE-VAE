import java.sql.*;

/**
 * 
 */
public class ObjetBD {
    private ConnexionMySQL laConnexionMySQL;
    Integer idLibreOb = null;
    Statement st;
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

    int maxIdObjet() throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        ResultSet resultats = this.st.executeQuery("SELECT max(idut) FROM UTILISATEUR;");
        resultats.next();
        int nb = resultats.getInt(1);
        resultats.close();
        return nb;
    }

    public int idLibre() throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        ResultSet resultats = this.st.executeQuery("SELECT count(idOb) FROM UTILISATEUR");
        resultats.next();
        int nb = resultats.getInt(1);
        Integer maxId = maxIdObjet();
        if (nb == maxId) {
            return maxId;
        } else {
            ResultSet lesId = this.st.executeQuery("SELECT i FROM UTILISATEUR");
            while (lesId.next()) {
                Integer actu = lesId.getInt(1);
                ResultSet leProchain = this.st.executeQuery("SELECT idUt FROM UTILISATEUR WHERE idUt =" + (actu + 1));
                if (!leProchain.next()) {
                    this.idLibreOb = actu + 1;
                }
            }
        }
    return this.idLibreOb;
    }

}