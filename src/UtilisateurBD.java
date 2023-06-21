
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 */
public class UtilisateurBD {
    private ConnexionMySQL laConnexionMySQL;
    Statement st;
    Integer idLibre = null;

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
        System.out.println(idLibre());
        ps.setInt((1), idLibre());
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
                    this.idLibre= actu + 1;
                }
            }
        }
    return this.idLibre;
    }

    public Integer getIDlibre(){
        return this.idLibre;
    }

    public Map<String, Object> rechercherJoueurParMail(String mail) throws SQLException {
        Map<String, Object> resultat = null;
        String query = "SELECT * FROM UTILISATEUR WHERE emailut = ?";
        System.out.println("rentrefonction");
        PreparedStatement statement = laConnexionMySQL.preparedStatement(query);
        statement.setString(1, mail);
        System.out.println("preparedinsertinmail");
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            resultat = new HashMap<String, Object>();
            int id = resultSet.getInt("idUt");
            String pseudo = resultSet.getString("pseudout");
            String email = resultSet.getString("emailut");
            String motDePasse = resultSet.getString("mdput");
            boolean estActif = resultSet.getString("activeut").equalsIgnoreCase("O");
            int role = resultSet.getInt("idrole");
            resultat.put("idut", id);
            resultat.put("pseudout", pseudo);
            resultat.put("emailut", email);
            resultat.put("mdput", motDePasse);
            resultat.put("activeut", estActif);
            resultat.put("idrole", role);
        }
        resultSet.close();
        statement.close();
    
        return resultat;
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

    public Utilisateur utilisateurParId(int idut) throws SQLException{
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
        rs.next();
        String pseudoV = rs.getString(1);
        String emailV = rs.getString(2);
        String mdpV = rs.getString(3);
        String activeV = rs.getString(4);
        boolean actifV = false;
        if (activeV.equals("O")) {
            actifV = true;
        }
        int idRoleV = rs.getInt(5);
        Utilisateur util = new Utilisateur(idut, pseudoV, emailV, mdpV, actifV, idRoleV);
        s.close();
        rs.close();
        return util;
    }

}