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
    private DateTimeFormatter inputFormatter;
    private DateTimeFormatter outputFormatter;

    /**
     * Default constructor
     */
    public VenteBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
        this.inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        this.outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
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
        ObjetBD obBD = new ObjetBD(laConnexionMySQL);

        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE idcat = ? order by idob, idve,idut, idst");
        s.setInt(1, categorie);
        ResultSet rs = s.executeQuery();

        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idob = rs.getInt(1);
            int idve = rs.getInt(2);
            int idst = rs.getInt(4);
            Double prixbase = rs.getDouble(5);
            Double prixmin = rs.getDouble(6);

            Timestamp debutVe = new Timestamp(rs.getDate(7).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), this.inputFormatter);
            String debutVeString = dateTimeDebut.format(this.outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(8).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), this.inputFormatter);
            String finVeString = dateTimeFin.format(this.outputFormatter);

            Objet ob = obBD.objetParId(idob);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst,
                    ob);
            ob.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public List<Vente> touteLesVentes() throws SQLException, ParseException {
        ObjetBD obBD = new ObjetBD(laConnexionMySQL);

        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "SELECT idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET order by idcat, idob, idve, idut, idst;");
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idob = rs.getInt(2);
            int idve = rs.getInt(3);
            int idst = rs.getInt(5);
            Double prixbase = rs.getDouble(6);
            Double prixmin = rs.getDouble(7);

            Timestamp debutVe = new Timestamp(rs.getDate(8).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), this.inputFormatter);
            String debutVeString = dateTimeDebut.format(this.outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(9).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), this.inputFormatter);
            String finVeString = dateTimeFin.format(this.outputFormatter);

            Objet ob = obBD.objetParId(idob);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst, ob);
            ob.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public List<Vente> venteParStatus(int status) throws SQLException, ParseException {
        ObjetBD obBD = new ObjetBD(laConnexionMySQL);

        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT idve, prixbase, prixmin, debutve, finve, idob FROM VENTE WHERE idst = ? order by idve");
        s.setInt(1, status);
        ResultSet rs = s.executeQuery();
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idve = rs.getInt(1);
            Double prixbase = rs.getDouble(2);
            Double prixmin = rs.getDouble(3);

            Timestamp debutVe = new Timestamp(rs.getDate(4).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), this.inputFormatter);
            String debutVeString = dateTimeDebut.format(this.outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(5).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), this.inputFormatter);
            String finVeString = dateTimeFin.format(this.outputFormatter);
            int idob = rs.getInt(6);

            Objet ob = obBD.objetParId(idob);
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, status, ob);
            ob.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    public int nombreVenteParStatus(int status) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT count(idst) FROM VENTE WHERE idst = ? group by idst");
        s.setInt(1, status);
        ResultSet rs = s.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public int nombreVentreParCategorie(int categorie) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement(
                "SELECT count(idcat) FROM VENTE NATURAL JOIN OBJET WHERE idcat = ? group by idcat");
        s.setInt(1, categorie);
        ResultSet rs = s.executeQuery();
        if (rs.next()) {
            return rs.getInt(1);
        }
        return 0;
    }

    public Vente venteParId(int idve) throws SQLException, ParseException {
        ObjetBD obBD = new ObjetBD(laConnexionMySQL);
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s
                .executeQuery("SELECT prixbase, prixmin, debutve, finve, idob, idst FROM VENTE WHERE idve = " + idve);
        rs.next();
        Double prixbase = rs.getDouble(1);
        Double prixmin = rs.getDouble(2);

        Timestamp debutVe = new Timestamp(rs.getDate(3).getTime());
        LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), this.inputFormatter);
        String debutVeString = dateTimeDebut.format(this.outputFormatter);
        Timestamp finVe = new Timestamp(rs.getDate(4).getTime());
        LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), this.inputFormatter);
        String finVeString = dateTimeFin.format(this.outputFormatter);
        int idob = rs.getInt(5);
        int idst = rs.getInt(6);
        Objet ob = obBD.objetParId(idob);
        Vente ven = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst, ob);
        ob.setVente(ven);
        return ven;
    }
}