import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Contrôleur à activer lorsque l'on clique sur le bouton info
 */
public class ControleurBoutonCompte implements EventHandler<ActionEvent> {

    private PageProfilUtilisateur vue;
    /**
     * @param p vue du jeu
     */
    public ControleurBoutonCompte(PageProfilUtilisateur vue, AppliVae appliVae, ConnexionMySQL connexionMySQL) {
        this.vue = vue;
    }

    /**
     * L'action consiste à afficher une fenêtre popup précisant les règles du jeu.
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        Button bouton = (Button) (actionEvent.getSource());

        if(bouton.getText().contains("Informations Personnelles")){
            this.vue.afficherInfoPerso();
        }

        if(bouton.getText().contains("Changer de Mot de Passe")){
            this.vue.afficherChangerMdp();
        }
        if(bouton.getText().contains("Paiements")){
            this.vue.afficherPaiements();
        }
    }
}
