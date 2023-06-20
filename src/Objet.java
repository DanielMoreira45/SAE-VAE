import javafx.scene.image.Image;

/**
 * 
 */
public class Objet {

    /**
     * L'ID de l'objet
     */
    private int idOb;

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
    public Image img;

    /**
     * La vente associée à l'objet
     */
    private Vente vente;

    /**
     * La catégorie de l'objet
     */
    private int categorie;

    /**
     * L'utilisateur qui met l'objet en vente
     */
    private Utilisateur vendeur;

    /**
     * Constructeur
     */
    public Objet(int idOb, String description, String nomOb, Image img, Vente vente, Utilisateur vendeur,
            int categorie) {
        this.idOb = idOb;
        this.description = description;
        this.nomOb = nomOb;
        this.img = img;
        this.vente = vente;
        this.categorie = categorie;
        this.vendeur = vendeur;
    }

    /**
     * Getter idOb
     * 
     * @return (Double) idOb
     */
    public int getidObjet() {
        return this.idOb;
    }

    /**
     * Setter idOb
     * 
     * @param idObjet
     */
    public void setidObjet(int idObjet) {
        this.idOb = idObjet;
    }

    /**
     * Getter description
     * 
     * @return (String) description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Setter description
     * 
     * @param nouvelleDescription
     */
    public void setDescription(String nouvelleDescription) {
        this.description = nouvelleDescription;
    }

    /**
     * Getter nomOb
     * 
     * @return (String) nomOb
     */
    public String getNomObjet() {
        return this.nomOb;
    }

    /**
     * Setter nomOb
     * 
     * @param nouveauNomObjet
     */
    public void setNomObjet(String nouveauNomObjet) {
        this.nomOb = nouveauNomObjet;
    }

    /**
     * Getter categorie
     * 
     * @return (int) categorie
     */
    public int getCategorie() {
        return this.categorie;
    }

    /**
     * Setter categorie
     * 
     * @param nouvelleCategorie
     */
    public void SetCategorie(int nouvelleCategorie) {
        this.categorie = nouvelleCategorie;
    }

    /**
     * Getter img
     * 
     * @return (Image) img
     */
    public Image getImage() {
        return this.img;
    }

    /**
     * Setter img
     * 
     * @param nouvelleImage
     */
    public void setImage(Image nouvelleImage) {
        this.img = nouvelleImage;
    }

    public Vente getVente() {
        return vente;
    }

    public void setVente(Vente vente) {
        this.vente = vente;
    }

    public Utilisateur getVendeur() {
        return vendeur;
    }

    public void setVendeur(Utilisateur vendeur) {
        this.vendeur = vendeur;
    }
}
