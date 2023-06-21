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
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh/mm/ss");
        UtilisateurBD utilBD = new UtilisateurBD(laConnexionMySQL);
        VenteBD venBD = new VenteBD(laConnexionMySQL);

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
        Utilisateur newUtiliAcheteur = utilBD.utilisateurParId(idutA);
        Vente ve = venBD.venteParId(idve);
        return new Enchere(newUtiliAcheteur, ve, montant, dateHeureString);
    }
}