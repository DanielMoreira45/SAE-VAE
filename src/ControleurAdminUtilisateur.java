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
        Utilisateur utilisateur = this.caseProfil.getUtilisateur();
        try {
            switch (bouton.getText()) {
                case "Supprimer":
                    this.vue.getToutLesUtilisateurs().supprimeUnUtilisateur(utilisateur.getId());
                    this.vue.removeUtilisateur(caseProfil.getUtilisateur());
                    break;
                case "Désactiver":
                    utilisateur.setActive(false);
                    this.vue.getToutLesUtilisateurs().setActif(utilisateur);
                    break;
                case "Activer":
                    utilisateur.setActive(true);
                    this.vue.getToutLesUtilisateurs().setActif(utilisateur);
                    break;
                case "Changer de rôles":
                    utilisateur.setRole(utilisateur.getRole() == 1 ? 2 : 1);
                    this.vue.getToutLesUtilisateurs().setRole(utilisateur);
                    break;
            
                default:
                    break;
            }
            this.vue.majDesProfils();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
