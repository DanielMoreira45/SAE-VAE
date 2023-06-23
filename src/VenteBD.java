import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * La classe Vente qui est en lien avec la base de données
 */
public class VenteBD {
    /** La connextion a la base de données */
    private ConnexionMySQL laConnexionMySQL;
    /** Un Formateur avec la forme "yyyy-MM-dd HH:mm:ss.S" */
    private DateTimeFormatter inputFormatter;
    /** Un formateur avec la forme "dd/MM/yy:HH/mm/ss" */
    private DateTimeFormatter outputFormatter;

    /**
     * Constructeur de la classe
     * 
     * @param laConnexionMySQL La connexion a la base de donnes
     */
    public VenteBD(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
        this.inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
        this.outputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd:hh/mm/ss");
    }

    /**
     * Permet d'ajoute une vente a la base de données
     * 
     * @param v La vente a ajouté
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
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

    /**
     * Permet de supprimer une vente de la base de données
     * 
     * @param v La vente a supprimer
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
    public void supprimeVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL
                .preparedStatement("DELETE FROM VENTE WHERE idve = ?, idob = ?, idst = ?");
        s.setInt(1, v.getIDVente());
        s.setInt(2, v.getObjet().getidObjet());
        s.setInt(3, v.getStatus());
        s.executeQuery();
    }

    /**
     * Permet de crée une liste de vente par rapport a une categorie
     * 
     * @param categorie la categorie
     * @return La liste de vente
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
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

    /**
     * Permet d'avoir toutes les ventes de la base de données
     * 
     * @return La liste de vente avec toutes les ventes de la base de données
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
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

    /**
     * Permet d'avoir la liste de vente avec un certain Status
     * 
     * @param status le status
     * @return la liste de vente
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
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

    /**
     * Permet d'avoir le nombre de vente dans un certain status
     * 
     * @param status le status
     * @return le nombre de vente avec ce status
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
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

    /**
     * Permet d'avoir le nombre de vente dans une certaine categorie
     * 
     * @param categorie la categorie
     * @return le nombre de vente
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
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

    /**
     * Permet d'avoir la vente qui a un certain idve
     * 
     * @param idve l'idve
     * @return La vente avec cette idve
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
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

    /**
     * Permet d'avoir le idve le plus grand dans la base de données
     * 
     * @return l'idve le plus grand
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
    public int maxIdVe() throws SQLException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(idve) FROM VENTE");
        rs.next();
        return rs.getInt(1);
    }

    public List<Vente> recherche(String text) throws SQLException, ParseException {
        // PreparedStatement s = this.laConnexionMySQL.preparedStatement(
        // "SELECT
        // idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob
        // FROM VENTE NATURAL JOIN OBJET WHERE nomob LIKE '%"+text+"%' order by idcat,
        // idob, idve, idut, idst;");
        // s.setInt(1, text);
        // ResultSet rs = s.executeQuery();
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "SELECT idcat,idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE nomob LIKE '%"
                        + text + "%' order by idcat, idob, idve, idut, idst;");
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
            Vente newVente = new Vente(idve, prixbase, prixmin, debutVeString, finVeString, idst, newObj);
            newObj.setVente(newVente);
            ventes.add(newVente);
        }
        return ventes;
    }

    /**
     * Permet d'avoir les ventes qui n'ont pas d'enchere
     * 
     * @return la liste de vente
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> venteSansEnchere() throws SQLException, ParseException {
        Statement s = this.laConnexionMySQL.createStatement();
        ObjetBD obBD = new ObjetBD(laConnexionMySQL);
        ResultSet rs = s.executeQuery(
                "select idve, prixbase, prixmin, debutve, finve, idob, idst from VENTE where not Exists (select * from ENCHERIR where VENTE.idVe = ENCHERIR.idVe)");
        List<Vente> liste = new ArrayList<Vente>();
        while (rs.next()) {
            int idve = rs.getInt(1);
            Double prixBase = rs.getDouble(2);
            Double prixmin = rs.getDouble(3);
            Timestamp debutVe = new Timestamp(rs.getDate(4).getTime());
            LocalDateTime dateTimeDebut = LocalDateTime.parse(debutVe.toString(), this.inputFormatter);
            String debutVeString = dateTimeDebut.format(this.outputFormatter);
            Timestamp finVe = new Timestamp(rs.getDate(5).getTime());
            LocalDateTime dateTimeFin = LocalDateTime.parse(finVe.toString(), this.inputFormatter);
            String finVeString = dateTimeFin.format(this.outputFormatter);
            int idob = rs.getInt(6);
            int idst = rs.getInt(7);
            Objet ob = obBD.objetParId(idob);
            Vente vente = new Vente(idve, prixBase, prixmin, debutVeString, finVeString, idst, ob);
            liste.add(vente);
        }
        return liste;

    }

    public List<Vente> ventePourAcheteur(int idut) throws SQLException, ParseException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "select idve, max(montant) from VENTE natural join ENCHERIR where idut = " + idut + " group by idve");
        List<Vente> ventes = new ArrayList<Vente>();
        if (rs.next()) {
            int idve = rs.getInt(1);
            Vente vente = this.venteParId(idve);
            ventes.add(vente);
        }
        return ventes;
    }
    


    public List<Vente> ventePrixMinMaxList(Double prixMin, Double prixMax) throws SQLException, ParseException {
        Statement s = this.laConnexionMySQL.createStatement();
        ResultSet rs = s.executeQuery(
                "select idve, max(montant) montant from VENTE natural join ENCHERIR montant group by idve having "+prixMin+" < montant and "+prixMax+ " > montant");
                System.out.println("select idve, max(montant) montant from VENTE natural join ENCHERIR montant group by idve having "+prixMin+" < montant and "+prixMax+ " > montant");
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()) {
            int idve = rs.getInt(1);
            Vente vente = this.venteParId(idve);
            ventes.add(vente);
        }
        return ventes;
    }
}
