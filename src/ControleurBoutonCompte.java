import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Contrôleur à activer lorsque l'on clique sur le bouton info
 */
public class ControleurBoutonCompte implements EventHandler<ActionEvent> {
    /** L'instance de la vue */
    private PageProfilUtilisateur vue;

    /**
     * Le contructeur Pour crée le controleur
     * 
     * @param vue La vue qui vas changer
     */
    public ControleurBoutonCompte(PageProfilUtilisateur vue) {
        this.vue = vue;
    }

    /**
     * Les actions qui va se faire
     * 
     * @param actionEvent l'event
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        Button bouton = (Button) (actionEvent.getSource());
        if (bouton.getText().contains("Informations Personnelles")) {
            this.vue.afficherInfoPerso();
        }
        if (bouton.getText().contains("Changer de Mot de Passe")) {
            this.vue.afficherChangerMdp();
        }
        if (bouton.getText().contains("Paiements")) {
            this.vue.afficherPaiements();
        }
    }
}
