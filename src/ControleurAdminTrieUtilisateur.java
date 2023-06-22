import java.sql.SQLException;
import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;

public class ControleurAdminTrieUtilisateur implements EventHandler<ActionEvent> {
    private VueAdminGestionUtilisateurs vue;

    public ControleurAdminTrieUtilisateur(VueAdminGestionUtilisateurs vue) {
        this.vue = vue;
    }

    @Override
    public void handle(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getTarget();
        try {
            switch ((String) comboBox.getSelectionModel().getSelectedItem()) {
                case "Admin":
                    this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().toutAdmin());
                    break;
                case "Utilisateurs":
                    this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().toutUtilisateurs());
                    break;
                case "Actif":
                    this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().actif());
                    break;
                case "Inactif":
                    this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().inactif());
                    break;
            
                default:
                    this.vue.setListeUtilisateurs(this.vue.getToutLesUtilisateurs().tout());
                    break;
            }
            this.vue.majDesProfils();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
