import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

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
        s.setTimestamp(5,new Timestamp(v.getFinVente()));
        s.setInt(6, v.getObjet().getidObjet());
        s.setInt(7, v.getStatus());
        s.executeQuery();
    }
    public void supprimeVente(Vente v) throws SQLException {
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("DELETE FROM VENTE WHERE idve = ?, idob = ?, idst = ?");
        s.setInt(1, v.getIDVente());
        s.setInt(2, v.getObjet().getidObjet());
        s.setInt(3, v.getStatus());        
        s.executeQuery();
    }
    public void venteParCategorie(int categorie) throws SQLException{
        // SELECT idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE idcat = 1 order by idob, idve, idut, idst;
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("SELECT idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob,descriptionob FROM VENTE NATURAL JOIN OBJET WHERE idcat = ? order by idob, idve,idut, idst");
        s.setInt(1, categorie);
        ResultSet rs = s.executeQuery();
        // List<Vente>
        List<Vente> ventes = new ArrayList<Vente>();
        while (rs.next()){
            int idob = rs.getInt(1);
            int idve = rs.getInt(2);
            int idut = rs.getInt(3);
            int idst = rs.getInt(4);
            Double prixbase = rs.getDouble(5);
            Double prixmin = rs.getDouble(6);
            Date debutVe = new Date(rs.getDate(7).getTime());
            System.out.println("debutVe "+debutVe);
            Date finVe = new Date(rs.getDate(8).getTime());
            System.out.println("finVe "+finVe);
            String nomob = rs.getString(9);
            String descob = rs.getString(10);

            PreparedStatement s2= this.laConnexionMySQL.preparedStatement("SELECT pseudout,emailut,mdput,activeut,idrole FROM UTILISATEUR where idut = ?");
            s2.setInt(1, idut);
            ResultSet rs2 = s2.executeQuery();
            rs2.next();
            String pseudo = rs2.getString(1);
            String email = rs2.getString(2);
            String mdp = rs2.getString(3);
            String active = rs2.getString(4);
            System.out.println("active "+active);
            boolean actif = false;
            if (active.equals("O")){actif = true;}
            int idRole = rs2.getInt(5);
            Utilisateur newUtili = new Utilisateur(idut, pseudo, email, mdp, actif, idRole);
            Objet newObj = new Objet(idob, descob, nomob, null, null, categorie);
            // Vente newVente = new Vente(idve, prixbase, prixmin, debutVe, finVe, idst, newObj);
            System.out.println("il faut voir se que donne les Dates pour les mettre en String");
            // newObj.setVente(newVente);
        }
    }
    public void touteLesVentes() throws SQLException{
        PreparedStatement s = this.laConnexionMySQL.preparedStatement("SELECT idob,idve,idut,idst,prixbase,prixmin,debutve,finve,nomob FROM VENTE NATURAL JOIN OBJET");

    }
}