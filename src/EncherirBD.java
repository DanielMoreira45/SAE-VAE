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
public class EncherirBD {
    private ConnexionMySQL laConnexionMySQL;
    private DateTimeFormatter inputFormatter;
    private DateTimeFormatter outputFormatter;

    /**
     * Default constructor
     */
    public EncherirBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
        inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        outputFormatter =  DateTimeFormatter.ofPattern("yyyy/MM/dd:hh/mm/ss");
    }

    public void insereEnchere(Enchere e) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("INSERT INTO ENCHERIR VALUES (?,?,?,?)");
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

    public List<Enchere> EnchereParIdVeUt(int idve, int idut) throws SQLException, ExceptionEnchereExistePas, ParseException{
        UtilisateurBD utilBD = new UtilisateurBD(laConnexionMySQL);
        VenteBD venteBD = new VenteBD(laConnexionMySQL);
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("select dateheure, montant from ENCHERIR natural join VENTE where idve = ? and idut = ?");
        s.setInt(1, idve);
        s.setInt(2, idut);
        ResultSet rs = s.executeQuery();
        List<Enchere> liste = new ArrayList<Enchere>();

        while (rs.next()){
            Timestamp dateHeure = new Timestamp(rs.getDate(1).getTime());
            LocalDateTime dateTimeDateHeure = LocalDateTime.parse(dateHeure.toString(), inputFormatter);
            String dateHeureString = dateTimeDateHeure.format(outputFormatter);
            Double montant = rs.getDouble(2);

            Utilisateur util = utilBD.utilisateurParId(idut);
            Vente ven = venteBD.venteParId(idve);
            Enchere enchere = new Enchere(util, ven, montant, dateHeureString);
            liste.add(enchere);
        }
        if(liste.isEmpty()){
            throw new ExceptionEnchereExistePas();
        }
        return liste;
    }

    // public int EnchereParStatus(int prixBase, int prixMontant) throws SQLException{
    //     Statement s = this.laConnexionMySQL.createStatement();
    //     ResultSet rs = s.executeQuery("select count(*) from ENCHERIR NATURAL JOIN VENTE WHERE prixbase > "+prixMontant+  absd= "+status);
    //     if(rs.next()){
    //         return rs.getInt(1);
    //     }
    //     return 0;
    // }

}