import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ControleurRechercheEnchereUtil implements EventHandler<MouseEvent> {
    private VueEncheresUtilisateur fenetreEncheres;
    private TextField barreRecherche;

    public ControleurRechercheEnchereUtil(VueEncheresUtilisateur fenetre, TextField barreRecherche) {
        this.fenetreEncheres = fenetre;
        this.barreRecherche = barreRecherche;
    }

    @Override
    public void handle(MouseEvent event) {

        // List<Vente> lesVentes = new ArrayList<>(this.fenetreEncheres.getLesVentes());
        // try {
        //     this.fenetreEncheres.setLesVentes(touteLesVentes.recherche(barreRecherche.getText()));
        //     this.fenetreEncheres.majAffichage();
        // } catch (SQLException | ParseException e) {
        //     e.printStackTrace();
        // }
    }

}
