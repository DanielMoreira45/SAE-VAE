import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe qui permet de travailler avec tout les ventes de la base
 */
public class TouteLesVentes {
    /** La connextion SQL */
    private ConnexionMySQL laConnexionMySQL;
    /** Une instance de venteBD */
    private VenteBD venteBD;

    /**
     * Constructeur de toute les ventes
     * 
     * @param laConnexionMySQL la connextion a la baase SQL
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
    public List<Vente> trieVenteParNomObjet(List<Vente> lesVentes) throws SQLException, ParseException {
        List<Vente> liste = new ArrayList<>(lesVentes);
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
    public List<Vente> trieVenteParDate(List<Vente> lesVentes) throws SQLException, ParseException {
        List<Vente> liste = new ArrayList<>(lesVentes);
        Collections.sort(liste, new ComparatorVenteDateFin());
        return liste;
    }

    /**
     * Permet de faire une liste de ventes qui n'ont pas encore d'enchere
     * 
     * @return la liste de vente qui n'a pas d'enchere
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> venteSansEnchere() throws SQLException, ParseException {
        return venteBD.venteSansEnchere();
    }

    /**
     * Permet de trouve une liste de vente via le nom de l'objet
     * 
     * @param un texte
     * @return la liste de vente qui dans le nom de l'objet contient le texte
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> recherche(String text) throws SQLException, ParseException {
        return venteBD.recherche(text);

    }

    /**
     * Permet de faire une liste de vente pour un Utilisateur, c'est l'enchere la
     * plus haute qui est pris en compte si il y a plusieurs enchere avec la même
     * idut et idve
     * 
     * @param idve L'id de l'utilisateur
     * @return La liste de vente
     * @throws SQLException   Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException Si il y a un probleme avec la date des ventes
     */
    public List<Vente> ventesPourUnAcheteur(int idve) throws SQLException, ParseException {
        return venteBD.ventePourAcheteur(idve);
    }


    public List<Vente> trieVenteIntervalle(String prixMin, String prixMax) throws SQLException, ParseException {
        return venteBD.ventePrixMinMaxList(Double.valueOf(prixMin), Double.valueOf(prixMax));
    }

    /**
     * Méthode permettant de rechercher une ou plusieurs ventes sur lesquelles un utilisateur aurait enchérit.
     * @param idve int : l'id de la personne
     * @param nom String : le nom de l'objet à rechercher.
     * @return List<Vente> : la liste des objets correspondant à la recherche.
     * @throws SQLException : Si il y a un probleme avec l'execution des lignes sql
     * @throws ParseException : Si il y a un probleme avec la date des ventes
     */
    public List<Vente> rechercheEnchereUtil(int idve, String nom) throws SQLException, ParseException {
        List<Vente> lesEncheresRecherchees = new ArrayList<>();
        for (Vente uneVente : this.ventesPourUnAcheteur(idve)) {
            if (uneVente.getObjet().getNomObjet().toUpperCase().contains(nom.toUpperCase())) {
                lesEncheresRecherchees.add(uneVente);
            }
        }
        return lesEncheresRecherchees;
    }
    
    public Double maxPrixEnchere(int idVente) throws SQLException {
        return this.venteBD.maxPrixEnchere(idVente);
    }

}