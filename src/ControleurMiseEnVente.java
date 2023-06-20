import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurMiseEnVente implements EventHandler<ActionEvent>{
    
    // La vue de l'application
    private VueVente vue;
    private ConnexionMySQL laConnexionMySQL;

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
        Button bouton = (Button) actionEvent.getTarget();
        if (bouton.getText().equals("Ajoutez des photos")){
            vue.ajoutImage();
        }
        if (bouton.getText().equals("Categorie")){
            vue.ajoutImage();
        }
	}

}