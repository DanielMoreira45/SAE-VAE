import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.EventHandler;
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
        try { // accède à partir de l'instance d'applieVAE passé en argument le page d'accueil pour placer les ventes à partir de l'instance de navBar pour 
        // récupérez les entrées clavier de l'utilisateur
            this.appliVae.getPageAccueil().setLesVentes(this.appliVae.getPageAccueil().getTouteLesVentes()
                    .recherche(this.navBar.getTextFieldRecherche().getText()));
            this.appliVae.getPageAccueil().majAffichage(); // met à jour
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
