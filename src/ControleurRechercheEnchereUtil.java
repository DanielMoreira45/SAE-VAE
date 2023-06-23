import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ControleurRechercheEnchereUtil implements EventHandler<KeyEvent> {
    private VueEncheresUtilisateur fenetreEncheres;
    private TextField barreRecherche;

    public ControleurRechercheEnchereUtil(VueEncheresUtilisateur fenetre, TextField barreRecherche) {
        this.fenetreEncheres = fenetre;
        this.barreRecherche = barreRecherche;
    }

    @Override
    public void handle(KeyEvent event) {
        int idUtil = this.fenetreEncheres.getIdUtilisateur();
        String nom = barreRecherche.getText();
        List<Vente> lesVentes;
        try {
            if (nom.length() != 0) {
                lesVentes = this.fenetreEncheres.getToutesLesVentes().rechercheEnchereUtil(idUtil, nom);
            }
            else {
                lesVentes = this.fenetreEncheres.getToutesLesVentes().ventesPourUnAcheteur(idUtil);
            }
            this.fenetreEncheres.setLesVentes(lesVentes);
            this.fenetreEncheres.majLesArticles();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
