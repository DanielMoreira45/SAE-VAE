import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    public void insereVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("INSERT INTO VENTE VALUES (?,?,?,?,?,?,?)");
        s.setInt(1, v.getIDVente());
        s.setDouble(2, v.getPrixBase());
        s.setDouble(3, v.getPrixMin());
        s.setTimestamp(4, new Timestamp(v.getDebutVente()));
        s.setTimestamp(5, new Timestamp(v.getFinVente()));
        s.setInt(6, v.getObjet().getidObjet());
        s.setInt(7, v.getStatus());
        s.executeQuery();
    }

    public void supprimeVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("DELETE FROM VENTE WHERE idve = ?, idob = ?, idst = ?");
        s.setInt(1, v.getIDVente());
        s.setInt(2, v.getObjet().getidObjet());
        s.setInt(3, v.getStatus());
        s.executeQuery();
    }

    public List<Vente> venteParCategorie(int categorie) throws SQLException, ParseException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE idcat = ? order by idob, idve,idut, idst");
        s.setInt(1, categorie);
        ResultSet rs = s.executeQuery();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idob = rs.getInt(1);
            int idve = rs.getInt(2);
            int idut = rs.getInt(3);
            int idst = rs.getInt(4);
            Double prixbase = rs.getDouble(5);
            Double prixmin = rs.getDouble(6);
            
            Timestamp debutVe = new Timestamp(rs.getDate(7).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), inputFormatter);
            String debutVeString = dateTimeDebut.format(outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(8).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), inputFormatter);
            String finVeString = dateTimeFin.format(outputFormatter);
            String nomob = rs.getString(9);
            String descob = rs.getString(10);

            Statement s2 = this.laConnexionMySQL.createStatement();
            ResultSet rs2 = s2
                    .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
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
            Utilisateur newUtili = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
            Objet newObj = new Objet(idob, descob, nomob, null, newUtili, categorie);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst,
            newObj);
            newObj.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public List<Vente> touteLesVentes() throws SQLException, ParseException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "SELECT idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET order by idcat, idob, idve, idut, idst;");
        List<Vente> ventes = new ArrayList<Vente>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
        while (rs.next()) {
            int idcat = rs.getInt(1);
            int idob = rs.getInt(2);
            int idve = rs.getInt(3);
            int idut = rs.getInt(4);
            int idst = rs.getInt(5);
            Double prixbase = rs.getDouble(6);
            Double prixmin = rs.getDouble(7);
            
            Timestamp debutVe = new Timestamp(rs.getDate(8).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), inputFormatter);
            String debutVeString = dateTimeDebut.format(outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(9).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), inputFormatter);
            String finVeString = dateTimeFin.format(outputFormatter);

            String nomob = rs.getString(10);
            String descob = rs.getString(11);

            Statement s2 = this.laConnexionMySQL.createStatement();
            ResultSet rs2 = s2
                    .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
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
            Utilisateur newUtili = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
            Objet newObj = new Objet(idob, descob, nomob, null, newUtili, idcat);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst,newObj);
            newObj.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public List<Vente> venteParStatus(int status) throws SQLException, ParseException {
        // SELECT idve, prixbase, prixmin, debutve, finve, idob FROM VENTE WHERE idst =
        // 4 order by idve;
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT idve, prixbase, prixmin, debutve, finve, idob FROM VENTE WHERE idst = ? order by idve");
        s.setInt(1, status);
        ResultSet rs = s.executeQuery();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idve = rs.getInt(1);
            Double prixbase = rs.getDouble(2);
            Double prixmin = rs.getDouble(3);

            Timestamp debutVe = new Timestamp(rs.getDate(4).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), inputFormatter);
            String debutVeString = dateTimeDebut.format(outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(5).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), inputFormatter);
            String finVeString = dateTimeFin.format(outputFormatter);
            int idob = rs.getInt(6);

            Statement s2 = this.laConnexionMySQL.createStatement();
            ResultSet rs2 = s2
                    .executeQuery("SELECT idob, nomob, descriptionob, idut, idcat FROM OBJET WHERE idob =" + idob);
            rs2.next();
            String nomob = rs2.getString(2);
            String descob = rs2.getString(3);
            int idut = rs2.getInt(4);
            int idcat = rs2.getInt(5);

            Statement s3 = this.laConnexionMySQL.createStatement();
            ResultSet rs3 = s3
                    .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
            rs3.next();
            String pseudo = rs3.getString(1);
            String email = rs3.getString(2);
            String mdp = rs3.getString(3);
            String active = rs3.getString(4);
            boolean actif = false;
            if (active.equals("O")) {
                actif = true;
            }
            int idRole = rs3.getInt(5);

            Utilisateur newUtili = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
            Objet newObj = new Objet(idob, descob, nomob, null, newUtili, idcat);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, status,
            newObj);
            newObj.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public int nombreVenteParStatus(int status) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT count(idst) FROM VENTE WHERE idst = ? group by idst");
        s.setInt(1, status);
        ResultSet rs = s.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return 0;
    }

    public int nombreVentreParCategorie(int categorie) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT count(idcat) FROM VENTE NATURAL JOIN OBJET WHERE idcat = ? group by idcat");
        s.setInt(1, categorie);
        ResultSet rs = s.executeQuery();
        if (rs.next()) return rs.getInt(1);
        return 0;
    }

    public List<Vente> recherche(String text) throws SQLException, ParseException {
        // PreparedStatement s = this.laConnexionMySQL.preparedStatement(
        //     "SELECT idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE nomob LIKE '%"+text+"%' order by idcat, idob, idve, idut, idst;");
        // s.setInt(1, text);
        // ResultSet rs = s.executeQuery();
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "SELECT idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE nomob LIKE '%"+text+"%' order by idcat, idob, idve, idut, idst;");
        List<Vente> ventes = new ArrayList<Vente>();
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
        while (rs.next()) {
            int idcat = rs.getInt(1);
            int idob = rs.getInt(2);
            int idve = rs.getInt(3);
            int idut = rs.getInt(4);
            int idst = rs.getInt(5);
            Double prixbase = rs.getDouble(6);
            Double prixmin = rs.getDouble(7);
            
            Timestamp debutVe = new Timestamp(rs.getDate(8).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), inputFormatter);
            String debutVeString = dateTimeDebut.format(outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(9).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), inputFormatter);
            String finVeString = dateTimeFin.format(outputFormatter);

            String nomob = rs.getString(10);
            String descob = rs.getString(11);

            Statement s2 = this.laConnexionMySQL.createStatement();
            ResultSet rs2 = s2
                    .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idut);
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
            Utilisateur newUtili = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
            Objet newObj = new Objet(idob, descob, nomob, null, newUtili, idcat);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst,newObj);
            newObj.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

}