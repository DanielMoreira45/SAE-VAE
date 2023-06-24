import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ControleurMdpOublie implements EventHandler<ActionEvent> {

    /**
     * La vue FenetreCoInsc
     */
    private FenetreDeLogin vue;

    /**
     */

    public ControleurMdpOublie(FenetreDeLogin vue) {
        this.vue = vue;
    }
    /**
     * L'action consiste à changer de fenêtre pour aller à la page de connexion
     * 
     * @param actionEvent l'événement action
     */
    @Override
    public void handle(ActionEvent actionEvent) {
        this.vue.ajouteMdpOublie();
    }
}