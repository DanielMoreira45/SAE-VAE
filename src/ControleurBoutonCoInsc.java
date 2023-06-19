import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurBoutonCoInsc implements EventHandler<ActionEvent>{
    
    // La vue de l'application
    private FenetreCoInsc vue;

    public ControleurBoutonCoInsc(FenetreCoInsc vue){
        this.vue = vue;
    }

    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * @param actionEvent l'événement action
     */
	@Override
	public void handle(ActionEvent actionEvent) {
        Button bouton = (Button) actionEvent.getTarget();
        if (bouton.getText().equals("Se connectez")){
            this.vue.modeLogin();
        }
        else{
            this.vue.modeCreationCompte();
        }
	}

}