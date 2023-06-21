import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurTrier implements EventHandler<ActionEvent> {
    private PageAccueil vue;
    private TouteLesVentes touteLesVentes;

    public ControleurTrier(PageAccueil vue, TouteLesVentes toutesLesVentes) {
        this.vue = vue;
        this.touteLesVentes = toutesLesVentes;
    }

    @Override
    public void handle(ActionEvent event) {
        Button bouton = (Button) event.getTarget();
        try {
            switch (bouton.getText()) {
                case "Tout":
                    this.vue.setLesVentes(this.touteLesVentes.toutVente());
                    break;
                case "Trier par date":
                    if (this.vue.getLesVentes().isEmpty()) this.vue.setLesVentes(this.touteLesVentes.trieVenteParNomObjet(this.vue.getTouteLesVentes().toutVente()));
                    else this.vue.setLesVentes(this.touteLesVentes.trieVenteParDate(this.vue.getLesVentes()));
                    break; 
                case "Trier par nom":
                    if (this.vue.getLesVentes().isEmpty()) this.vue.setLesVentes(this.touteLesVentes.trieVenteParNomObjet(this.vue.getTouteLesVentes().toutVente()));
                    else this.vue.setLesVentes(this.touteLesVentes.trieVenteParNomObjet(this.vue.getLesVentes()));
                    break;
                case "Enchères en cours":
                    this.vue.setLesVentes(this.touteLesVentes.trieVenteParStatus(Status.ENCOURS));
                    break;
                case "Enchères à venir":
                    this.vue.setLesVentes(this.touteLesVentes.trieVenteParStatus(Status.AVENIR));
                    break;
                case "Inverse":
                    this.vue.reverseLesVentes();
                    break;
            
                default:
                    break;
            }
            this.vue.majAffichage();
        } catch (SQLException | ParseException e) {
            e.printStackTrace();
        }
    }

}
