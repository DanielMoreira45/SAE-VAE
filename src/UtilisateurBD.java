import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class UtilisateurBD {
    private ConnexionMySQL laConnexionMySQL;
    Statement st;

    /**
     * Default constructor
     */
    public UtilisateurBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
        System.out.println("connecteur "+this.laConnexionMySQL);
    }

    int maxIdUtilisateur() throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        ResultSet resultats = this.st.executeQuery("SELECT max(idut) FROM UTILISATEUR;");
        resultats.next();
        int nb = resultats.getInt(1);
        resultats.close();
        return nb;
    }

    public void insererUtilisateur(Utilisateur j) throws SQLException {
        System.out.println("Utilisateur BD"+this.laConnexionMySQL);
        PreparedStatement ps = laConnexionMySQL.preparedStatement("INSERT INTO UTILISATEUR VALUES(?, ?, ?, ?, ?, ?)");
        ps.setInt((1), 1010);
        ps.setString(2, j.getPseudo());
        ps.setString(3, j.getEmail());
        ps.setString(4, j.getMotDePasse());
        String estActive = j.estActive() ? "O" : "N";
        ps.setString(5, estActive);
        ps.setInt(6, j.getRole());
        ps.executeUpdate();
    }

    public void effacerJoueur(int num) throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        String query;
        query = "DELETE FROM UTILISATEUR WHERE idUt =" + num + ";";
        this.st.executeUpdate(query);
        st.executeUpdate(query);
    }

    public int idLibre() throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        ResultSet resultats = this.st.executeQuery("SELECT count(idUt) FROM UTILISATEUR");
        resultats.next();
        int nb = resultats.getInt(1);
        Integer maxId = maxIdUtilisateur();
        if (nb == maxId) {
            return maxId;
        } else {
            ResultSet lesId = this.st.executeQuery("SELECT idUt FROM UTILISATEUR");
            while (lesId.next()) {
                Integer actu = lesId.getInt(1);
                ResultSet leProchain = this.st.executeQuery("SELECT idUt FROM UTILISATEUR WHERE idUt =" + (actu + 1));
                if (!leProchain.next()) {
                    return actu + 1;
                }
            }
        }
    return 0;
    }

    public void majUtilisateur(Utilisateur j) throws SQLException {
        PreparedStatement ps = laConnexionMySQL.preparedStatement("INSERT INTO UTILISATEUR VALUES(?, ?, ?, ?, ?, ?)");
        ps.setInt((1), idLibre() + 1);
        ps.setString(2, j.getPseudo());
        ps.setString(3, j.getEmail());
        ps.setString(4, j.getMotDePasse());
        String estActive = j.estActive() ? "O" : "N";
        ps.setString(5, estActive);
        ps.setInt(6, j.getRole());
        ps.executeUpdate();
    }
}