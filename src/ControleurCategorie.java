import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurCategorie implements EventHandler<ActionEvent> {
    private AppliVae appli;

    public ControleurCategorie(AppliVae appli) {
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        ComboBox comboBox = (ComboBox) event.getTarget();
        try {
            switch ((String) comboBox.getSelectionModel().getSelectedItem()) {
                case "Vêtement": 
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.VETEMENT));
                    break;
                case "Chaussure":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.CHAUSSURE));
                    break;
                case "Accessoire":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.ACCESSOIRE));
                    break;
                case "Electromenager":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.ELECTROMENAGER));
                    break;
                case "Informatique":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.INFORMATIQUE));
                    break;
                case "Jeux":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.JEUX));
                    break;
                case "Livre":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.LIVRE));
                    break;
                case "Musique":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.MUSIQUE));
                    break;
                case "Sport":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.SPORT));
                    break;
                case "Vehicule":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.VEHICULE));
                    break;
                case "Ustensile Cuisine":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.USTENSILECUISINE));
                    break;
                case "Meuble":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.MEUBLE));
                    break;
                case "Outil":
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().trieVenteParCategorie(Categorie.OUTIL));
                    break;
                default:
                    this.appli.getPageAccueil().setLesVentes(this.appli.getPageAccueil().getTouteLesVentes().toutVente());
                    break;
            }
            this.appli.getPageAccueil().majAffichage();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
