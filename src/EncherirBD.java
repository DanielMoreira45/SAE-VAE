import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public void insereEnchere(Enchere e) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("INSERT INTO ENCHERIR VALUES (?,?,STR_TO_DATE(?,'%d/%m/%Y:%h:%i:%s'),?)");
        s.setInt(1, e.getEncherisseur().getId());
        s.setInt(2, e.getVente().getIDVente());
        s.setTimestamp(3, new Timestamp(e.getDateHeure()));
        s.setDouble(4, e.getMontant());
        s.executeQuery();
    }

    public void supprimeEnchere(Enchere e) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("DELETE FROM ENCHERIR WHERE idut = ?, idve = ?, montant = ?, dateheure = ?");
        s.setInt(1, e.getEncherisseur().getId());
        s.setDouble(2, e.getVente().getIDVente());
        s.setDouble(3, e.getMontant());
        s.setTimestamp(4, new Timestamp(e.getDateHeure()));
        s.executeQuery();
    }

    public Enchere meilleurEnchere(int idve) throws SQLException, ParseException {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy:HH/mm/ss");
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "select idve, idut, dateheure, montant, prixbase, prixmin, debutve, finve, idob, idst  from ENCHERIR natural join VENTE where idve = "
                        + idve + " and montant >= ALL (select montant FROM ENCHERIR natural join VENTE where idve ="
                        + idve + ")");
        rs.next();
        int idutA = rs.getInt(2);
        Timestamp dateHeure = new Timestamp(rs.getDate(3).getTime());
        LocalDateTime dateTimeDateHeure = LocalDateTime.parse(dateHeure.toString(), inputFormatter);
        String dateHeureString = dateTimeDateHeure.format(outputFormatter);
        Double montant = rs.getDouble(4);
        Double prixbase = rs.getDouble(5);
        Double prixmin = rs.getDouble(6);
        Timestamp debutVe = new Timestamp(rs.getDate(7).getTime());
        LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), inputFormatter);
        String debutVeString = dateTimeDebut.format(outputFormatter);
        Timestamp finVe = new Timestamp(rs.getDate(8).getTime());
        LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), inputFormatter);
        String finVeString = dateTimeFin.format(outputFormatter);
        int idob = rs.getInt(9);
        int idst = rs.getInt(10);

        Statement s2 = this.laConnexionMySQL.createStatement();
        ResultSet rs2 = s2.executeQuery("select nomob, descriptionob, idcat, idut from OBJET where idob = " + idob);

        String nomob = rs2.getString(1);
        String descob = rs2.getString(2);
        int idcat = rs2.getInt(3);
        int idutV = rs2.getInt(4);

        Statement s4 = this.laConnexionMySQL.createStatement();
        ResultSet rs4 = s4
                .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idutV);
        rs4.next();
        String pseudoV = rs4.getString(1);
        String emailV = rs4.getString(2);
        String mdpV = rs4.getString(3);
        String activeV = rs4.getString(4);
        boolean actifV = false;
        if (activeV.equals("O")) {
            actifV = true;
        }
        int idRoleV = rs4.getInt(5);


        Statement s3 = this.laConnexionMySQL.createStatement();
        ResultSet rs3 = s3
                .executeQuery("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut =" + idutA);
        rs3.next();
        String pseudoA = rs3.getString(1);
        String emailA = rs3.getString(2);
        String mdpA = rs3.getString(3);
        String activeA = rs3.getString(4);
        boolean actifA = false;
        if (activeA.equals("O")) {
            actifA = true;
        }
        int idRoleA = rs3.getInt(5);

        Utilisateur newUtiliAcheteur = new Utilisateur(idutA, pseudoA, emailA, mdpA, actifA, idRoleA);
        Utilisateur newUtiliVendeur = new Utilisateur(idRoleV, pseudoV, emailV, mdpV, actifV, idRoleV);
        Objet objet =  new Objet(idob, descob, nomob, null, newUtiliVendeur, idcat);
        Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst, objet);
        objet.setVente(newVente);
        return new Enchere(newUtiliAcheteur, newVente, montant, dateHeureString);
    }
}