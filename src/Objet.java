import java.util.*;

import javax.swing.text.html.ImageView;

/**
 * 
 */
public class Objet {

    /**
     * L'ID de l'objet
     */
    private Double idOb;

    /**
     * La description de l'objet
     */
    private String description;

    /**
     * Le nom de l'objet
     */
    private String nomOb;

    /**
     * L'image de l'objet
     */
    public ImageView img;

    /**
     * La vente associée à l'objet
     */
    private Vente vente;

    /**
     * La catégorie de l'objet
     */
    private Categorie categorie;

    /**
     * L'utilisateur qui met l'objet en vente
     */
    private Utilisateur vendeur;



    /**
     * Constructeur
     */
    public Objet(Double idOb, String description, String nomOb, ImageView img) {
        this.idOb = idOb;
        this.description = description;
        this.nomOb = nomOb;
        this.img = img;
    }

    /**
     * Getter idOb
     * @return (Double) idOb
     */
    public Double getidObjet() {
        return this.idOb;
    }

    /**
     * Setter idOb
     * @param idObjet 
     */
    public void setidObjet(Double idObjet) {
        this.idOb = idObjet;
    }

    /**
     * Getter description
     * @return (String) description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter description
     * @param nouvelleDescription
     */
    public void setDescription(String nouvelleDescription) {
        this.description = nouvelleDescription;
    }

    /**
     * Getter nomOb
     * @return (String) nomOb
     */
    public String getNomObjet() {
        return this.nomOb;
    }

    /**
     * Setter nomOb
     * @param nouveauNomObjet 
     */
    public void setNomObjet(String nouveauNomObjet) {
        this.nomOb = nouveauNomObjet;
    }

    /**
     * Getter categorie
     * @return (Categorie) categorie
     */
    public Categorie getCategorie() {
        return this.categorie;
    }

    /**
     * Setter categorie
     * @param nouvelleCategorie 
     */
    public void SetCategorie(Categorie nouvelleCategorie) {
        this.categorie = categorie;
    }

    /**
     * Getter img
     * @return (ImageView) img
     */
    public ImageView getImage() {
        return this.img;
    }

    /**
     * Setter img
     * @param nouvelleImage 
     */
    public void setImage(ImageView nouvelleImage) {
        this.img = nouvelleImage;
    }

}
