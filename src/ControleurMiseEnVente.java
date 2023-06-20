import java.sql.SQLException;
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
        Integer prixMin = vue.getPrixMin();
        Integer prixMax = vue.getPrixMax();
        LocalDate dateDeBut = vue.dateDebut();
        LocalDate dateFin = vue.dateFin();
        String desc = vue.getDesc();
        List<Photo> lesPhotos = vue.getPhotos();
        String titre = vue.getTitre();
        System.out.println(prixMin);
        System.out.println(prixMax);
        System.out.println(cat);
        System.out.println(dateDeBut);
        System.out.println(dateFin);
        ObjetBD objetBD = new ObjetBD(laConnexionMySQL);
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
            System.out.println("rentre dans la condition");
            if (prixMin != null && prixMax != null && cat != null && dateFin != null && dateDeBut != null) {
                try {
                    Objet obj = new Objet(objetBD.maxIdObjet()+1, desc, titre, lesPhotos, vue.getVendeur(), 1);
                    objetBD.insereObjet(obj);
                    for (Photo photos : lesPhotos) {
                        photoBd.insertPhoto(photos, obj);
                    }
                    vue.popUpCompteConnecte(titre);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
