import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurAdminSwitchPage implements EventHandler<ActionEvent> {
    private VueAdminGestionUtilisateurs vue;

    public ControleurAdminSwitchPage(VueAdminGestionUtilisateurs vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        Button bouton = (Button) event.getTarget();
        switch (bouton.getText()) {
            case "Gestion des utilisateurs   >":
                this.vue.setPageUtilisateur();
                break;
            case "Gestion des ventes          >":
                this.vue.setPageVente();
                break;
        
            default:
                break;
        }
    }

}
