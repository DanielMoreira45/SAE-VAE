import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurMiseEnVente implements EventHandler<ActionEvent> {

    // La vue de l'application
    private VueVente vue;
    private ConnexionMySQL laConnexionMySQL;
    Integer idLibre = null;
    PhotoBD photoBd;
    Objet obj = null;

    public ControleurMiseEnVente(VueVente vue, ConnexionMySQL laConnexionMySQL) {
        this.vue = vue;
        this.laConnexionMySQL = laConnexionMySQL;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        String cat = vue.getCategorie();
        String marque = vue.getMarque();
        Double prixMin = vue.getPrixMin();
        Double prixMax = vue.getPrixMax();
        String dateDeBut = vue.dateDebut().toString() + " 00:00:00"; // pour correspondre au String du modèle Vente
        String dateFin = vue.dateFin().toString()+ " 00:00:00"; // + pour correspondre au String du modèle Vente
        String desc = vue.getDesc();
        List<Photo> lesPhotos = vue.getPhotos();
        String titrePh = vue.getTitre();
        String titreOb = vue.titreVente();
        System.out.println(prixMin);
        System.out.println(prixMax);
        System.out.println(cat);
        System.out.println(dateDeBut);
        System.out.println(dateFin);
        ObjetBD objetBD = new ObjetBD(laConnexionMySQL);
        VenteBD venteDeOb= new VenteBD(laConnexionMySQL);

        try {
            this.idLibre = objetBD.maxIdObjet()+1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Button bouton = (Button) actionEvent.getTarget();
        System.out.println(bouton.getText());
        if (bouton.getText().equals("Ajoutez des photos")) {
            this.photoBd = new PhotoBD(laConnexionMySQL);
            System.out.println("Connexion PhotoBD");
            try {
                vue.ajoutImage();
                Photo photo = new Photo(photoBd.maxIdPhoto() + 1, vue.getTitre(), vue.getImageView());
                System.out.println("Creation instance photo");
                vue.ajouteUnePhoto(photo);
                System.out.println("photo ajout");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (bouton.getText().equals("Ajouter le produit > ")) {
            if (prixMin != null && prixMax != null && cat != null && dateFin != null && dateDeBut != null) {
                try {
                    this.obj = new Objet(objetBD.maxIdObjet()+1, desc, titreOb, lesPhotos, vue.getVendeur(), 1);
                    objetBD.insereObjet(obj);
                    for (Photo photos : lesPhotos) {
                        photoBd.insertPhoto(photos, obj);
                    }
                    vue.popUpCompteConnecte(titreOb);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try{
                    Vente vente = new Vente(venteDeOb.maxIdVente()+1, prixMin, prixMax, dateDeBut, dateFin, 1, this.obj);
                    venteDeOb.insereVente(vente);
                }
                catch(SQLException e){
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
