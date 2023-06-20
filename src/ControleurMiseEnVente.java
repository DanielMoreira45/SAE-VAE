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

        ObjetBD objetBD = new ObjetBD(laConnexionMySQL);
        try {
            this.idLibre = objetBD.idLibre();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Button bouton = (Button) actionEvent.getTarget();
        if (bouton.getText().equals("Ajoutez des photos")) {
            PhotoBD photoBd = new PhotoBD(laConnexionMySQL);
            try {
                vue.ajoutImage();
                Photo photo = new Photo(photoBd.maxIdPhoto() + 1, vue.getTitre(), vue.getImageView());
                vue.ajouteUnePhoto(photo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (bouton.getText().equals("Ajouter le produit > ")) {
                if (prixMin != null && prixMax != null && cat != null && dateFin != null && dateDeBut != null) {
                    try {
                        Objet obj = new Objet(objetBD.idLibre(), desc, titre, lesPhotos, vue.getVendeur(), 1);
                        objetBD.insereObjet(obj);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
