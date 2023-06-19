import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 */
public class Vente {


    /**
     * ID de la vente
     */
    private int idVente;

    /**
     * Prix de base de la vente
     */
    private Double prixBase;

    /**
     * Prix minimum de la vente 
     */
    private Double prixMin;

    /**
     * Date de début de la vente
     */
    private Date debutVe;

    /**
     * Date de fin de la vente
     */
    private Date finVe;

    /**
     * Status de la vente
     */
    private int status;

    /**
     * Objet de la vente
     */
    private Objet objetVente;

    /**
     * Les enchères de la vente
     */
    private List<Enchere> encheres;



    /**
     * Default constructor
     * @throws ParseException
     */
    public Vente(int idVente, Double prixBase, Double prixMin, String debutVe, String finVe, int status, Objet objetVente) throws ParseException {
        SimpleDateFormat lecteur = new SimpleDateFormat("dd/MM/yy:hh/mm/ss");
        this.idVente = idVente;
        this.prixBase = prixBase;
        this.prixMin = prixMin;
        this.debutVe = lecteur.parse(debutVe);
        this.finVe = lecteur.parse(finVe);
        this.status = status;
        this.objetVente = objetVente;
        this.encheres = new ArrayList<>();
    }

    /**
     * Getter id
     * @return (Double) id
     */
    public int getIDVente() {
        return this.idVente;
    }

    /**
     * Setter id
     * @param nouveauID 
     */
    public void setIDVente(int nouveauID) {
        this.idVente = nouveauID;
    }

    /**
     * Getter prixBase
     * @return (Double) prixBase
     */
    public Double getPrixBase() {
        return this.prixBase;
    }

    /**
     * Setter prixBase
     * @param nouveauPrixBase 
     */
    public void setPrixBase(Double nouveauPrixBase) {
        this.prixBase = nouveauPrixBase;
    }

    /**
     * Getter prixMin
     * @return (Double) prixMin
     */
    public Double getPrixMin() {
        return this.prixMin;
    }

    /**
     * Setter prixMin
     * @param nouveauPrixMininum 
     */
    public void setPrixMin(Double nouveauPrixMininum) {
        this.prixMin = nouveauPrixMininum;
    }

    /**
     * Getter debutVente
     * @return (String) debutVe
     */
    public Long getDebutVente() {
        return this.debutVe.getTime();
    }

    /**
     * Setter debutVente
     * @param nouveauDebutVente 
     * @throws ParseException
     */
    public void setDebutVente(String nouveauDebutVente) throws ParseException {
        SimpleDateFormat lecteur = new SimpleDateFormat("dd/MM/yy:hh/mm/ss");
        this.debutVe = lecteur.parse(nouveauDebutVente);
    }

    /**
     * Getter finVe
     * @return (String) finVe
     */
    public Long getFinVente() {
        return this.finVe.getTime();
    }

    /**
     * Setter finVe
     * @param nouveauFinVente 
     * @throws ParseException
     */
    public void setFinVente(String nouveauFinVente) throws ParseException {
        SimpleDateFormat lecteur = new SimpleDateFormat("dd/MM/yy:hh/mm/ss");
        this.finVe = lecteur.parse(nouveauFinVente);
    }

    /**
     * Setter status
     * @param nouveauStatus 
     */
    public void changeStatus(int nouveauStatus) {
        this.status = nouveauStatus;
    }

    /**
     * Getter status
     * @return (int) status
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Renvoie le prix final à la fin de la vente
     * @return (Double) le prix final de la vente
     * @throws ParseException
     */
    public Double prixFinal() throws ExceptionVentePasTerminee, ParseException{
        if (Calendar.getInstance().getTime().before(this.finVe)){
            throw new ExceptionVentePasTerminee();
        }
        return this.encheres.get(this.encheres.size()-1).getMontant();
    }

    /**
     * Getter objetVente
     * @return (Objet) objetVente
     */
    public Objet getObjet() {
        return this.objetVente;
    }

    /**
     * Getter encheres
     * @return (List<Enchere>) encheres
     */
    public List<Enchere> getEncheres() {
        return this.encheres;
    }

    /**
     * Ajoute une enchère sur la vente
     * @param nouvelleEnchere 
     */
    public void ajouteEnchere(Enchere nouvelleEnchere) throws ExceptionPrixIncorrecte{
        if (nouvelleEnchere.getMontant() < this.prixMin || nouvelleEnchere.getMontant() < this.encheres.get(this.encheres.size()-1).getMontant()){
            throw new ExceptionPrixIncorrecte();
        }
        this.encheres.add(nouvelleEnchere);
    }
}
