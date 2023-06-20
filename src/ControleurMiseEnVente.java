import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurMiseEnVente implements EventHandler<ActionEvent>{
    
    // La vue de l'application
    private VueVente vue;
    private ConnexionMySQL laConnexionMySQL;
    Integer idLibre = null;

    public ControleurMiseEnVente(VueVente vue, ConnexionMySQL laConnexionMySQL){
        this.vue = vue;
        this.laConnexionMySQL = laConnexionMySQL;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * @param actionEvent l'événement action
     */
	@Override
	public void handle(ActionEvent actionEvent) {
        ObjetBD objetBD = new ObjetBD(laConnexionMySQL);
        try {
            this.idLibre = objetBD.idLibre();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Objet objetTempo = new Objet(idLibre, null, null, null, null, 0);
        Button bouton = (Button) actionEvent.getTarget();
        if (bouton.getText().equals("Ajoutez des photos")){
            PhotoBD photoBd = new PhotoBD(laConnexionMySQL);
            try {
                Photo photo = new Photo(photoBd.maxIdPhoto()+1, vue.getTitre(), vue.getImageView());
                photoBd.insertPhoto(photo, objetTempo);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            vue.ajoutImage();
        }
        if (bouton.getText().equals("Categorie")){
            // vue.ajoutImage();
        }
	}

}