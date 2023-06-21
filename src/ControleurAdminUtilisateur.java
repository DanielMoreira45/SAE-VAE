import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ControleurAdminUtilisateur implements EventHandler<ActionEvent> {
    private CaseProfil caseProfil;
    private VueAdminGestionUtilisateurs vue;

    public ControleurAdminUtilisateur(CaseProfil caseProfil, VueAdminGestionUtilisateurs vue) {
        this.caseProfil = caseProfil;
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        Button bouton = (Button) event.getTarget();
        try {
            switch (bouton.getText()) {
                case "Supprimer":
                    this.vue.getToutLesUtilisateurs().supprimeUnUtilisateur(this.caseProfil.getUtilisateur().getId());
                    this.vue.removeUtilisateur(caseProfil.getUtilisateur());
                    this.vue.majDesProfils();
                    break;
                case "Désactiver":
                    this.caseProfil.getUtilisateur().setActive(false);
                    this.vue.getToutLesUtilisateurs().setActif(this.caseProfil.getUtilisateur());
                    this.vue.majDesProfils();
                    break;
                case "Activer":
                    this.caseProfil.getUtilisateur().setActive(true);
                    this.vue.getToutLesUtilisateurs().setActif(this.caseProfil.getUtilisateur());
                    this.vue.majDesProfils();
                    break;
            
                default:
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
