import java.text.DateFormat;
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
    private String debutVe;

    /**
     * Date de fin de la vente
     */
    private String  finVe;

    /**
     * Status de la vente
     */
    private Status status;

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
     */
    public Vente(int idVente, Double prixBase, Double prixMin, String debutVe, String finVe, Status status, Objet objetVente) {
        this.idVente = idVente;
        this.prixBase = prixBase;
        this.prixMin = prixMin;
        this.debutVe = debutVe;
        this.finVe = finVe;
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
    public String getDebutVente() {
        return this.debutVe;
    }

    /**
     * Setter debutVente
     * @param nouveauDebutVente 
     */
    public void setDebutVente(String nouveauDebutVente) {
        this.debutVe = nouveauDebutVente;
    }

    /**
     * Getter finVe
     * @return (String) finVe
     */
    public String getFinVente() {
        return this.finVe;
    }

    /**
     * Setter finVe
     * @param nouveauFinVente 
     */
    public void setFinVente(String nouveauFinVente) {
        this.finVe = nouveauFinVente;
    }

    /**
     * Setter status
     * @param nouveauStatus 
     */
    public void changeStatus(Status nouveauStatus) {
        this.status = nouveauStatus;
    }

    /**
     * Getter status
     * @return (Status) status
     */
    public Status getStatus() {
        return this.status;
    }

    // /**
    //  * Renvoie le prix final à la fin de la vente
    //  * @return (Double) le prix final de la vente
    //  */
    // public Double prixFinal() throws ExceptionVentePasTerminee{
    //     Date dateAjd = new SimpleDateFormat("").parse(this.finVe); // mettre le format de la date --------------------------------------------------------------------
    //     if (Calendar.getInstance().getTime().before(dateAjd)){
    //         throw new ExceptionVentePasTerminee();
    //     }
    //     return this.encheres.get(this.encheres.size()-1).getMontant();
    // }

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
    // public void ajouteEnchere(Enchere nouvelleEnchere) throws ExceptionPrixIncorrect{
    //     if (nouvelleEnchere.getMontant() < this.prixMin || nouvelleEnchere.getMontant() < this.encheres.get(this.encheres.size()-1).getMontant()){
    //         throw new ExceptionPrixIncorrect();
    //     }
    //     this.encheres.add(nouvelleEnchere);
    // }
}
