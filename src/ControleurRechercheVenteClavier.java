import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurRechercheVenteClavier implements EventHandler<KeyEvent> {
    private VueAdminGestionUtilisateurs parent;

    public ControleurRechercheVenteClavier(VueAdminGestionUtilisateurs parent) {
        this.parent = parent;
    }

    @Override
    public void handle(KeyEvent event) {
        TextField recherche = (TextField) event.getTarget();
        try {
            this.parent.setListeVentes(this.parent.getTouteLesVentes().recherche(recherche.getText()));
            this.parent.majDesVentes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
