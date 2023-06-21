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

    public void insereObjet(Objet o) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO OBJET VALUES (?,?,?,?,?)");
        s.setInt(1, o.getidObjet());
        s.setString(2, o.getNomObjet());
        s.setString(3, o.getDescription());
        s.setInt(4, o.getVendeur().getId());
        s.setDouble(5, o.getCategorie());
        s.executeQuery();
    }

    public void supprimeObjet(Objet o) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("DELETE FROM OBJET WHERE idob = ?, nomob = ?, idut = ?, idcat = ?");
        s.setInt(1, o.getidObjet());
        s.setString(2, o.getNomObjet());
        s.setInt(3, o.getVendeur().getId());
        s.setInt(4, o.getCategorie());
        s.executeQuery();
    }

    public Objet recupObjet(int idob) throws SQLException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT idob,nomob,descriptionob,idut,idcat FROM OBJET WHERE idob =" + idob);
        rs.next();
        int idut = rs.getInt(4);
        String desc = rs.getString(3);
        String nomOb = rs.getString(2);
        int idcat = rs.getInt(5);
        rs.close();

        Statement s2 = this.laConnexionMySQL.createStatement();
        ResultSet rs2 = s2
                .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
        ;
        rs2.next();
        String pseudo = rs2.getString(1);
        String email = rs2.getString(2);
        String mdp = rs2.getString(3);
        String active = rs2.getString(4);
        boolean actif = false;
        if (active.equals("O")) {
            actif = true;
        }
        int idRole = rs2.getInt(5);
        rs2.close();
        Utilisateur util = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
        return new Objet(idob, desc, nomOb, null, util, idcat);
    }

    public int maxIdOb() throws SQLException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(idob) FROM OBJET");
        rs.next();
        return rs.getInt(1);
    }
}