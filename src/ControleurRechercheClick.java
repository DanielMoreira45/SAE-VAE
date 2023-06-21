import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class ControleurRechercheClick implements EventHandler<MouseEvent> {
    private AppliVae appliVae;
    private NavBar navBar;

    public ControleurRechercheClick(AppliVae appli, NavBar navBar) {
        this.appliVae = appli;
        this.navBar = navBar;
    }

    @Override
    public void handle(MouseEvent event) {
        try {
            this.appliVae.getPageAccueil().setLesVentes(this.appliVae.getPageAccueil().getTouteLesVentes().recherche(this.navBar.getTextFieldRecherche().getText()));
            this.appliVae.getPageAccueil().majAffichage();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
