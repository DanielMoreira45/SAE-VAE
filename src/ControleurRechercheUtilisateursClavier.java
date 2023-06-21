import java.sql.SQLException;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurRechercheUtilisateursClavier implements EventHandler<KeyEvent> {
    private VueAdminGestionUtilisateurs vue;

    public ControleurRechercheUtilisateursClavier(VueAdminGestionUtilisateurs vue) {
        this.vue = vue;
    }

    @Override
    public void handle(KeyEvent event) {
        TextField recherche = (TextField) event.getTarget();
        try {
            this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().recherche(recherche.getText()));
            this.vue.majDesProfils();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
