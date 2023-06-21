import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class ControleurRechercheEntrer implements EventHandler<KeyEvent> {
    private AppliVae appliVae;
    private NavBar navBar;

    public ControleurRechercheEntrer(AppliVae appli, NavBar navBar) {
        this.appliVae = appli;
        this.navBar = navBar;
    }

    @Override
    public void handle(KeyEvent event) {
        try {
            this.appliVae.getPageAccueil().setLesVentes(this.appliVae.getPageAccueil().getTouteLesVentes().recherche(this.navBar.getTextFieldRecherche().getText()));
            this.appliVae.getPageAccueil().majAffichage();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
