
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        PreparedStatement ps = laConnexionMySQL.preparedStatement("INSERT INTO UTILISATEUR VALUES(?, ?, ?, ?, ?, ?)");
        ps.setInt((1), idLibre());
        ps.setString(2, j.getPseudo());
        ps.setString(3, j.getEmail());
        ps.setString(4, j.getMotDePasse());
        String estActive = j.estActive() ? "O" : "N";
        ps.setString(5, estActive);
        ps.setInt(6, j.getRole());
        ps.executeUpdate();
    }

    public void supprimerUtilisateur(int num) throws SQLException {
        ResultSet resultNumObj = laConnexionMySQL.createStatement().executeQuery("SELECT idob FROM OBJET WHERE idut =" + num + ";");
        resultNumObj.next();
        int numObj = resultNumObj.getInt(1);
        ResultSet resultNumVe = laConnexionMySQL.createStatement().executeQuery("SELECT idve FROM VENTE WHERE idob =" + numObj + ";");
        resultNumVe.next();
        int numVe = resultNumVe.getInt(1);
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM ENCHERIR WHERE idve =" + numVe + ";");
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM VENTE WHERE idob =" + numObj + ";");
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM PHOTO WHERE idob =" + numObj + ";");
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM OBJET WHERE idut =" + num + ";");
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM ENCHERIR WHERE idut =" + num + ";");
        laConnexionMySQL.createStatement().executeUpdate("DELETE FROM UTILISATEUR WHERE idut =" + num + ";");
    }



    /**
     * solution non chosie car si un utilisateur possédant des objets aux enchères est supprimé, l'id libre qui sera dorénavant attribuable
     * associera un nouvel utilisateur aux ventes de l'ancien, hors nous souhaitons (en vue du temps) ne pas s'impliquer dans la gestion de dépense.*
     * @return int un id libre (c-a-d l'id le plus bas possible attribuable)
     * @throws SQLException
     */
    public int idLibre() throws SQLException {
        this.st = laConnexionMySQL.createStatement();
        ResultSet resultats = this.st.executeQuery("SELECT count(idUt) FROM UTILISATEUR");
        resultats.next();
        int nb = resultats.getInt(1);
        Integer maxId = maxIdUtilisateur();
        if (nb == maxId) {
            return maxId + 1;
        } else {
            ResultSet lesId = this.st.executeQuery("SELECT idUt FROM UTILISATEUR");
            boolean idFound = false;
            for (int i = 1; i <= maxId; i++) {
                if (lesId.next()) {
                    int actu = lesId.getInt(1);
                    if (actu != i) {
                        this.idLibre = i;
                        idFound = true;
                        break;
                    }
                } else {
                    this.idLibre = i;
                    idFound = true;
                    break;
                }
            }
            if (!idFound) {
                this.idLibre = maxId + 1;
            }
        }
        return this.idLibre;
    }

    public Integer getIDlibre() {
        return this.idLibre;
    }

    public void setActif(Utilisateur utilisateur) throws SQLException {
        PreparedStatement ps = laConnexionMySQL.preparedStatement("UPDATE UTILISATEUR SET activeut = ? WHERE idut = ?");
        ps.setString(1, utilisateur.estActive() ? "O" : "N");
        ps.setInt(2, utilisateur.getId());
        ps.executeUpdate();
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

    public Utilisateur utilisateurParId(int idut) throws SQLException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s
                .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
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

    public List<Utilisateur> tout() throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement()
                .executeQuery("SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR;");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public List<Utilisateur> toutAdmin() throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement()
                .executeQuery("SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR WHERE idrole = 1;");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public List<Utilisateur> toutUtilisateurs() throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement()
                .executeQuery("SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR WHERE idrole = 2;");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public List<Utilisateur> actif() throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement()
                .executeQuery("SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR WHERE activeut = 'O';");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public List<Utilisateur> inactif() throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement()
                .executeQuery("SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR WHERE activeut = 'N';");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public List<Utilisateur> recherche(String text) throws SQLException {
        ResultSet rs = this.laConnexionMySQL.createStatement().executeQuery(
                "SELECT idut, pseudout, emailut, mdput, activeut, idrole FROM UTILISATEUR WHERE pseudout LIKE '%" + text
                        + "%' or emailut LIKE '%" + text + "%';");
        List<Utilisateur> listeUtilisateurs = new ArrayList<>();
        while (rs.next()) {
            listeUtilisateurs.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
                    rs.getString(5).equals("O") ? true : false, rs.getInt(6)));
        }
        return listeUtilisateurs;
    }

    public void setRole(Utilisateur utilisateur) throws SQLException {
        PreparedStatement ps = laConnexionMySQL.preparedStatement("UPDATE UTILISATEUR SET idrole = ? WHERE idut = ?");
        ps.setInt(1, utilisateur.getRole());
        ps.setInt(2, utilisateur.getId());
        ps.executeUpdate();
    }
    
    public void updateUtilisateur(Utilisateur utilisateur) throws SQLException{
        PreparedStatement s = laConnexionMySQL.preparedStatement("UPDATE UTILISATEUR SET pseudout = ?, emailut = ?, mdput = ? where idut = ?");
        s.setString(1, utilisateur.getPseudo());
        s.setString(2, utilisateur.getEmail());
        s.setString(3, utilisateur.getMotDePasse());
        s.setInt(4, utilisateur.getId());
        s.executeUpdate();
    }
}