import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Contrôleur à activer lorsque l'on clique sur le bouton info
 */
public class ControleurBoutonCompte implements EventHandler<ActionEvent> {

    private FenetrePageUtilisateur p;
    /**
     * @param p vue du jeu
     */
    public ControleurBoutonCompte(FenetrePageUtilisateur p) {
        this.p = p;
    }

    /**
     * L'action consiste à afficher une fenêtre popup précisant les règles du jeu.
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {

        Button bouton = (Button) (actionEvent.getSource());

        if(bouton.getText().contains("Informations Personnelles")){
            this.p.afficherInfoPerso();
        }

        if(bouton.getText().contains("Changer de mot de passe")){
            this.p.afficherChangerMdp();
        }
        if(bouton.getText().contains("Paiment")){
            this.p.afficherPaiments();
        }
    }
}