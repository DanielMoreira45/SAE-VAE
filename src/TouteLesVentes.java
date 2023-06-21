import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * Classe qui permet de travailler avec tout les ventes de la base
 */
public class TouteLesVentes {
    private ConnexionMySQL laConnexionMySQL;
    private VenteBD venteBD;

    /**
     * Constructeur de base
     * 
     * @param laConnexionMySQL
     */
    public TouteLesVentes(ConnexionMySQL laConnexionMySQL) {
        this.laConnexionMySQL = laConnexionMySQL;
        this.venteBD = new VenteBD(this.laConnexionMySQL);
    }

    /**
     * Permet d'avoir toute les Ventes de la base de donnée
     * 
     * @return Une liste de vente
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> toutVente() throws SQLException, ParseException {
        return venteBD.touteLesVentes();
    }

    /**
     * Permet d'avoir le nombre de vente par status
     * 
     * @param leStatus le status voulu
     * @return la valeur en int
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
    public int getNombreVenteParStatus(int leStatus) throws SQLException {
        return venteBD.nombreVenteParStatus(leStatus);
    }

    /**
     * Permet d'avoir le nombred de vente dans un categorie
     * 
     * @param categorie la categorie voulu
     * @return la valeur en int
     * @throws SQLException Si il y a un probleme avec l'execution des lignes sql
     */
    public int getNombreVenteParCategorie(int categorie) throws SQLException {
        return venteBD.nombreVentreParCategorie(categorie);
    }

    /**
     * Permet d'avoir la liste de vente avec un status particulier
     * 
     * @param leStatus le status particulier
     * @return la liste de vente avec ce status
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> trieVenteParStatus(int leStatus) throws SQLException, ParseException {
        return venteBD.venteParStatus(leStatus);
    }

    /**
     * Permet d'avoir la liste de vente dans une categorie particuliere
     * 
     * @param categorie la categorie particuliere
     * @return la liste de vente dans cette categorie
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> trieVenteParCategorie(int categorie) throws SQLException, ParseException {
        return venteBD.venteParCategorie(categorie);
    }

    /**
     * Permet de faire la liste des ventes trier par le nom des objets
     * 
     * @return la liste de vente trier par le nom de l'objet
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> trieVenteParNomObjet() throws SQLException, ParseException {
        List<Vente> liste = venteBD.touteLesVentes();
        Collections.sort(liste, new ComparatorVenteNom());
        return liste;
    }

    /**
     * Permet de faire la liste des ventes trier par la date de fin
     * 
     * @return la liste de vente trier par la date de fin la plus proche
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> trieVenteParDate() throws SQLException, ParseException {
        List<Vente> liste = venteBD.touteLesVentes();
        Collections.sort(liste, new ComparatorVenteDateFin());
        return liste;
    }
}