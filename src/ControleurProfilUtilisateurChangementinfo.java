import java.sql.SQLException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

/**
 * ControleurProfilUtilisateurChangementinfo
 */
public class ControleurProfilUtilisateurChangementinfo implements EventHandler<ActionEvent> {

    private PageProfilUtilisateur vue;
    private ConnexionMySQL connexion;
    private Utilisateur utilisateur;

    public ControleurProfilUtilisateurChangementinfo(PageProfilUtilisateur pageProfilUtilisateur,
            ConnexionMySQL connexionMySQL, Utilisateur utilisateur) {
        this.vue = pageProfilUtilisateur;
        this.connexion = connexionMySQL;
        this.utilisateur = utilisateur;
    }

    @Override
    public void handle(ActionEvent event) {
        Button button = (Button) event.getSource();
        if (vue.getBoutonModifNomUtilisateur().equals(button)) {
            if (vue.getTextFieldNomUtilisateur().getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.show();
            } else {
                String nouveauPseudo = vue.getTextFieldNomUtilisateur().getText();
                UtilisateurBD utilBD = new UtilisateurBD(connexion);
                try {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Vous ete sur de vouloir changer ?",
                            ButtonType.NO, ButtonType.YES);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        utilisateur.setPseudo(nouveauPseudo);
                        ;
                        utilBD.updateUtilisateur(utilisateur);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (vue.getBoutonModifEmail().equals(button)) {
            if (vue.getTextFieldEmail().getText().isEmpty()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.show();
            } else {
                String nouveauEmail = vue.getTextFieldEmail().getText();
                UtilisateurBD utilBD = new UtilisateurBD(connexion);
                try {
                    Alert alert = new Alert(AlertType.CONFIRMATION, "Vous ete sur de vouloir changer ?",
                            ButtonType.YES, ButtonType.NO);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.YES) {
                        System.out.println("Oui");
                        utilisateur.setEmail(nouveauEmail);
                        System.out.println("pseudo set");
                        utilBD.updateUtilisateur(utilisateur);
                        System.out.println("update fait");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}